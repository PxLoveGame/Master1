import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote
{
	public String getInfos() throws RemoteException;
	
	public String getNom() throws RemoteException;
	
	public IEspece getEspece() throws RemoteException;
	
	public IDossier getDossier() throws RemoteException;
}