import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVeterinaire extends Remote {

    public void notify(String a) throws RemoteException;
}
