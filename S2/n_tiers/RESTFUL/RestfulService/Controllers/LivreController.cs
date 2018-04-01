using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using RestfulService.Models;
using System.Threading.Tasks;
using RestfulService.DB;
using System.Data.Entity;
using RestfulService.ViewModels;


namespace RestfulService.Controllers
{
    public class LivreController : ApiController
    {
        //GET
        [AllowAnonymous]
        public async Task<IHttpActionResult> Get()
        {
            using (var context = new BibliothequeContext())
            {
                return Ok(await context.Livres.Include(x => x.commentaires).ToListAsync());
            }
        }

        [Authorize(Roles = "Inscrit")]
        [Route("api/livre/GetbyISBN/{param}")]
        public async Task<IHttpActionResult> GetbyISBN(int param)
        {
            using (var context = new BibliothequeContext())
            {
                Livre livre = await context.Livres.FirstOrDefaultAsync(l => l.isbn == param);
                
                if (livre == null)
                {
                    return NotFound();
                }

                LivreViewModel livreView = new LivreViewModel(livre);
                return Ok(livreView);
            }
        }

        [Authorize(Roles = "Inscrit")]
        [Route("api/livre/GetbyAuteur/{param}")]
        public async Task<IHttpActionResult> GetbyAuteur(string param)
        {
            using (var context = new BibliothequeContext())
            {
                var queryResult = context.Livres.Where(l => l.auteur == param);
                List<LivreViewModel> livres = new List<LivreViewModel>();

                await queryResult.ForEachAsync(l => livres.Add(new LivreViewModel(l)));
                return Ok(livres);
            }
        }

        //POST
        [Authorize(Roles = "Bibliothecaire")]
        public async Task<IHttpActionResult> Post([FromBody]LivreViewModel livreView)
        {
            using (var context = new BibliothequeContext())
            {
                Livre newLivre = context.Livres.Add(livreView.ToLivre());
                await context.SaveChangesAsync();
                return Ok(new LivreViewModel(newLivre));
            }
        }

        //DELETE
        [Authorize(Roles = "Bibliothecaire")]
        public async Task<IHttpActionResult> Delete(int id)
        {
            using(var context = new BibliothequeContext())
            {
                Livre livre = await context.Livres.FirstOrDefaultAsync(l => l.Id == id);
                if(livre == null)
                {
                    return NotFound();
                }

                context.Livres.Remove(livre);
                await context.SaveChangesAsync();
            }

            return Ok();
        }



    }
}
