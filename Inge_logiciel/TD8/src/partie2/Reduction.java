package partie2;

public class Reduction extends DecorateurForfait {

    private final double reduction = 0.10;

    public Reduction(Compte c){
        compte = c;
    }

    @Override
    public double prixLocation(Produit p) {
        return compte.prixLocation(p) * (1 - reduction);
    }
}
