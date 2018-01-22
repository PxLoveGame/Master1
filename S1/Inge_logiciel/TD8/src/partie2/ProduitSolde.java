package partie2;

public class ProduitSolde extends Produit {

    ProduitSolde(double p, String t){
        super(p,t);
    }

    public double prixLocation(){
        return (super.prixLocation() / 2);
    }
}
