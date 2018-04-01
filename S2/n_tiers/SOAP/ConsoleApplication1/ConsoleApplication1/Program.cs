using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;



namespace ConsoleApplication1 // impossible de rename, il ne reconnait plus BiblioServiceReference..
{
    class Program
    {
        // Permet l'appel au méthodes du WebService. 
        private static BiblioServiceReference.BiblioServiceSoapClient proxy;
       // private static readonly string typeUtilisateur = "Inscrit";

        //Variables objets du WebService
        private static BiblioServiceReference.Inscrit utilisateur = null;
        private static BiblioServiceReference.Livre livre = null;
        private static BiblioServiceReference.Livre[] livres;

        // Variable récupérant la saisi de l'utilisateur. 
        private static string saisie = "";
        // Variable de choix. (récupérant des valeurs saisi par l'utilisateur après convertion)
        private static int choix;
        private static string choixCommentaire;
        // Variables utiles lors de l'authentification et de l'inscription. (récupérant des valeurs saisi par l'utilisateur)
        private static int id;
        private static string mdp;
        //Variables utiles lors de la recherche
        private static int isbn;
        private static string auteur;
        // Variable servant la condition d'arrêt du service.
        private static bool stop = false;
        // Variable permettant d'utiliser le service sans être reconnu comme un utilisateur. (n'auras pas accès aux commentaires).
        private static bool invite = false;

        static void Main(string[] args)
        {
            proxy = new BiblioServiceReference.BiblioServiceSoapClient();

            Console.WriteLine("Vous pouvez : ");
            Console.WriteLine("1 - Vous connectez ");
            Console.WriteLine("2 - Vous inscrire ");
            Console.WriteLine("3 - Utiliser le service en tant qu'invité ");
            
            saisie = Console.ReadLine();
            int.TryParse(saisie, out choix); // Convertion de la saisi utilisateur dans le type voulu (ici string to int)

        // BOUCLE D'IDENTIFICATION 
            while (utilisateur == null && invite == false)
            {

                Console.WriteLine(proxy.afficherUtilisateurs());    // utile pour voir l'ajout de l'utilisateur après inscription. (debug)

                switch (choix)
                {
        // CONNECTION 
                    case 1:
                        connection();
                        break;

        // INSCRIPTION + CONNECTION
                    case 2:
                        // === Inscription ===
                        Console.WriteLine("Vous voulez vous inscrire, veuillez saisir votre nom : ");
                        string nom = Console.ReadLine();
                        Console.WriteLine("Veuillez saisir votre mot de passe : ");
                        mdp = Console.ReadLine();

                        id = proxy.inscription(nom, mdp); 
                        Console.WriteLine("Vous êtes bien inscrit ! votre identifiant utilisateur est : " + id);
                        Console.WriteLine(proxy.afficherUtilisateurs());

                        // === Connection ===
                        connection();
                        break;
        // STATUT INVITE
                    case 3 :
                        Console.WriteLine("Vous êtes connecté en tant qu'invité, vos actions sont limitées. Pour accèder à toutes les fonctionnalitées veuillez vous inscrires.");
                        invite = true;
                        break;
        // SAISIE INVALIDE
                    default:
                        Console.WriteLine("La valeur saisie n'est pas valide, veuillez saisir une nouvelle valeur.");
                        saisie = Console.ReadLine();
                        int.TryParse(saisie, out choix);
                        break;
                }
            }

        // BOUCLE METIER DU SERVICE
            while (!stop)
            {
                Console.WriteLine("Que voulez vous faire ?" + Environment.NewLine
                                 + " 1 - Chercher un livre par ISBN (et poster un commentaire)" + Environment.NewLine
                                 + " 2 - Chercher les livres d'un Auteur" + Environment.NewLine
                                 + " 3 - Se deconnecter ");

                saisie = Console.ReadLine();
                int.TryParse(saisie, out choix);

                switch (choix)
                {
        // RECHERCHE ISBN
                    case 1:
                        Console.WriteLine("Veuillez saisir le numero ISBN");
                        int.TryParse(Console.ReadLine(), out isbn);
                        livre = proxy.rechercheParISBN(isbn);
                        
                        if (livre != null)
                        {
                            Console.WriteLine(proxy.getLivreDescription(livre)); 
                        }
                       
                        Console.WriteLine("Voulez vous laisser un commentaire ? (o/n)");
                        choixCommentaire = Console.ReadLine();
                        Console.WriteLine(" choixCommentaire : " + choixCommentaire);
                        if (choixCommentaire.Equals("o") || choixCommentaire.Equals("oui") || choixCommentaire.Equals("yes") || choixCommentaire.Equals("y"))
                        {
                            Console.WriteLine("Veuillez saisir votre commentaire : ");
                            string contenu = Console.ReadLine();
                            string result = proxy.publierCommentaire(utilisateur, contenu, livre);
                            Console.WriteLine(result);
                        }
                        break;
        // RECHERCHE AUTEUR
                    case 2:
                        Console.WriteLine("Veuillez saisir le nom de l'auteur");
                        auteur = Console.ReadLine();
                        livres = proxy.rechercheParAuteur(auteur);
                        foreach (BiblioServiceReference.Livre l in livres)
                        {
                            Console.WriteLine(proxy.getLivreDescription(l)); 
                        }
                        break;
        // DECONNECTION 
                    case 3:
                        utilisateur = null;
                        Console.WriteLine("Vous êtes deconnecté !");
                        Console.WriteLine("Voulez vous vous reconnecter ? (y/n)");
                        saisie = Console.ReadLine();
                        if (saisie.Equals("y"))
                        {
                            connection();
                        }
                        else
                        {
                            Console.WriteLine("Merci d'avoir utilisé notre service. A bientôt !");
                            System.Threading.Thread.Sleep(5000);
                            stop = true;
                        }
                        break;
        // SAISIE INVALIDE
                    default:
                        Console.WriteLine("La valeur saisi n'est pas valide, veuillez en saisir une autre s'il vous plait.");
                        saisie = Console.ReadLine();
                        int.TryParse(saisie, out choix);
                        break;
                }

            }

            proxy.Close();
        }

        public static void connection()
        {
            Console.WriteLine("Veuillez saisir votre identifiant : ");
            int.TryParse(Console.ReadLine(), out id);
            Console.WriteLine("Veuillez saisir votre mot de passe : ");
            mdp = Console.ReadLine();

            utilisateur = proxy.authentificationInscrit(id, mdp);
            if (utilisateur != null)
            {
                Console.WriteLine("Vous êtes connecté !");
            }
            else
            {
                Console.WriteLine("Une erreur à eu lieu, vous n'êtes pas connecté...");
                choix = 1;
            }
        }
    }
}
