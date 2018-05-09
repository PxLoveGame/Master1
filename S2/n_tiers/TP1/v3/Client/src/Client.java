import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client
{
	private static final String DEFAULT_REGISTRY_KEY = "veterinary_practice";

	public static void main(String [] args)
	{	
		/*
		System.setProperty("java.security.policy", "file:D:\\workspace\\RMI\\TP1Client\\client.policy");
		System.setProperty("java.rmi.server.codebase","file:D:\\workspace\\RMI\\TP1Server\\bin\\");
	
		System.setSecurityManager(new SecurityManager());
		*/
		
		Scanner t_keyboard = new Scanner(System.in);
		
		try
		{
			System.out.println("[Connection...]");
			
			Registry t_registry = LocateRegistry.getRegistry(1100);
			
			System.out.println("[Connected]");

			System.out.println("[Get VeterinaryPractice]");	
			ICabinet cabinet = (ICabinet) t_registry.lookup(DEFAULT_REGISTRY_KEY);
			System.out.println("[VeterinaryPractice getted]");
			
			System.out.println("[Add veterinary]");
			IVeterinaire t_veterinary = new Veterinaire("Toto");
			cabinet.addVeterinaire(t_veterinary);
			System.out.println("[Veterinary added]");
			
			System.out.println("[Add animal]");
			System.out.print("Animal name : ");
			String t_animal_name =t_keyboard.nextLine();
			System.out.println("");
			cabinet.addAnimal(new Animal(t_animal_name, "Jean-Kevin", "Pokemon", new Espece("Eau", 42), new Dossier("Ok.")));	
			System.out.println("[Animal added]");
			
			System.out.println("[Get animal]");		
			IAnimal t_animal = cabinet.getAnimal(t_animal_name);	
			System.out.println(t_animal.getInfos());	
			System.out.println("[Animal getted]");

			System.out.println("[Add animal with SubBreed (Client only)]");
			System.out.print("Animal name : ");
			t_animal_name =t_keyboard.nextLine();
			System.out.println("");
			cabinet.addAnimal(new Animal(t_animal_name, "Jean-Kevin", "Pokemon", new SousEspece("Eau", 42), new Dossier("Ok.")));
			System.out.println("[Animal added]");
			
			System.out.println("Please, press enter to continue");
			t_keyboard.nextLine();
			t_keyboard.close();
		}	
		catch(Exception t_exception)
		{
			t_exception.printStackTrace();
		}
	}
}