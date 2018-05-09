import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class Cabinet extends UnicastRemoteObject implements ICabinet
{
	private static final long serialVersionUID = 1L;
	
	private Map<String, IAnimal> animaux;
	private Collection<IVeterinaire> veterinaires;
	
	public Cabinet() throws RemoteException
	{
		animaux = new HashMap<String, IAnimal>();
		
		veterinaires = new ArrayList<IVeterinaire>();
	}
	
	@Override
	public void addAnimal(IAnimal newAnimal) throws RemoteException
	{
		System.out.println("Ajout d'un animal..");
		System.out.println(newAnimal.getInfos());
		
		animaux.put(newAnimal.getNom(), newAnimal);

		switch(animaux.size())
		{
			case 1 :
				for(IVeterinaire veterinaire : veterinaires)
					veterinaire.alert(System.getProperty("line.separator") + " population faible : " + animaux.size());
			break;
				
			case 2:
				for(IVeterinaire veterinaire : veterinaires)
					veterinaire.alert(System.getProperty("line.separator") + "  population moyenne " + animaux.size());
			break;
				
			case 3:
				for(IVeterinaire veterinaire : veterinaires)
					veterinaire.alert(System.getProperty("line.separator") + "  population forte " + animaux.size());
			break;
			
			default:
				break;
		}
	}
	
	@Override
	public void removeAnimal(String animal) throws RemoteException
	{
		System.out.println("Retrait d'un animal..");
		
		animaux.remove(animal).getNom();
		
		int seuil = 0;
		
		switch(animaux.size())
		{
			case 1 :
				seuil = 1;
				
				for(IVeterinaire veterinaire : veterinaires)
					veterinaire.alert(System.getProperty("line.separator") + " Seuil " + seuil);
			break;
				
			case 2:
				seuil = 2;
				
				for(IVeterinaire veterinaire : veterinaires)
					veterinaire.alert(System.getProperty("line.separator") + " Seuil " + seuil);
			break;
				
			case 3:
				seuil = 3;
				
				for(IVeterinaire veterinaire : veterinaires)
					veterinaire.alert(System.getProperty("line.separator") + " Seuil " + seuil);
			break;
		}
	}
	
	@Override
	public void addVeterinaire(IVeterinaire newVeterinaire) throws RemoteException
	{
		System.out.println("Ajout d'un vétérinaire..");
		
		veterinaires.add(newVeterinaire);
	}
	
	@Override
	public IAnimal getAnimal(String nom) throws RemoteException
	{	
		return animaux.get(nom);
	}

}

