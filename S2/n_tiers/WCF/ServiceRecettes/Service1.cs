using Share;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;

namespace ServiceRecettes
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom de classe "Service1" à la fois dans le code et le fichier de configuration.
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, ConcurrencyMode = ConcurrencyMode.Single)]
    public class Service1 : ServiceInterface
    {

        private List<Recette> recettes { get; set; }
        private Selection selection { get; set; }

        public Service1()
        {
            recettes = new List<Recette>();
            selection = new Selection();
        }


        public void AddLastSearchToCurrentSelection()
        {
            if(selection.historique != null)
            {
                selection.selectionCourante.Concat(selection.historique.Last());
            }
        }

        public void AddRecette(Recette recette)
        {
            if(recette != null)
            {
                recettes.Add(recette);
            }
        }

        public List<Recette> GetCommonRecettes(string ingredient)
        {
            List<Recette> results = new List<Recette>();
            foreach (Recette r in recettes) {
                foreach(string i in r.Ingredients)
                {
                    if(i == ingredient)
                    {
                        results.Add(r);
                    }
                }
            }

            if(results != null)
            {
                selection.historique.Add(results);
            }

            return results;
        }

        public List<Recette> GetCurrentSelection()
        {
            return selection.selectionCourante;
        }

        public void RemoveOfCurrentSelection(Recette recette)
        {
            if(recette != null)
            {
                selection.selectionCourante.Remove(recette);
            }
        }
    }

}
