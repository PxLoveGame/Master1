import java.util.Stack;

// Le recepteur
public class Lanceur {

    private Stack<ICommande> history;

    public Lanceur(){
        this.history = new Stack<ICommande>();
    }

    public void pushCommande(ICommande c){
        c.execute();
        history.push(c);
    }

    public void popLastCommande(){
        history.lastElement().cancel();
        history.pop();
    }

    public static String etatBidon(Bidon b1, Bidon b2, Bidon b3){
        return " Volume de b1 : "+ b1.getVolume() + "\n Volume de b2 : " + b2.getVolume() + "\n Volume de b3 : " + b3.getVolume() + "\n";
    }

    public static void main(String[] args){

        Lanceur lanceur = new Lanceur();

        Bidon b1 = new Bidon(5);
        Bidon b2 = new Bidon(6);
        Bidon b3 = new Bidon(2);

        lanceur.pushCommande(new RemplirBidon(b1));
        lanceur.pushCommande(new RemplirBidon(b3));

        System.out.println(etatBidon(b1,b2,b3));

        lanceur.pushCommande(new TransvaserBidon(b1,b2));
        lanceur.pushCommande(new TransvaserBidon(b3,b2));

        System.out.println(etatBidon(b1,b2,b3));

        lanceur.popLastCommande();

        System.out.println(etatBidon(b1,b2,b3));

        lanceur.pushCommande(new ViderBidon(b2));

        System.out.println(etatBidon(b1,b2,b3));

    }


}
