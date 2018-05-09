
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Veterinaire extends UnicastRemoteObject implements IVeterinaire
{
	private static final long serialVersionUID = 1L;
	
	public Veterinaire(String nom) throws RemoteException
	{
	}
	
	@Override
	public void alert(String alert) throws RemoteException
	{
		System.out.println("[ALERT] : " + alert);
	}
}