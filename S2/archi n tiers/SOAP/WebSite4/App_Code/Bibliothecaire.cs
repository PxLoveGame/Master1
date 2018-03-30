using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for Bibliothecaire
/// </summary>
public class Bibliothecaire : Utilisateur
{
    private static int bibliothecaireID = 0;

	public Bibliothecaire()
	{
	}

    public Bibliothecaire(string nom, string mdp)
    {
        this.id = bibliothecaireID;
        bibliothecaireID = this.id;

        this.mdp = mdp;
        this.nom = nom;
    }


    public Livre ajouterLivre(string titre, string auteur, int isbn, int nbExemplaire, string editeur)
    {
        Livre l = new Livre(titre, auteur, isbn, nbExemplaire, editeur);
        Bibliotheque.ajoutLivre(l);
        return l;
    }

    public Inscrit ajouterInscrit(string nom, string mdp)
    {
        Inscrit u = new Inscrit(nom, mdp);
        Bibliotheque.ajoutInscrit(u);
        return u;
    }

    public bool retirerLivre(string titre, string auteur, string editeur)
    {
        return Bibliotheque.retirerLivre(titre, auteur, editeur);
    }

    public bool retirerInscrit(string nom, int id)
    {
        return Bibliotheque.retirerInscrit(nom, id);
    }



}