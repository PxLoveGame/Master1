package partie2;

public class Main {
    public static void main(String[] args){
        Produit lgv = new Produit(10.0,"La grande vadrouille");
        Client c1 = new Client("Dupont");
        Client c2 = new Client("Chaillot");
        Compte cpt1 = new CompteNormal(c1);
        cpt1 = new Reduction(cpt1);
        cpt1 = new Seuil(cpt1);
        System.out.println("Seuil + Reduction : "+ cpt1.prixLocation(lgv));

        Compte cpt2 = new CompteNormal(c2);
        cpt2 = new Reduction(cpt2);
        System.out.println("Réduction : "+ cpt2.prixLocation(lgv));

    }
}
