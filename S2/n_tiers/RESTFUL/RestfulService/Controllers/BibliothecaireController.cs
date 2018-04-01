using System.Web.Http;
using RestfulService.Models;
using System.Threading.Tasks;
using RestfulService.DB;
using System.Data.Entity;
using RestfulService.ViewModels;
using Microsoft.AspNet.Identity;

namespace RestfulService.Controllers
{
    public class BibliothecaireController : ApiController
    {
        //GET
        //ToDo : GetAll retournant un BibliothecaireViewModel plutôt qu'un Bibliothecaire
        //[Authorize(Roles = "Bibliothecaire")]
        [AllowAnonymous]
        public async Task<IHttpActionResult> Get()
        {
            using(var context = new BibliothequeContext())
            {
                return Ok(await context.Bibliothecaires.ToListAsync());
            }
        }

        [Authorize(Roles = "Bibliothecaire")]
        public async Task<IHttpActionResult> Get(int id)
        {
            using(var context = new BibliothequeContext())
            {
                Bibliothecaire bibliothecaire = await context.Bibliothecaires.FirstOrDefaultAsync(b => b.Id == id);

                if(bibliothecaire == null)
                {
                    return NotFound();
                }

                return Ok(new BibliothecaireViewModel(bibliothecaire));
            }
        }
        
        //POST
        [AllowAnonymous]
        public async Task<IHttpActionResult> Post([FromBody] BibliothecaireViewModel bibliothecaireView)
        {

            using (var authRepository = new AuthRepository())
            {

                if (!ModelState.IsValid)
                {
                    return BadRequest(ModelState);
                }

                IdentityResult result = await authRepository.RegisterBibliothecaire(bibliothecaireView.toBibliothecaire());

                IHttpActionResult errorResult = GetErrorResult(result);

                if(errorResult != null)
                {
                    return errorResult;
                }

                return Ok(new BibliothecaireViewModel(bibliothecaireView.toBibliothecaire()));
            }

        }

        //DELETE
        [AllowAnonymous]
        public async Task<IHttpActionResult> Delete(int id)
        {
            using (var context = new BibliothequeContext())
            {
                Bibliothecaire bibliothecaire = await context.Bibliothecaires.FirstOrDefaultAsync(b => b.Id == id);
                if (bibliothecaire == null)
                {
                    return NotFound();
                }

                using (var authRepository = new AuthRepository())
                {
                    IdentityResult result = await authRepository.UnRegisterBibliothecaire(bibliothecaire);

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
            using(var authRepository = new AuthRepository())
            {
                if(disposing)
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
