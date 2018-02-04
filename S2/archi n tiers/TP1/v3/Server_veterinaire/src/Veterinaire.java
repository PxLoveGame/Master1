import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Veterinaire extends UnicastRemoteObject implements  IVeterinaire {


    public Veterinaire () throws RemoteException {

    }

    @Override
    public void notify(String a) throws RemoteException {
        System.out.println("Message : " + a);
    }
}
