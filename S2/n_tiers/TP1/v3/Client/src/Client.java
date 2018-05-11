import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Client
{

	public static void main(String [] args)
	{	

		Scanner saisie = new Scanner(System.in);
		int choix;
		boolean stop = false;
		String nom;
	
		
		try
		{
			System.out.println("[Connection...]");
			
			Registry registry = LocateRegistry.getRegistry(1100);
			
			System.out.println("[Connecté]");

			System.out.println("[Recherche du Cabinet...]");	
			ICabinet cabinet = (ICabinet) registry.lookup("Cabinet");
			System.out.println("[Cabinet récupéré]");
			
			System.out.println("[Ajout d'un vétérinaire..]");
			IVeterinaire veterinaire = new Veterinaire("Anthony");
			cabinet.addVeterinaire(veterinaire);
			System.out.println("[Vétérinaire ajouté]");
			
			while(!stop){
				StringBuilder affichage = new StringBuilder();

				affichage.append("Que voulez vous faire : ");
				affichage.append(System.getProperty("line.separator"));
				affichage.append("1 - Ajouter un animal ");
				affichage.append(System.getProperty("line.separator"));
				affichage.append("2 - Retirer un animal ");
				affichage.append(System.getProperty("line.separator"));
				affichage.append("3 - Quitter ");

				System.out.println(affichage.toString());

				choix = Integer.parseInt(saisie.nextLine());

				switch(choix){
					case 1 : 
						System.out.println("[Ajouter un animal]");
						System.out.print("Veuillez saisir son nom : ");
						nom = saisie.nextLine();
						System.out.println("");
						cabinet.addAnimal(new Animal(nom, "Jean-Kevin", "Labrador", new Espece("Chien", 15), new Dossier("Ok.")));	
						System.out.println("[Animal ajouté]");

						System.out.println("[Récupération des informations le concernant...]");		
						IAnimal animal = cabinet.getAnimal(nom);	
						System.out.println(animal.getInfos());	


						System.out.println("[Ajouter un animal avec une sous Espece (Connu uniquement par le client)]");
						System.out.print("Veuillez saisir son nom : ");
						nom = saisie.nextLine();
						System.out.println("");
						cabinet.addAnimal(new Animal(nom, "Jean-Kevin", "Pouloulou", new SousEspece("Oiseau", 2500), new Dossier("Ok.")));
						System.out.println("[Animal ajouté]");

						System.out.println("[Récupération des informations le concernant...]");		
						animal = cabinet.getAnimal(nom);	
						System.out.println(animal.getInfos());
						break;

					case 2 : 
						System.out.println("[Retirer un animal]");
						System.out.print("Veuillez saisir son nom : ");
						nom = saisie.nextLine();
						System.out.println("");
						cabinet.removeAnimal(nom);	
						System.out.println("[Animal retiré]");
						break;

					case 3 :
						System.out.println("[Deconnexion...]");
						stop = true;
						System.out.println("[FIN]");
						break;
				}

			}

			saisie.close();
		}	
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}