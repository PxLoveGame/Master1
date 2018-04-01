using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;


[WebService(Namespace = "http://tempuri.org/")]
[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
// To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
// [System.Web.Script.Services.ScriptService]

public class BiblioService : System.Web.Services.WebService
{
    /*
    private Bibliotheque _bibliotheque;
    public Bibliotheque bibliotheque
    {
        get { return _bibliotheque; }
        set { _bibliotheque = value; }
    }
    */

    public BiblioService () {
        //Uncomment the following line if using designed components 
        //InitializeComponent(); 
    }

    // ============= WebMethode Utilitaire ==============

    [WebMethod]
    public string HelloWorld() {
        return "Hello World";
    }

    [WebMethod]
    public string afficherUtilisateurs()
    {
        return Bibliotheque.afficherInscrits();
    }

    [WebMethod]
    public string afficherLivres()
    {
        return Bibliotheque.afficherLivres();
    }

    [WebMethod]
    public string getLivreDescription(Livre livre)
    {
        return livre.ToString();
    }


    // ============ WebMethode Pour Inscrits ==============
    [WebMethod]
    public Livre[] rechercheParAuteur(string auteur)
    {
        return Bibliotheque.rechercheParAuteur(auteur);
    }

    [WebMethod]
    public Livre rechercheParISBN(int isbn)
    {
        return Bibliotheque.rechercheParISBN(isbn);
    }

    [WebMethod]
    public string publierCommentaire(Inscrit inscrit, string contenu, Livre livre)
    {
        if (inscrit != null)
        {
            inscrit.publierCommentaire(contenu, livre);
            return "Commentaire posté";
        }
        else
        {
            return "Vous devez être connecté pour écrire un commentaire";
        }
    }


    // ToDo une seule méthode pour les deux types d'utilisateurs.
    /*
    [WebMethod]
    public Utilisateur authentification(int id, string mdp, string type)
    {
        if (type.Equals("Inscrit"))
        {
            return Bibliotheque.authentificationInscrit(id, mdp);
        }
        else
        {
            return Bibliotheque.authentificationBibliothecaire(id, mdp);
        }
    }
    */

    [WebMethod]
    public Inscrit authentificationInscrit(int id, string mdp)
    {
        return Bibliotheque.authentificationInscrit(id, mdp);
    }

  
    [WebMethod]
    public int inscription(string nom, string mdp)
    {
        return Bibliotheque.inscription(nom, mdp);
    }

    

    // ============ WebMethode Pour les Bibliothecaires ==============

    [WebMethod]
    public Bibliothecaire authentificationBibliothecaire(int id, string mdp)
    {
        return Bibliotheque.authentificationBibliothecaire(id, mdp);
    }


    [WebMethod]
    public string ajoutInscrit(Bibliothecaire bibliothecaire, string nom, string mdp)
    {
        if (bibliothecaire != null)
        {
            Inscrit i = bibliothecaire.ajouterInscrit(nom, mdp);
            return i.ToString();
        }
        else
        {
            return "Impossible d'ajouter l'utilisateur, vous n'êtes pas connecté";
        }
    }

    [WebMethod]
    public string ajoutLivre(Bibliothecaire bibliothecaire, string titre, string auteur, int isbn, int nbExemplaire, string editeur)
    {
        if (bibliothecaire != null)
        {
            Livre l = bibliothecaire.ajouterLivre(titre, auteur, isbn, nbExemplaire, editeur);
            return l.ToString();
        }
        else
        {
            return "Impossible d'ajouter le livre , vous n'êtes pas connecté";
        }
    }

    [WebMethod]
    public string retirerLivre(Bibliothecaire bibliothecaire, string titre, string auteur, string editeur)
    {
        bool result;
        if (bibliothecaire != null)
        {
            result = bibliothecaire.retirerLivre(titre, auteur, editeur);
            if (result)
            {
                return "Le livre à bien été retiré";
            }
            else
            {
                return "Le livre n'as pas pu être retirer car il n'est pas présent dans le catalogue";
            }
        }
        else
        {
            return "Vous ne pouvez pas retirer de livre car vous n'êtes pas connecté";
        }
    }

    [WebMethod]
    public string retirerUtilisateur(Bibliothecaire bibliothecaire, int id, string nom)
    {
        bool result;
        if (bibliothecaire != null)
        {
            result = bibliothecaire.retirerInscrit(nom, id);
            if (result)
            {
                return "L'inscrit à bien été desabonné ";
            }
            else
            {
                return "L'inscrit n'as pas pu être désabonné car il n'est pas inscrit";
            }
        }
        else
        {
            return "Vous ne pouvez pas desabonné d'utilisateur car vous n'êtes pas connecté";
        }
    }


    
    
}