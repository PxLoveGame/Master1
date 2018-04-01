import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Cabinet extends UnicastRemoteObject implements ICabinet {

    protected ArrayList<IAnimal> patients = new ArrayList<>();
    protected ArrayList<IVeterinaire> veterinaires = new ArrayList<>();

    public Cabinet() throws RemoteException {
        try{
            patients.add(new Animal("Bobo", "Frank", new Espece("Singe"), "difficulté a respirer"));
            patients.add(new Animal("Chen", "Théo", new Espece("Chien"), "difficulté a marcher"));
            patients.add(new Animal("Cathy", "Monique",new Espece("Chat"), "difficulté a voir"));
        } catch(RemoteException e) {
            e.printStackTrace();
        }

    }

    public void addAnimal(IAnimal animal) throws RemoteException{

        animal.printInfos();
        this.patients.add(animal);

        for(IVeterinaire veterinaire : this.veterinaires){
            veterinaire.notify("Nouvel animal dans le cabinet : " + animal.returnInfos());
        }
    }

    public void removeAnimal(IAnimal animal) throws RemoteException{
        animal.printInfos();
        this.patients.remove(animal);

        for(IVeterinaire veterinaire : this.veterinaires){
            veterinaire.notify(animal.getName() + " Quitte le cabinet ! ");
        }
    }

    public void addVeterinaire(IVeterinaire veterinaire) throws RemoteException {
        this.veterinaires.add(veterinaire);
    }

    public void removeVeterinaire(IVeterinaire veterinaire) throws RemoteException{
        this.veterinaires.remove(veterinaire);
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
