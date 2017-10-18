package compil;

import java.util.ArrayList;

class Sommet {

  private String nom;
  private int couleur;
  ArrayList<Sommet> voisins;

  public Sommet(String n)
  {
    nom = n;
    couleur = 0;
    voisins = new ArrayList<Sommet>();
  }


  public Sommet() {
	// TODO Auto-generated constructor stub
}

  public int getCouleur(){
	return this.couleur;
}

  public String getNom(){
	  return nom;
  }
  public void setCouleur(int clr)
  {
    couleur = clr;
  }

  public void arète(Sommet s){
	  this.voisins.add(s);
	  s.voisins.add(this);
  }

  public void ajoutVoisin()
  {
    for(Sommet voisin : voisins){
    	voisin.voisins.add(this);
    }
  }

  public void retireVoisins()
  {
    for (Sommet voisin : voisins) {
      voisin.voisins.remove(this);
    }
  }



  public String toString()
  {
	  String rslt = "\n nom : " + nom +" couleur : "+ couleur +"\n voisins : ( ";
	  for(Sommet sommet : voisins){
		  rslt += sommet.nom +" ";
	  }
	  rslt += ")";
	  return rslt;
  }

}

class Graphe {

  private ArrayList<Sommet> sommets;
  private ArrayList<Sommet> sommets_spille;

  public Graphe(ArrayList<Sommet> smts)
  {
	  System.err.println("Taille : " + smts.size());
    sommets = smts;
    sommets_spille = new ArrayList<Sommet>();
  }

  public void ajoutSommet(Sommet sommet)
  {
    sommets.add(sommet);
    sommet.ajoutVoisin();
  }

  public void retireSommet(Sommet sommet)
  {
    sommets.remove(sommet);
    sommet.retireVoisins();
  }

  private Sommet sommet_trivialement_coloriable(int k)
  {
    for (Sommet sommet : sommets)
    {
      if(sommet.voisins.size() < k)
      {
        return sommet;
      }
    }
    return null;
  }

  private Sommet sommet_avec_MaxVoisin(){
	System.out.println("In MaxVoisins, j'ai " + sommets.size() + " sommets");
	Sommet s = sommets.get(0);
	for (Sommet sommet : sommets)
	{
		if(s.voisins.size() < sommet.voisins.size())
		{
			s = sommet;
		}
	}
	return s;
  }

  public void set_couleur_disponible(Sommet s, int k)
  {
	boolean valide = false;
	System.out.println("Sommet courant : "+ s.getNom()+" ");
	if(s.voisins.size() == 0){
		s.setCouleur(1);
	}
	else{

		int color = 1;
		boolean available = false;
		while(!available){
			for(Sommet voisin : s.voisins){
				if(voisin.getCouleur() == color){
					color++;
					break;
				}
			}
			available = true;
		}
		s.setCouleur(color);
		/*
		for(int i = 1; i < k; i++)
		{
			for(Sommet voisin : s.voisins)
			{
				System.out.println("Voisin : "+voisin.getNom()+"  "+voisin.getCouleur());
				if(voisin.getCouleur() != i){
					valide = true;
				}
				else valide = false;
			}
			s.setCouleur(i);

			return;

		}
	*/
  }
 }

  public void colorier(int k)
  {

	  if(sommets.size() == 0 ) return;
	Sommet s = sommet_trivialement_coloriable(k);

	if(s != null){
		retireSommet(s);
		colorier(k);
		ajoutSommet(s);
		set_couleur_disponible(s, k);
	}
	else{
		s = sommet_avec_MaxVoisin();
	    retireSommet(s);
	    colorier(k);
	    //ToDo essayer de reintégrer le sommet si possible
		sommets_spille.add(s);

	}
  }

  public String toString()
  {
	  String rslt = "Coloration : \n";
	  for(Sommet sommet : sommets)
		  rslt += sommet.toString() + " \n";
	  return rslt;
  }

  public String toStringSpill(){
	  String rslt = "Sommet spill : ";
	  for(Sommet sommet : sommets_spille)
		  rslt += sommet.getNom()+ " ";
	  return rslt;
  }
}

class main {

	public static void main(String[] args ) {

		Sommet a = new Sommet("a");
		Sommet b = new Sommet("b");
		Sommet c = new Sommet("c");
		Sommet d = new Sommet("d");

		a.arète(b);
		a.arète(c);
		b.arète(d);
		c.arète(d);

		int k = 3;

		ArrayList<Sommet> ss = new ArrayList<Sommet>();
		ss.add(a);
		ss.add(b);
		ss.add(c);
		ss.add(d);
		Graphe g1 = new Graphe(ss);
		g1.colorier(k);
		System.out.println(g1.toString());
		System.out.println(g1.toStringSpill());

	}
}
