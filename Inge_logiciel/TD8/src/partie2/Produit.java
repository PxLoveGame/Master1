package partie2;

public class Produit {

    private double prix;
    private final double TVA = 19.6;
    private final double marge = 1.10;
    String titre;

    public Produit(double p, String t){
        this.prix = p;
        this.titre = t;
    }

    protected double prixHT(){
        return prix * marge;
    }

    public double prixVente(){
        return (this.prixHT() * (1 + TVA));
    }

    public double prixLocation(){
        return (this.prixVente() * (5 / 100));
    }

}
