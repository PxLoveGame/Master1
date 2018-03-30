using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


/// <summary>
/// Summary description for Commentaire
/// </summary>
public class Commentaire
{
    private Utilisateur _auteur;
    public Utilisateur auteur
    {
        get { return _auteur; }
        set { _auteur = value; }
    }

    private string _contenu;
    public string contenu
    {
        get { return _contenu; }
        set { _contenu = value; }
    }

    public Commentaire() { }
	public Commentaire(Utilisateur u, string c)
	{
        auteur = u;
        contenu = c;
	}

    public override string ToString()
    {
        return auteur.nom + " : " + contenu;
    }

}