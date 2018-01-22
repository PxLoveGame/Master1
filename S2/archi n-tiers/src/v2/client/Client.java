package v2.client;

import v2.serveur.IAnimal;
import v2.serveur.ICabinet;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args){
        try{
            Registry registry = LocateRegistry.getRegistry(1099);
            ICabinet obj = (ICabinet) registry.lookup("Cabinet1");

            IAnimal animal = (IAnimal) obj.findAnimal("Bobo");
            System.out.println(animal.returnInfos());

        } catch (RemoteException e){
            e.printStackTrace();
        } catch (NotBoundException e){
            e.printStackTrace();
        }
    }
}
