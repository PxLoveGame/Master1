import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVeterinaire extends Remote
{
	public void alert(String alert)  throws RemoteException;;
}