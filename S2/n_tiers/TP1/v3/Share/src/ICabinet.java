import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICabinet extends Remote
{
	public IAnimal getAnimal(String nom) throws RemoteException;
	
	public void addAnimal(IAnimal animal) throws RemoteException;
	
	public void removeAnimal(String nom) throws RemoteException;
	
	public void addVeterinaire(IVeterinaire veterinaire) throws RemoteException;
}