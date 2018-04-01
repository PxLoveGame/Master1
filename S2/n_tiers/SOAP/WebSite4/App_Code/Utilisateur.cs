using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;


/// <summary>
/// Summary description for Utilisateur
/// </summary>
public class Utilisateur
{
    public static int uniqueID = 0;

    private int _id;
    public int id
    {
        get
        {
            return _id;
        }
        set
        {
            _id = value + 1;
        }
    }

    private string _mdp;
    public string mdp
    {
        get
        {
            return _mdp;
        }
        set
        {
            _mdp = value;
        }
    }

    private string _nom;
    public string nom
    {
        get
        {
            return _nom;
        }
        set
        {
            _nom = value;
        }
    }

    public Utilisateur() {}

    public Utilisateur(string nom, string mdp)
	{
        this.id = uniqueID;
        uniqueID = this.id;

        this.nom = nom;
        this.mdp = mdp;
	}

    public override bool Equals(object o)
    {
        if (o == null)
            return false;

        Utilisateur utilisateur = o as Utilisateur;

        return this.id == utilisateur.id && this.mdp.Equals(utilisateur.mdp);
    }

    public override string ToString()
    {
        string result = "";
        result += "nom : " + this.nom + Environment.NewLine;
        result += "id : " + this.id + Environment.NewLine;
        result += "mdp : " + this.mdp + Environment.NewLine;

        return result;
    }
}