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
		StringBuilder affichage = new StringBuilder();
		
		affichage.append("Dossier");	
		affichage.append(System.getProperty("line.separator"));
		affichage.append("{");
		affichage.append(System.getProperty("line.separator"));
		affichage.append("\t");
		affichage.append(etat);
		affichage.append(System.getProperty("line.separator"));
		affichage.append("}");
		affichage.append(System.getProperty("line.separator"));
		
		return affichage.toString();
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