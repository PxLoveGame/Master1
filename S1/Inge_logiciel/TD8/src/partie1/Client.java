package partie1;

public class Client {

    protected String nom;
    protected Compte cpt;

    public void setCpt(Compte c){this.cpt = cpt;}
    public Compte getCpt(){return this.cpt;}

    Client(String n){
        this.nom = n;
    }

    Client(String n, Compte c){
        this.nom = n;
        this.cpt = c;
    }
}
