using RestfulService.Models;

namespace RestfulService.ViewModels
{
    public class InscritViewModel
    {
        public string nom { get; set; }
        public string mdp { get; set; }
        public string confirmation { get; set; }
        
        public InscritViewModel() { }

        public InscritViewModel(Inscrit inscrit)
        {
            if(inscrit == null)
            {
                return;
            }

            nom = inscrit.nom;
            mdp = inscrit.mdp;
            confirmation = inscrit.confirmation;
        }

        public Inscrit toInscrit()
        {
            return new Inscrit
            {
                nom = nom,
                mdp = mdp,
                confirmation = confirmation
            
            };
        }
    }
}