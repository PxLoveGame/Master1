
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
		StringBuilder r_to_string = new StringBuilder();
		
		r_to_string.append("Breed");
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("{");
	
		r_to_string.append("\t");
		r_to_string.append("Breed :");
		r_to_string.append(nom);
		r_to_string.append(System.getProperty("line.separator"));
		
		r_to_string.append("\t");
		r_to_string.append("Life  :");
		r_to_string.append(esperence);
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("}");
		r_to_string.append(System.getProperty("line.separator"));
		
		return r_to_string.toString();
	}
	
	@Override	
	public String getInfos() throws RemoteException
	{
		return toString();
	}
}