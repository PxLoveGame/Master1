using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for Inscrit
/// </summary>
public class Inscrit : Utilisateur
{
    private static int inscritID = 0;
    public Inscrit() { }

	public Inscrit(string nom, string mdp)
	{
        this.id = inscritID;
        inscritID = this.id;

        this.nom = nom;
        this.mdp = mdp;
	}


    public void publierCommentaire(string contenu, Livre livre)
    {
        livre.ajoutCommentaire(new Commentaire(this, contenu));
        Console.WriteLine("Le commentaire à bien été publié.");
    }
}