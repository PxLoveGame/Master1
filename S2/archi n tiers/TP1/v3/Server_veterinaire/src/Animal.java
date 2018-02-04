import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Animal extends UnicastRemoteObject implements IAnimal {

    protected String name = "";
    protected String master = "";
    protected IEspece species;
    protected IDossier dossier;

    protected Animal(String n, String m, IEspece s, String d) throws RemoteException {
        this.name = n;
        this.master = m;
        this.species = s;
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

    public IEspece getSpecies() throws RemoteException {
        return species;
    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }
}
