package partie1;

public class CompteAvecSeuil extends Forfait{

    static int init = 2;
    int compteur = init;

    CompteAvecSeuil(Compte c){super(c);}

    public double prixLocation(Produit p){
        if(compteur-- == 0){
            compteur = init;
            return 0.0;
        }
        else
            return super.prixLocation(p);
    }

}
