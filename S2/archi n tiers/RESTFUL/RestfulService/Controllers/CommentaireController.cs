using System.Collections.Generic;
using System.Web.Http;
using RestfulService.Models;
using System.Threading.Tasks;
using RestfulService.DB;
using System.Data.Entity;
using RestfulService.ViewModels;


namespace RestfulService.Controllers
{
    public class CommentaireController : ApiController
    {
        // GET

        //Tout les commentaires, pour un livre donné
        [Authorize(Roles = "Inscrit")]
        public async Task<IHttpActionResult> Get(int id)
        {
            using(var context  = new BibliothequeContext())
            {
                Livre livre = await context.Livres.FirstOrDefaultAsync(l => l.Id == id);
                List<CommentaireViewModel> commentairesView = new List<CommentaireViewModel>();
                foreach(Commentaire c in livre.commentaires)
                {
                    commentairesView.Add(new CommentaireViewModel(c));
                }

                return Ok(commentairesView);
            }
            
        }


        // POST: api/Commentaire
        [Authorize(Roles = "Inscrit")]
        [Route("api/livre/{id}/commentaire")]
        public async Task<IHttpActionResult> Post(int id, [FromBody]CommentaireViewModel commentaireView)
        {
            using (var context = new BibliothequeContext())
            {
                Commentaire newCommentaire = context.Commentaires.Add(commentaireView.toCommentaire());
                (await context.Livres.FindAsync(id)).ajoutCommentaire(newCommentaire);
                await context.SaveChangesAsync();
                return Ok(new CommentaireViewModel(newCommentaire));
            }
        }

        // DELETE: api/Commentaire/5
        [Authorize(Roles = "Inscrit")]
        public async Task<IHttpActionResult> Delete(int id)
        {
            using (var context = new BibliothequeContext())
            {
                Commentaire commentaire = await context.Commentaires.FirstOrDefaultAsync(c => c.Id == id);
                if (commentaire == null)
                {
                    return NotFound();
                }

                context.Commentaires.Remove(commentaire);
                await context.SaveChangesAsync();

                (await context.Livres.FindAsync(id)).retirerCommentaire(commentaire);
            }

            return Ok();
        }
    }
}
