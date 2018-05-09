import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur
{
	private static final String JAVA_PROPERTY_POLICY = "java.security.policy";
	private static final String JAVA_SECURITY_POLICY_SERVER = "..\\server.policy";
		
	public static void main(String args[])
	{
		
		try
		{
			System.setProperty(JAVA_PROPERTY_POLICY, JAVA_SECURITY_POLICY_SERVER);
						
			System.setSecurityManager(new SecurityManager());
		
			Registry registry = LocateRegistry.createRegistry(1100);

			System.out.println("Registry créé");
			
			if(registry == null)
			{
				System.err.println("Registry introuvable");
				
				return;
			}
			
			ICabinet cabinet = new Cabinet();
			
			registry.rebind("Cabinet", cabinet);
			
			System.out.println("Registry bind");
			
		}
		catch(Exception t_exception)
		{
			System.err.println("Error : " + t_exception.toString());
			t_exception.printStackTrace();
		}
	}
}