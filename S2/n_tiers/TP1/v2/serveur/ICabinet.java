package v2.serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote{

    IAnimal findAnimal(String name) throws RemoteException;
}
