

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
    private Sommet sommet_trivialement_coloriable(int k){
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

    public int coloration_preferences(Sommet s){
        boolean disponible = false;
        int couleur = 1;

        if(s.voisins.size() == 0){
            s.setCouleur(s.preferences.get(0).getCouleur());
        }
        else
        {
            int i = 0;
            while(i < s.preferences.size() & !disponible){
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
                    disponible = true;
                }
            }
        }
        return couleur;
    }

    public int coloration_trivial(Sommet s){
        boolean disponible = false;
        int couleur = 1;
        int tmp_couleur = 0;


        if (s.voisins.size() == 0) {
            s.setCouleur(couleur);
        }
        else {
            while (!disponible) {
                for (Sommet voisin : s.voisins) {
                    if (voisin.getCouleur() == couleur) {
                        couleur++;
                        if(couleur == tmp_couleur)
                            couleur++;
                    }
                    else{
                        tmp_couleur = voisin.getCouleur();
                    }
                }
                disponible = true;
            }
        }
        return couleur;
    }

    public void set_couleur_disponible(Sommet s, int k)
    {
        int couleur;

        if(s.preferences.size() > 0) {
            couleur = coloration_preferences(s);
        }
        else {
            couleur = coloration_trivial(s);
        }

        if(couleur > k) {
            retireSommet(s);
            System.out.println("Coloration du sommet ["+ s.getNom()+"] impossible !");
        }
        else
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

    public void coloration_sommets_spill(Sommet sommet,int k){
        int tmp_couleur = 0;
        int couleur = 1;
        if(sommet.voisins.get(0).getCouleur() != 0){
            couleur = sommet.voisins.get(0).getCouleur();
        }

        ajoutSommet(sommet);
        sommet.setCouleur(couleur);
        for(Sommet voisin: sommet.voisins){
            if(couleur == voisin.getCouleur()){
                couleur++;
                if(couleur == tmp_couleur)
                    couleur++;
            }
            else{
                tmp_couleur = voisin.getCouleur();
            }
        }
        if(couleur > k){
            retireSommet(sommet);
            System.out.println("Ajout et coloration  du sommet spill ["+ sommet.getNom()+"] impossible !");
        }
        else
            sommet.setCouleur(couleur);
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
            if(sommets_spille.size() != 0){
                coloration_sommets_spill(s,k);
            }

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


        System.out.println("\n\n\n============== n°1 ==============");
        System.out.println("Test n°1 : \n sommets {a,b,c,d} \n arètes {ab,ac,bd,bc,cd} \n aucune préférences");
        System.out.println("Test la coloration");
        Sommet a1 = new Sommet("a");
        Sommet b1 = new Sommet("b");
        Sommet c1 = new Sommet("c");
        Sommet d1 = new Sommet("d");

        a1.arète(b1);
        a1.arète(c1);
        b1.arète(d1);
        b1.arète(c1);
        c1.arète(d1);

        ArrayList<Sommet> sommets1 = new ArrayList<Sommet>();
        sommets1.add(a1);
        sommets1.add(b1);
        sommets1.add(c1);
        sommets1.add(d1);
        Graphe g1 = new Graphe(sommets1);
        g1.colorier(k);
        System.out.println(g1.toString());
        System.out.println(g1.toStringSpill());

        System.out.println("\n\n\n============== n°2 ==============");
        System.out.println("Test n°2 : \n sommets {a,b,c,d} \n arètes {ab,ac,ad,bd,bc,cd} \n aucune préférences");
        System.out.println("Test la coloration optimiste (essai de réinsérer les sommets spills)");
        Sommet a2 = new Sommet("a");
        Sommet b2 = new Sommet("b");
        Sommet c2 = new Sommet("c");
        Sommet d2 = new Sommet("d");

        a2.arète(b2);
        a2.arète(c2);
        a2.arète(d2);
        b2.arète(d2);
        b2.arète(c2);
        c2.arète(d2);

        ArrayList<Sommet> sommets2 = new ArrayList<Sommet>();
        sommets2.add(a2);
        sommets2.add(b2);
        sommets2.add(c2);
        sommets2.add(d2);
        Graphe g2 = new Graphe(sommets2);
        g2.colorier(k);
        System.out.println(g2.toString());
        System.out.println(g2.toStringSpill());

        System.out.println("\n\n\n============== n°3 ==============");
        System.out.println("Test n°3 : \n sommets {u,v,w,x,y,z} \n arètes {uy,ux,vz,vx,vw,wy,yx} \n préference : u avec w");
        System.out.println("Test la coloration par préférences");
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
        ArrayList<Sommet> sommets3 = new ArrayList<Sommet>();
        sommets3.add(u);
        sommets3.add(v);
        sommets3.add(w);
        sommets3.add(x);
        sommets3.add(y);
        sommets3.add(z);
        Graphe g3 = new Graphe(sommets3);
        g3.colorier(k);
        System.out.println(g3.toString());
        System.out.println(g3.toStringSpill());
    }

}
