import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote{

    void printInfos() throws RemoteException;
    String returnInfos() throws  RemoteException;
    IEspece getSpecies() throws  RemoteException;
    String getName() throws  RemoteException;
}
