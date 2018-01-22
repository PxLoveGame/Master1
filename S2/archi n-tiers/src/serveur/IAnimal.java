package serveur;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {

    void printInfos() throws RemoteException;
    String returnInfos() throws  RemoteException;
    Espece getSpecies() throws  RemoteException;
}
