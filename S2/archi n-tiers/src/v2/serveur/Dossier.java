package v2.serveur;

public class Dossier {

    private String contenu = "";

    public Dossier(String c){
            this.contenu = c;
    }

    public String toString(){
        return this.contenu;
    }
}
