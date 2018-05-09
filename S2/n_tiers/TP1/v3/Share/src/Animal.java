
import java.io.Serializable;
import java.rmi.RemoteException;

public class Animal implements IAnimal, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private String maitre;
	private String race;
	
	private IEspece espece;
	
	private IDossier dossier;
	
	
	public Animal(String nom, String maitre, String race, IEspece espece, IDossier dossier) throws RemoteException
	{
		this.nom = nom;
		this.maitre = maitre;
		this.race = race;
		
		this.espece = espece;
		
		this.dossier = dossier;
	}
	
	@Override
	public String toString()
	{
		StringBuilder r_to_string = new StringBuilder();
		
		r_to_string.append("Animal");
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("{");			
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("Name  : ");
		r_to_string.append(nom);
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("Master : ");
		r_to_string.append(maitre);
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("Race  : ");
		r_to_string.append(race);
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append(espece.toString());
		r_to_string.append(dossier.toString());
		r_to_string.append("}");	
		r_to_string.append(System.getProperty("line.separator"));
		
		return r_to_string.toString();
	}
	
	public String getNom() throws RemoteException
	{
		return nom;
	}

	public String getInfos() throws RemoteException
	{
		return toString();
	}

	@Override
	public IEspece getEspece() throws RemoteException
	{
		return espece;
	}
	
	@Override
	public IDossier getDossier() throws RemoteException
	{
		return dossier;
	}
}

