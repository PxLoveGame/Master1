using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


/// <summary>
/// Summary description for Livre
/// </summary>
public class Livre 
{
    private string _titre;
    public string titre
    {
        get
        {
            return _titre;
        }
        set
        {
            _titre = value;
        }
    }

    private string _auteur;
    public string auteur
    {
        get
        {
            return _auteur;
        }
        set
        {
            _auteur = value;
        }
    }

    private int _isbn;
    public int isbn
    {
        get
        {
            return _isbn;
        }
        set
        {
            _isbn = value;
        }
    }

    private int _nbExemplaire;
    public int nbExemplaire
    {
        get
        {
            return _nbExemplaire;
        }
        set
        {
            _nbExemplaire = value;
        }
    }

    private string _editeur;
    public string editeur
    {
        get
        {
            return _editeur;
        }
        set
        {
            _editeur = value;
        }
    }

    private static List<Commentaire> commentaires;

    public Livre()
    {
    }

	public Livre(string titre, string auteur, int isbn, int nbExemplaire, string editeur)
	{
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.nbExemplaire = nbExemplaire;
        this.editeur = editeur;
        commentaires = new List<Commentaire>();
	}

    public void ajoutCommentaire(Commentaire c)
    {
        commentaires.Add(c);
    }

    public override string ToString()
    {
        string result = "";
        result += "titre : " + titre + Environment.NewLine;
        result += "auteur : " + auteur + Environment.NewLine;
        result += "isbn : " + isbn + Environment.NewLine;
        result += "Nombre d'exemplaire : " + nbExemplaire + Environment.NewLine;
        result += "Editeur : " + editeur + Environment.NewLine;
        result += "Commentaires : " + Environment.NewLine;
        result += afficherCommentaires();

        return result;
    }

    public string afficherCommentaires()
    {
        string result = "";

        foreach (Commentaire c in commentaires)
        {
            result += c.ToString() + Environment.NewLine;
        }

        return result;
    }

    public override bool Equals(object o)
    {
        if (o == null)
            return false;

        Livre livre = o as Livre;

        return this.titre.Equals(livre.titre) &&
            this.auteur.Equals(livre.auteur) &&
            this.isbn == livre.isbn &&
            this.editeur.Equals(livre.editeur);
    }

}