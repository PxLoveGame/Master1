using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


/// <summary>
/// Summary description for Bibliothèque
/// </summary>
public static class Bibliotheque
{
    private static List<Livre> livres = new List<Livre>();
    private static List<Inscrit> inscrits = new List<Inscrit>();
    private static List<Bibliothecaire> bibliothecaires = new List<Bibliothecaire>();

    static Bibliotheque() {

        ajoutLivre(new Livre("titre1", "auteur1", 1, 5, "editeur1"));
        ajoutLivre(new Livre("titre2", "auteur2", 2, 10, "editeur2"));
        ajoutLivre(new Livre("titre3", "auteur3", 3, 2, "editeur3"));

        ajoutInscrit(new Inscrit("Paul", "abcd"));
        ajoutInscrit(new Inscrit("Théo", "1234"));
        ajoutInscrit(new Inscrit("Frank", "mdplol"));

        bibliothecaires.Add(new Bibliothecaire("Jean", "oooo"));
        bibliothecaires.Add(new Bibliothecaire("Bob", "pppp"));
        bibliothecaires.Add(new Bibliothecaire("Marc", "iiii"));
    }

    public static void ajoutLivre(Livre l)
    {
        livres.Add(l);
        Console.WriteLine("Le livre " + l.titre + " à bien été ajouté à la bibliothèque");
    }

    public static bool retirerLivre(string titre, string auteur, string editeur)
    {

        foreach (Livre l in livres)
        {
            if (l.titre.Equals(titre) && l.auteur.Equals(auteur) && l.editeur.Equals(editeur))
            {
                return livres.Remove(l);
            }
        }
        return false;
    }

    public static void ajoutInscrit(Inscrit i)
    {
        inscrits.Add(i);
        Console.WriteLine(i.nom + "est maintenant inscrit à la bibliothèque");
    }

    public static bool retirerInscrit(string nom, int id)
    {
        foreach (Inscrit u in inscrits)
        {
            if (u.nom.Equals(nom) && u.id == id)
            {
                return inscrits.Remove(u);
            }
        }
        return false;
    }

    public static Livre[] rechercheParAuteur(string a)
    {
        List<Livre> result = new List<Livre>();

        foreach (Livre l in livres)
        {
            if (l.auteur.Equals(a))
            {
                result.Add(l);
            }
        }
        return result.ToArray();
    }

    public static Livre rechercheParISBN(int i)
    {
        foreach (Livre l in livres) 
        {
            if (l.isbn == i)
            {
                return l;
            }
        }
        return null;
    }

    public static Inscrit authentificationInscrit(int id, string mdp)
    {
            foreach (Inscrit u in inscrits)
            {
                if (u.id == id && object.Equals(u.mdp, mdp))
                {
                    return u;
                }
            }
        return null;
    }

    public static Bibliothecaire authentificationBibliothecaire(int id, string mdp)
    {
        foreach (Bibliothecaire u in bibliothecaires)
        {
            if (u.id == id && object.Equals(u.mdp, mdp))
            {
                return u;
            }
        }
        return null;
    }

    public static int inscription(string nom, string mdp)
    {
        Inscrit inscrit = new Inscrit(nom, mdp);
        ajoutInscrit(inscrit);
        return inscrit.id;
    }

    public static string afficherInscrits()
    {
        string result = "";
        if (inscrits.Count == 0)
        {
            result = "Aucun utilisateurs enregistré";
        }
        else
        {
            foreach (Utilisateur u in inscrits)
            {
                result += u.ToString() + Environment.NewLine;
            }
        }
        return result;
    }

    public static string afficherLivres()
    {
        string result = "";
        if (livres.Count == 0)
        {
            result = "Aucun livres enregistrés";
        }
        else
        {
            foreach (Livre l in livres)
            {
                result += l.ToString() + Environment.NewLine;
            }
        }
        return result;
    }
}