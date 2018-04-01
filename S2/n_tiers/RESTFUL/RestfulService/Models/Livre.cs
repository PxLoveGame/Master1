using System.Collections.Generic;

namespace RestfulService.Models
{
    public class Livre
    {
        public int Id { get; set; }
        public int isbn { get; set; }
        public string titre { get; set; }
        public string auteur { get; set; }
        public int nbExemplaire { get; set; }
        public string editeur { get; set; }
       
        public virtual List<Commentaire> commentaires { get; set; }

        public Livre()
        {
        }

        public Livre(string titre, string auteur, int isbn, int nbExemplaire, string editeur)
        {
            this.titre = titre;
            this.auteur = auteur;
            this.isbn = isbn;
            this.nbExemplaire = nbExemplaire;
            this.editeur = editeur;
            commentaires = new List<Commentaire>();
        }

        public void ajoutCommentaire(Commentaire c)
        {
            commentaires.Add(c);
        }

        public void retirerCommentaire(Commentaire c)
        {
            commentaires.Remove(c);
        }
    }
}