package partie2;

public abstract class DecorateurForfait extends Compte {

    protected Compte compte;

    public abstract double prixLocation(Produit p);
}
