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
        List<Recette> GetCommonRecettes(String ingredient);

        [OperationContract]
        void AddRecette(Recette recette);

        [OperationContract]
        void RemoveOfCurrentSelection(Recette r);

        [OperationContract]
        List<Recette> GetCurrentSelection();

        [OperationContract]
        void AddLastSearchToCurrentSelection();

    }

    [DataContract]
    public class Recette
    {
        [DataMember]
        public List<string> Ingredients { get; set; }
       
        [DataMember]
        public List<string> Etapes { get; set; }

    }

    [DataContract]
    public class Selection
    {
        [DataMember]
        public List<Recette> selectionCourante { get; set; }

        [DataMember]
        public List<List<Recette>> historique { get; set; }
    }
}
