package partie1;

public class CompteNormal extends Compte {

    private static int compteur = 0;
    int numero;
    protected Client client;

    CompteNormal(Client cl){
        this.client = cl;
        cl.setCpt(this);
        this.numero = compteur++;
    }


}
