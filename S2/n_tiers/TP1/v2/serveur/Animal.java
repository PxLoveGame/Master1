package v2.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Animal extends UnicastRemoteObject implements IAnimal {

    protected String name = "";
    protected String master = "";
    protected Espece species;
    protected Dossier dossier;

    protected Animal(String n, String m, String s, String d) throws RemoteException {
        this.name = n;
        this.master = m;
        this.species = new Espece(s);
        this.dossier = new Dossier(d);
    }

    public String toString(){
        return "l'animal  " + this.name +
                " \n de l'espèce : " + this.species +
                " \n appartient à  " + this.master +
                " \n dossier médical : " + this.dossier;
    }

    @Override
    public void printInfos() throws RemoteException {
        System.out.println(this.toString());
    }

    @Override
    public String returnInfos() throws RemoteException {
        return this.toString();
    }

    public Espece getSpecies() throws RemoteException {
        return species;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }


}
