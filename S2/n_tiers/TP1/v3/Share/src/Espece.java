
import java.io.Serializable;
import java.rmi.RemoteException;

public class Espece implements IEspece, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nom;
	private int esperence;
	
	public Espece(String nom, int esperence) throws RemoteException
	{
		this.nom = nom;
		this.esperence = esperence;
	}
	
	@Override
	public String toString()
	{
		StringBuilder affichage = new StringBuilder();
		
		affichage.append("Espece");
		affichage.append(System.getProperty("line.separator"));
		affichage.append("{");
	
		affichage.append("\t");
		affichage.append("Espece :");
		affichage.append(nom);
		affichage.append(System.getProperty("line.separator"));
		
		affichage.append("\t");
		affichage.append("Durée de vie :");
		affichage.append(esperence);
		affichage.append(System.getProperty("line.separator"));
		affichage.append("}");
		affichage.append(System.getProperty("line.separator"));
		
		return affichage.toString();
	}
	
	@Override	
	public String getInfos() throws RemoteException
	{
		return toString();
	}
}