import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("Lancement du client");
        if (System.getSecurityManager() == null) {
            System.setProperty("java.security.server.policy", "client.policy");
            System.setSecurityManager(new SecurityManager());
        }

        try{

            Registry registry = LocateRegistry.createRegistry(1200);
            IVeterinaire veterinaire = new Veterinaire();
            ICabinet cabinet = (ICabinet) registry.lookup("cabinet");

            cabinet.addVeterinaire(veterinaire);


            IAnimal a1 = new Animal("Kirikou", "Paul", new SousEspece("Chien") , "Problème à la pate");

            Scanner sc = new Scanner(System.in);
            boolean stop = false;

            while(!stop){
                System.out.println("Vous pouvez ajouter (add) ou retirer un animal (remove)");

                switch(sc.nextLine()) {
                    case "add" :
                            cabinet.addAnimal(a1);
                            break;
                    case "remove" :
                            cabinet.removeAnimal(a1);
                            break;
                    case "stop" :
                            sc.close();
                            stop = true;
                            break;
                    default :
                        System.out.println("> nouvelle saisie <");
                }
            }


        } catch (RemoteException e){
            e.printStackTrace();
        } catch (NotBoundException e){
            e.printStackTrace();
        }
        System.out.println("Fin du client");
    }
}
