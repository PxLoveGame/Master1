package v1.serveur;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur  {
    public static void main(String[] args){
        System.out.println("Serveur start");

        System.setProperty("java.security.policy", "server.policy");
        System.setSecurityManager(new RMISecurityManager());

        try{
            Animal obj = new Animal("Gigi", "Paul" , "Girafe", "Cou tordu");
            Registry registry = LocateRegistry.createRegistry(1099);

            if(registry == null){
                System.err.println("Registery not found ! ");
            }
            else {
                registry.rebind("Gigi", obj);
                System.out.println("Object binded ");
            }
        }
        catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
