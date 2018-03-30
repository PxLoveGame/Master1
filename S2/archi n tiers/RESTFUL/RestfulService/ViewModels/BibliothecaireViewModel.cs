using RestfulService.Models;

namespace RestfulService.ViewModels
{
    public class BibliothecaireViewModel
    {
        public string nom { get; set; }
        public string mdp { get; set; }
        public string confirmation { get; set; }

        public BibliothecaireViewModel()
        {

        }

        public BibliothecaireViewModel(Bibliothecaire bibliothecaire)
        {
            if(bibliothecaire == null)
            {
                return;
            }

            nom = bibliothecaire.nom;
            mdp = bibliothecaire.mdp;
            confirmation = bibliothecaire.confirmation;
        }

        public Bibliothecaire toBibliothecaire()
        {
            return new Bibliothecaire
            {
                nom = nom,
                mdp = mdp,
                confirmation = confirmation
            };
        }
    }
}