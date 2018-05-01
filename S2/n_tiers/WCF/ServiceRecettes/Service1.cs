using Share;
using System.Collections.Generic;
using System.ServiceModel;

namespace ServiceRecettes
{
    // REMARQUE : vous pouvez utiliser la commande Renommer du menu Refactoriser pour changer le nom de classe "Service1" à la fois dans le code et le fichier de configuration.
    [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single, ConcurrencyMode = ConcurrencyMode.Single)]
    public class Service1 : ServiceInterface
    {

        private static int ID = 0;

        private List<Recette> recettes { get; set; }
        private IDictionary<int,Selection>  selection { get; set; }

        public Service1()
        {
            recettes = new List<Recette>(new Recette[] {
                new Recette("Mille feuilles", new string[] {"pâte feuilletée", "glaçage" , "crème"}),
                new Recette("Eclair au chocolat", new string[] {"chocolat", "crème" }),
                new Recette("Panini de la fac", new string[] {"un peu de viande (quelconque)", "un peu de fromage", "pain trop cuit", "beaucoup d'air"}),
            });
            selection = new Dictionary<int, Selection>();
        }


        public void AddRecette(Recette recette)
        {
            if(recette != null)
            {
                recettes.Add(recette);
            }
        }

        public List<Recette> GetAllRecettes(int id)
        {
            Selection s = null;
            selection.TryGetValue(id, out s);
            s.historique.Add(recettes);

            s.selectionCourante = new List<Recette>(recettes);
            return recettes;
        }

        public List<Recette> GetCommonRecettes(int id, string ingredient)
        {
            List<Recette> results = new List<Recette>();
            foreach (Recette r in recettes) {
                foreach(string i in r.ingredients)
                {
                    if(i.Equals(ingredient))
                    {
                        results.Add(r);
                    }
                }
            }

            if(results.Count != 0)
            {
                Selection s = null;
                selection.TryGetValue(id, out s);
                if(s == null)
                {
                    return null;
                }

                s.historique.Add(results);
                s.selectionCourante = new List<Recette>(results);
            }

            return results;
        }

        public List<Recette> GetCurrentSelection(int id)
        {
            Selection s = null;
            selection.TryGetValue(id, out s);
            return s.selectionCourante;
        }

        public void UpdateCurrentSelection(int id, List<Recette> selectionCourante)
        {
            Selection s = null;
            selection.TryGetValue(id, out s);

            if (selection != null)
            {
                s.selectionCourante = new List<Recette>(selectionCourante);     
            }
        }

        public int authentification()
        {
            int id = ID++;
            selection.Add(id, new Selection());
            return id;
        }

        public void disconnect(int id)
        {
            selection.Remove(id);
        }

        
    }

}
