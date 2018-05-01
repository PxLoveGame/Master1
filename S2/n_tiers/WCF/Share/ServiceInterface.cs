using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.ServiceModel;

namespace Share
{

    [ServiceContract]
    public interface ServiceInterface
    {

        [OperationContract]
        int authentification();

        [OperationContract]
        void disconnect(int id);

        [OperationContract]
        List<Recette> GetCommonRecettes(int id, String ingredient);

        [OperationContract]
        List<Recette> GetAllRecettes(int id);

        [OperationContract]
        void AddRecette( Recette recette);

        [OperationContract]
        void UpdateCurrentSelection(int id, List<Recette> selection);

        [OperationContract]
        List<Recette> GetCurrentSelection(int id);

    }

    [DataContract]
    public class Recette
    {

        [DataMember]
        public string nom { get; set; }
        [DataMember]
        public List<string> ingredients { get; set; }
       

        public Recette(String n)
        {
            nom = n;
            ingredients = new List<string>();
        }

        public Recette(String n, string[] i)
        {
            nom = n;
            ingredients = new List<string>(i);
        }

        public override string ToString()
        {
            string chaine = "nom : " + nom + "\n ingrédients : ";
            foreach (String i in ingredients)
            {
                chaine += "\n - " + i;
            }
            return chaine;
        }

        public override bool Equals(Object o)
        {
            Recette r = o as Recette;
            return this.ingredients.Equals(r.ingredients) && this.nom.Equals(r.nom);
        }

    }

    [DataContract]
    public class Selection
    {
        [DataMember]
        public List<Recette> selectionCourante { get; set; }

        [DataMember]
        public List<List<Recette>> historique { get; set; }

        public Selection()
        {
            selectionCourante = new List<Recette>();
            historique = new List<List<Recette>>();
        }
    }
}
