package partie1;

public class Forfait extends Compte {
    Compte decoré;

    Forfait(Compte c){
        this.decoré = c;
    }

    public double prixLocation(Produit p){
        return decoré.prixLocation(p);
    }
}
