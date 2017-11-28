package partie2;

public class Seuil extends DecorateurForfait {

    private final int init = 2;
    private int compteur = init;

    public Seuil(Compte c){
        compte = c;
    }

    @Override
    public double prixLocation(Produit p) {
        if(compteur == 0){
            compteur = init;
            return 0.0;
        }
        else
            compteur--;
            return compte.prixLocation(p);
    }
}
