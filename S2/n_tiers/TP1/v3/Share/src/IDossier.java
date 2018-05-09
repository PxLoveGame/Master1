import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDossier extends Remote
{
	public void setState(String newState) throws RemoteException;
	
	public String getState() throws RemoteException;
}