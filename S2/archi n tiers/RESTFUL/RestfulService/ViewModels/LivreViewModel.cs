using System.Collections.Generic;
using RestfulService.Models;

namespace RestfulService.ViewModels
{
    public class LivreViewModel
    {

        public int isbn { get; set; }
        public string titre { get; set; }
        public string auteur { get; set; }
        public int nbExemplaire { get; set; }
        public string editeur { get; set; }

        public virtual List<Commentaire> commentaires { get; set; }

        public LivreViewModel() { }

        public LivreViewModel(Livre livre)
        {
            if(livre == null)
            {
                return;
            }

            isbn = livre.isbn;
            titre = livre.titre;
            auteur = livre.auteur;
            nbExemplaire = livre.nbExemplaire;
            editeur = livre.editeur;

            commentaires = livre.commentaires;
        }

        public Livre ToLivre()
        {
            return new Livre
            {
                isbn = isbn,
                titre = titre,
                auteur = auteur,
                nbExemplaire = nbExemplaire,
                editeur = editeur,

                commentaires = commentaires
            };
        }
    }
}