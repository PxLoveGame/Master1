
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
		StringBuilder affichage = new StringBuilder();
		
		affichage.append("Animal");
		affichage.append(System.getProperty("line.separator"));
		affichage.append("{");			
		affichage.append(System.getProperty("line.separator"));
		affichage.append("Nom  : ");
		affichage.append(nom);
		affichage.append(System.getProperty("line.separator"));
		affichage.append("Maitre : ");
		affichage.append(maitre);
		affichage.append(System.getProperty("line.separator"));
		affichage.append("Race  : ");
		affichage.append(race);
		affichage.append(System.getProperty("line.separator"));
		affichage.append(espece.toString());
		affichage.append(dossier.toString());
		affichage.append("}");	
		affichage.append(System.getProperty("line.separator"));
		
		return affichage.toString();
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

