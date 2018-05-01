using System;
using System.Collections.Generic;
using System.ServiceModel;
using Share;

namespace Client
{
    class Client
    {

        private ChannelFactory<ServiceInterface> channelFactory;
        private ServiceInterface service;

        public void start()
        {
            channelFactory = new ChannelFactory<ServiceInterface>("ServiceRecettes");
            channelFactory.Open();

            service = channelFactory.CreateChannel();
        }

        public void Stop()
        {
            channelFactory.Close();
        }


        static void Main(string[] args)
        {
            Client client = new Client();
            client.start();

            int id = client.service.authentification();
            var stop = false;
            var clientAnswerInt = 0;
            var clientAnswerString = "";
            List<Recette> recettes;

            while (stop != true)
            {
                Console.WriteLine("Que voulez vous faire :");
                Console.WriteLine("1 - Ajouter une recette");
                Console.WriteLine("2 - Afficher l'ensemble des recettes");
                Console.WriteLine("3 - Chercher une recette selon un ingrédient");
                Console.WriteLine("4 - Afficher la selection courante");
                Console.WriteLine("5 - Quitter le service");

                clientAnswerInt = int.Parse(Console.ReadLine());

                switch (clientAnswerInt)
                {
                    case 1:
                        Console.WriteLine("Veuillez saisir un nom pour votre recette ");
                        clientAnswerString = Console.ReadLine();
                        Recette recette = new Recette(clientAnswerString);
                        clientAnswerString = "";

                        Console.WriteLine("Veuillez saisir les ingrédients (tapez stop pour quitter)");
                        while(clientAnswerString != "stop")
                        {
                            clientAnswerString = Console.ReadLine();
                            if(!clientAnswerString.Equals("stop"))
                            {
                                recette.ingredients.Add(clientAnswerString);
                            }

                            Console.WriteLine("Veuillez saisir un autre ingrédients (ou stop)");
                        }

                        clientAnswerString = "";
                    
                        client.service.AddRecette(recette);
                        Console.WriteLine(recette.ToString());
                        break;

                    case 2:
                        recettes = new List<Recette>();
                        recettes = client.service.GetAllRecettes(id);
                        
                        foreach(Recette r in recettes)
                        {
                            Console.WriteLine(r.ToString());
                        }

                        break;
                        

                    case 3:
                        Console.WriteLine("Saisissez l'ingrédient pour la recherche");
                        clientAnswerString = Console.ReadLine();

                        recettes = new List<Recette>();
                        recettes = client.service.GetCommonRecettes(id, clientAnswerString);

                        foreach(Recette r in recettes)
                        {
                            Console.WriteLine(r.ToString());
                        }
                        break;

                    case 4:
                        List<Recette> selection = client.service.GetCurrentSelection(id);
                        foreach (Recette r in selection)
                        {
                            Console.WriteLine(r.ToString());
                        }
                        Console.WriteLine(selection.Count);

                        Console.WriteLine("Voulez vous supprimer des recettes ? (0/1)");
                        clientAnswerInt = int.Parse(Console.ReadLine());

                        List<Recette> selection_temp = new List<Recette>(selection);

                        if (clientAnswerInt == 1)
                        {
                            
                            foreach (Recette r in selection)
                            {
                                Console.WriteLine("Voulez vous supprimer la recette de votre selection ? (0/1)");
                                Console.WriteLine(r.ToString());
                                clientAnswerInt = int.Parse(Console.ReadLine());
                                if (clientAnswerInt == 1)
                                {
                                    selection_temp.Remove(r);
                                }
                            }
                        }
                        client.service.UpdateCurrentSelection(id, selection_temp);
                        clientAnswerInt = 0;
                        break;

                    case 5:
                        Console.WriteLine("Merci d'avoir utiliser notre service !");
                        stop = true;
                        client.Stop();
                        break;

                    default:
                        Console.WriteLine("Votre saisi n'est pas correct, veuillez essayer à nouveau.");
                        break;

                }
            }
           





        }
    }

    
}
