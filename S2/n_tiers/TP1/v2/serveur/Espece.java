package v2.serveur;

import java.io.Serializable;
import java.util.Random;

public class Espece implements Serializable{

    private String name;
    private int lifetime;

    public Espece (String n){
        this.name = n;
        Random r = new Random();
        this.lifetime = r.nextInt(100);
    }

    public String toString(){
        return " \n     nom : "+ this.name + " \n     Durée de vie : " + this.lifetime;
    }
}
