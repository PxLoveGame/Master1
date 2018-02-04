package partie1;

public class Main {
    public static void main(String[] args){
        Produit lgv = new Produit(10.0,"La grande vadrouille");
        Client c1 = new Client("Dupont");
        Compte cpt1 = new CompteNormal(c1);

        Compte cpt2 = new CompteAvecSeuil(new CompteAvecReduction(new CompteNormal(c1)));
        System.out.println("Seuil + Réduction : " + cpt2.prixLocation(lgv));
        Produit r4 = new ProduitSolde(10.0, "RockyIV");
        System.out.println("Seuil + Réduction + Soldé : " + cpt2.prixLocation(r4));



    }
}
