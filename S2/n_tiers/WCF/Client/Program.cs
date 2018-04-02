using System;
using System.Collections.Generic;
using System.Linq;
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
            channelFactory = new ChannelFactory<ServiceInterface>("SI");
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

            var stop = false;
            var clientAnswerInt = 0;
            var clientAnswerString = "";

            while (stop != true)
            {
                Console.WriteLine("Que voulez vous faire :");
                Console.WriteLine("1 - Ajouter une recettes");
                Console.WriteLine("2 - Afficher la selection courante");
                Console.WriteLine("3 - Chercher une recherche selon un ingrédient");
                Console.WriteLine("4 - Ajouter une recettes à sa selection courante (dernière recherche effectué)");
                Console.WriteLine("5 - Quitter le service");

                clientAnswerInt = int.Parse(Console.ReadLine());

                switch (clientAnswerInt)
                {
                    case 1:
                        Recette recette = new Recette();
                     
                        Console.WriteLine("Veuillez saisir les ingrédients (tapez stop pour quitter)");
                        while(clientAnswerString != "stop")
                        {
                            clientAnswerString = Console.ReadLine();
                            recette.Ingredients.Add(clientAnswerString);
                            Console.WriteLine("Veuillez saisir un autre ingrédients (ou stop)");
                        }

                        clientAnswerString = "";
                    
                        client.service.AddRecette(recette);
                        break;

                    case 2:

                        List<Recette> selection = client.service.GetCurrentSelection();
                        Console.WriteLine(selection);

                        Console.WriteLine("Voulez vous supprimer des recettes ? (0/1)");
                        clientAnswerInt = int.Parse(Console.ReadLine());

                        if(clientAnswerInt == 1)
                        {
                            foreach (Recette r in selection)
                            {
                                Console.WriteLine("Voulez vous supprimer la recette de votre selection ? (0/1)");
                                clientAnswerInt = int.Parse(Console.ReadLine());
                                if (clientAnswerInt == 1)
                                {
                                    client.service.RemoveOfCurrentSelection(r);
                                }
                            }
                        }
                        clientAnswerInt = 0;
                        break;

                    case 3:
                        Console.WriteLine("Saisissez l'ingrédient pour la recherche");
                        clientAnswerString = Console.ReadLine();

                        List<Recette> recettes = new List<Recette>();
                        recettes = client.service.GetCommonRecettes(clientAnswerString);

                        Console.WriteLine(recettes);
                        break;

                    case 4:

                        client.service.AddLastSearchToCurrentSelection();
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
