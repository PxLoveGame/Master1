package partie1;

public class CompteAvecReduction extends Forfait {

    double reduction = 0.10;

    CompteAvecReduction(Compte c){super(c);}

    public double prixLocation(Produit p){
        return (super.prixLocation(p) * (1 - reduction));
    }
}
