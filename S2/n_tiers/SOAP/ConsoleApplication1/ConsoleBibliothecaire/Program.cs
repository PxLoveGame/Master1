using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ConsoleBibliothecaire
{
    class Program
    {
        private static BiblioServiceReference.BiblioServiceSoapClient proxy;
        
        private static BiblioServiceReference.Bibliothecaire utilisateur = null;

        // Variable récupérant la saisi de l'utilisateur. 
        private static string saisie = "";

        // Variable utile a la connection
        private static int id;
        private static string mdp;

        // Variable de choix d'action 
        private static int choix;

        //Variable servant au parametre des WebMethod
        //Utilisateur - id et mdp déjà définie
        private static string nom;
        // Livre 
        private static string titre;
        private static string auteur;
        private static int isbn;
        private static int nbExemplaire;
        private static string editeur;

        // String de retour des WebMethods
        private static string result;
        //Boolean d'arret
        private static bool stop = false;
           

        static void Main(string[] args)
        {
            proxy = new BiblioServiceReference.BiblioServiceSoapClient();

            

            while (utilisateur == null)
            {
                connection();
            }

            while (!stop)
            {
                Console.WriteLine("Veuillez choisir une action : ");
                Console.WriteLine("1 - Ajouter un utilisateur ");
                Console.WriteLine("2 - Retirer un utilisateur ");
                Console.WriteLine("3 - Ajouter un livre ");
                Console.WriteLine("4 - Retirer un livre ");
                Console.WriteLine("5 - Afficher le catalogues ");
                Console.WriteLine("6 - Afficher la liste des abonnés ");
                Console.WriteLine("7 - Se deconnecter ");

                saisie = Console.ReadLine();
                int.TryParse(saisie, out choix);

                switch (choix)
                {
        // Ajout de livre
                    case 1:
                        Console.WriteLine("Veuillez saisir le nom de l'utilisateur : ");
                        nom = Console.ReadLine();
                        Console.WriteLine("Veuillez saisir le mdp de l'utilisateur : ");
                        mdp = Console.ReadLine();

                        result = proxy.ajoutInscrit(utilisateur, nom, mdp);
                        Console.WriteLine(result);
                        break;
        // retrait utilisateur
                    case 2:
                        Console.WriteLine("Veuillez saisir l'identifiant de l'utilisateur à retirer ");
                        saisie = Console.ReadLine();
                        int.TryParse(saisie, out id);

                        Console.WriteLine("Veuillez saisir le nom de l'utilisateur : ");
                        nom = Console.ReadLine();

                        result = proxy.retirerUtilisateur(utilisateur, id, nom);
                        Console.WriteLine(result);
                        break;
        // Ajout livre
                    case 3:
                        Console.WriteLine("Veuillez saisir le titre du livre ");
                        titre = Console.ReadLine();
                        Console.WriteLine("Veuillez saisir l'auteur du livre ");
                        auteur = Console.ReadLine();
                        Console.WriteLine("Veuillez saisir l'ISBN du livre ");
                        saisie = Console.ReadLine();
                        int.TryParse(saisie, out isbn);
                        Console.WriteLine("Veuillez saisir le nombre d'exemplaire ");
                        saisie = Console.ReadLine();
                        int.TryParse(saisie, out nbExemplaire);
                        Console.WriteLine("Veuillez saisir l'editeur ");
                        editeur = Console.ReadLine();

                        result = proxy.ajoutLivre(utilisateur, titre, auteur, isbn, nbExemplaire, editeur);
                        Console.WriteLine(result);
                
                        break;
        // retrait livre
                    case 4:
                        Console.WriteLine("Veuillez saisir le titre du livre ");
                        titre = Console.ReadLine();
                        Console.WriteLine("Veuillez saisir l'auteur du livre ");
                        auteur = Console.ReadLine();
                        Console.WriteLine("Veuillez saisir l'editeur ");
                        editeur = Console.ReadLine();

                        result = proxy.retirerLivre(utilisateur, titre, auteur, editeur);
                        Console.WriteLine(result);
                        break;
        // Affichage du catalogues
                    case 5:
                        Console.WriteLine("Catalogues : ");
                        Console.WriteLine(proxy.afficherLivres());
                        break;
        // Affichages des abonnées
                    case 6:
                        Console.WriteLine("Liste des abonnées : ");
                        Console.WriteLine(proxy.afficherUtilisateurs());
                        break;
        // Arret du service
                    case 7:
                        utilisateur = null;
                        Console.WriteLine("Vous êtes deconnecté ! ");
                        Console.WriteLine("Voulez vous vous reconnecter ? (y/n)");
                        saisie = Console.ReadLine();
                        if (saisie.Equals("y"))
                        {
                            connection();
                        }
                        else
                        {
                            Console.WriteLine("Merci d'avoir utiliser notre service, à bientôt ");
                            System.Threading.Thread.Sleep(5000);
                            stop = true;
                        }

                        break;
        // Mauvaise saisie 
                    default:
                        Console.WriteLine("La valeur saisi est incorrect veuillez en saisir une nouvelle.. ");
                        saisie = Console.ReadLine();
                        int.TryParse(saisie, out choix);
                        break;
                }
                
            }

            proxy.Close();
        }

        public static void connection()
        {

            Console.WriteLine("Connectez vous pour acceder aux services de bibliothécaire");
            Console.WriteLine("Veuillez saisir votre identifiant : ");

            saisie = Console.ReadLine();
            int.TryParse(saisie, out id);

            Console.WriteLine("Veuillez saisir votre mot de passe : ");

            mdp = Console.ReadLine();

            utilisateur = proxy.authentificationBibliothecaire(id, mdp);
            if (utilisateur != null)
            {
                Console.WriteLine("Vous êtes maintenant connecté");
            }
            else 
            {
                Console.WriteLine("Un problème a eu lieu, veuillez essayer à nouveau");
            }
        }
    }
}
