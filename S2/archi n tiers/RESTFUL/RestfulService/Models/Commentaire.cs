
namespace RestfulService.Models
{
    public class Commentaire
    {
        public int Id { get; set; }
        public Inscrit auteur { get; set; }
        public string contenu { get; set; }

        public Commentaire() { }
        public Commentaire(Inscrit a,  string c)
        {
            auteur = a;
            contenu = c;
        }
        
    }
}