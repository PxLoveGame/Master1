using System.Web.Http;
using RestfulService.Models;
using System.Threading.Tasks;
using RestfulService.DB;
using System.Data.Entity;
using RestfulService.ViewModels;
using Microsoft.AspNet.Identity;

namespace RestfulService.Controllers
{
    public class InscritController : ApiController
    {

        //GET
        //ToDo : GetAll retournant un InscritViewModel plutôt qu'un Inscrit

        [Authorize(Roles = "Bibliothecaire")]
        public async Task<IHttpActionResult> Get()
        {
            using (var context = new BibliothequeContext())
            {
                return Ok(await context.Inscrits.ToListAsync());
            }
        }


        [Authorize(Roles = "Bibliothecaire , Inscrit")]
        public async Task<IHttpActionResult> Get(int id)
        {
            using (var context = new BibliothequeContext())
            {
                Inscrit inscrit = await context.Inscrits.FirstOrDefaultAsync(i => i.Id == id);

                if(inscrit == null)
                {
                    return NotFound();
                }

                return Ok(new InscritViewModel(inscrit));
            }
        }


        //POST
        [Authorize(Roles = "Bibliothecaire")]
        public async Task<IHttpActionResult> Post([FromBody]InscritViewModel inscritView)
        {
            using (var authRepository = new AuthRepository())
            {
                IdentityResult result = await authRepository.RegisterInscrit(inscritView.toInscrit());

                IHttpActionResult errorResult = GetErrorResult(result);

                if (errorResult != null)
                {
                    return errorResult;
                }

                return Ok(new InscritViewModel(inscritView.toInscrit()));
            }
        }

        //DELETE
        [Authorize(Roles = "Bibliothecaire , Inscrit")]
        public async Task<IHttpActionResult> Delete(int id)
        {

            using (var context = new BibliothequeContext())
            {
                Inscrit inscrit = await context.Inscrits.FirstOrDefaultAsync(i => i.Id == id);
                if (inscrit == null)
                {
                    return NotFound();
                }

                using(var authRepository = new AuthRepository())
                {
                    IdentityResult result = await authRepository.UnRegisterInscrit(inscrit);

                    IHttpActionResult errorResult = GetErrorResult(result);

                    if (errorResult != null)
                    {
                        return errorResult;
                    }
                }

                await context.SaveChangesAsync();
            }

            return Ok();
        }

        protected override void Dispose(bool disposing)
        {
            using (var authRepository = new AuthRepository())
            {
                if (disposing)
                {
                    authRepository.Dispose();
                }

                base.Dispose(disposing);
            }
        }
        private IHttpActionResult GetErrorResult(IdentityResult result)
        {
            if (result == null)
            {
                return InternalServerError();
            }

            if (!result.Succeeded)
            {
                if (result.Errors != null)
                {
                    foreach (string error in result.Errors)
                    {
                        ModelState.AddModelError("", error);
                    }
                }

                if (ModelState.IsValid)
                {
                    // No ModelState errors are available to send, so just return an empty BadRequest.
                    return BadRequest();
                }

                return BadRequest(ModelState);
            }

            return null;
        }
    }
}

