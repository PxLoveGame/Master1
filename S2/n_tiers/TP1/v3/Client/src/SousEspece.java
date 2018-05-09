import java.io.Serializable;
import java.rmi.RemoteException;

public class SousEspece extends Espece implements ISousEspece, Serializable
{
	private static final long serialVersionUID = 1L;

	public SousEspece(String nom, int esperence) throws RemoteException
	{
		super(nom, esperence);
	}
	
	@Override
	public void Foo()
	{
		System.out.println("Foo.Foo = Foo0");
	}
	
	@Override
	public void Bar()
	{
		System.out.println("Bar.Bar = Barbis.bar.barbis.0");
	}
}