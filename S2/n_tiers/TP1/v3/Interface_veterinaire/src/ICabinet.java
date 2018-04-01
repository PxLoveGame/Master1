import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote{

    void addAnimal(IAnimal animal) throws RemoteException;
    void removeAnimal(IAnimal animal) throws RemoteException;
    void addVeterinaire(IVeterinaire veterinaire) throws RemoteException;
    void removeVeterinaire(IVeterinaire veterinaire) throws RemoteException;
    IAnimal findAnimal(String name) throws RemoteException;
}
