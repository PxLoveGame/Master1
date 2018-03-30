using RestfulService.Models;

namespace RestfulService.ViewModels
{
    public class CommentaireViewModel
    {
        public Inscrit auteur { get; set; }
        public string contenu { get; set; }

        public CommentaireViewModel() { }

        public CommentaireViewModel(Commentaire commentaire)
        {
            if(commentaire == null)
            {
                return;
            }

            auteur = commentaire.auteur;
            contenu = commentaire.contenu;
        }

        public Commentaire toCommentaire()
        {
            return new Commentaire
            {
                auteur = auteur,
                contenu = contenu,
            };
        }
    }
}