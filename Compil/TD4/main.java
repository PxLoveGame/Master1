
import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;

class Sommet {

    private String nom;
    private int couleur;
    ArrayList<Sommet> voisins;
    ArrayList<Sommet>  preferences;

    public Sommet(String n)
    {
        nom = n;
        couleur = 0;
        voisins = new ArrayList<Sommet>();
        preferences = new ArrayList<Sommet>();
    }


    public Sommet() {
        // TODO Auto-generated constructor stub
    }

    public int getCouleur()
    {
        return this.couleur;
    }
    public String getNom()
    {
        return nom;
    }

    public void setCouleur(int clr)
    {
        couleur = clr;
    }
    public void ajoutPreference(Sommet s)
    {
        this.preferences.add(s);
        s.preferences.add(this);
    }
    public void arète(Sommet s)
    {
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

    public boolean uniqueVoisin(){
        for(Sommet voisin : voisins){
            if(voisin.voisins.size() == 1)
                return true;
        }
        return false;
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

    //retourne un sommet ayant nb de voisins < k
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
    // retourne le sommet ayant le plus de voisins (dans le cas ou le nb de voisins > k )
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

    // colore un sommet avec la première couleur disponible
    public void set_couleur_disponible(Sommet s, int k)
    {
        boolean available = false;
        int couleur = 1;

        if(s.preferences.size() > 0)
        {
            if(s.voisins.size() == 0){
                s.setCouleur(s.preferences.get(0).getCouleur());
            }
            else
            {
                int i = 0;
                while(i < s.preferences.size() & !available){
                    int couleur_pref = s.preferences.get(i).getCouleur();
                    if(s.preferences.get(i).getCouleur() != 0)
                        couleur = couleur_pref;
                    for(Sommet voisin : s.voisins) {
                        if (voisin.getCouleur() == couleur) {
                            couleur++;
                            if(couleur != couleur_pref){
                                ajuster_coloration(s.preferences.get(i),couleur);
                                if(couleur != couleur_pref)
                                    ajuster_coloration(s, couleur_pref);
                            }
                        }
                        available = true;
                    }
                }
            }
        }
        else {
            System.out.println("Sommet courant : " + s.getNom() + " ");
            if (s.voisins.size() == 0) {
                s.setCouleur(1);
            } else {
                while (!available) {
                    for (Sommet voisin : s.voisins) {
                        if (voisin.getCouleur() == couleur) {
                            couleur++;
                        }
                    }
                    available = true;
                }
            }
        }
            s.setCouleur(couleur);

    }

    public void ajuster_coloration(Sommet sommet, int couleurVoulu){
        for(Sommet voisin : sommet.voisins){
            if(voisin.getCouleur() == couleurVoulu){
                if(voisin.uniqueVoisin()){
                    voisin.setCouleur(voisin.voisins.get(0).getCouleur());
                    voisin.voisins.get(0).setCouleur(couleurVoulu);
                    System.out.println("Couleur ajusté");
                    sommet.setCouleur(couleurVoulu);
                }
            }
        }

    }

    public void colorier(int k)
    {

        if(sommets.size() == 0 ) return;

        Sommet s = sommet_trivialement_coloriable(k);
        if(s != null){    // voisins < k
            retireSommet(s);
            colorier(k);
            ajoutSommet(s);
            set_couleur_disponible(s, k);
        }
        else{             // voisins > k
            s = sommet_avec_MaxVoisin();
            retireSommet(s);
            colorier(k); //ToDo essayer de reintégrer le sommet si possible
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
        int k = 3;

/*
        //essai simple
        System.out.println("Test n°1 : \n sommets {a,b,c,d} \n arètes {ab,ac,bd,cd} \n aucune préférences");
        Sommet a = new Sommet("a");
        Sommet b = new Sommet("b");
        Sommet c = new Sommet("c");
        Sommet d = new Sommet("d");

        a.arète(b);
        a.arète(c);
        b.arète(d);
        c.arète(d);

        ArrayList<Sommet> ss = new ArrayList<Sommet>();
        ss.add(a);
        ss.add(b);
        ss.add(c);
        ss.add(d);
        Graphe g1 = new Graphe(ss);
        g1.colorier(k);
        System.out.println(g1.toString());
        System.out.println(g1.toStringSpill());
*/
        //essai demandé
        System.out.println("Test n°1 : \n sommets {u,v,w,x,y,z} \n arètes {uy,ux,vz,vx,vw,wy,yx} \n préference : u avec w");
        Sommet u = new Sommet("u");
        Sommet v = new Sommet("v");
        Sommet w = new Sommet("w");

        Sommet y = new Sommet("y");
        Sommet x = new Sommet("x");
        Sommet z = new Sommet("z");

        u.arète(y);
        u.arète(x);
        v.arète(z);
        v.arète(x);
        v.arète(w);
        w.arète(y);
        y.arète(x);

        u.ajoutPreference(w);
        ArrayList<Sommet> sommets = new ArrayList<Sommet>();
        sommets.add(u);
        sommets.add(v);
        sommets.add(w);
        sommets.add(x);
        sommets.add(y);
        sommets.add(z);
        Graphe g2 = new Graphe(sommets);
        g2.colorier(k);
        System.out.println(g2.toString());
        System.out.println(g2.toStringSpill());
    }

}
