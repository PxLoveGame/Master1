package v2.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CabinetMedical extends UnicastRemoteObject implements ICabinet {

    protected ArrayList<Animal> patients = new ArrayList<>();

    public CabinetMedical() throws RemoteException{
        try{
            patients.add(new Animal("Bobo", "Frank", "Bonobo", "difficulté a respirer"));
            patients.add(new Animal("Chen", "Théo", "Chien", "difficulté a marcher"));
            patients.add(new Animal("Cathy", "Monique", "Chat", "difficulté a voir"));
        } catch(RemoteException e) {
            e.printStackTrace();
        }

    }


    @Override
    public IAnimal findAnimal(String name) throws RemoteException{
        for (IAnimal a : patients){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }
}
