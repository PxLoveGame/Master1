import java.io.Serializable;
import java.rmi.RemoteException;

public class Dossier implements IDossier, Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String etat;
	
	public Dossier(String etat) throws RemoteException
	{
		this.etat = etat;
	}
	
	@Override
	public String toString()
	{
		StringBuilder r_to_string = new StringBuilder();
		
		r_to_string.append("Medical file");	
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("{");
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("\t");
		r_to_string.append(etat);
		r_to_string.append(System.getProperty("line.separator"));
		r_to_string.append("}");
		r_to_string.append(System.getProperty("line.separator"));
		
		return r_to_string.toString();
	}

	@Override
	public void setState(String newEtat) throws RemoteException
	{
		etat = newEtat;
	}

	@Override
	public String getState() throws RemoteException
	{
		return etat;
	}
}