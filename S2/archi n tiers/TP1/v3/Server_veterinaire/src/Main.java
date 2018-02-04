import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

    public static void main(String[] args){
        System.out.println("Lancement du serveur...");

        try{

            System.setProperty("java.security.policy", "server_policy");
            System.setProperty("java.rmi.codebase","/auto_home/pxl/Workspace/Master1");

            if(System.getSecurityManager() == null){
                System.out.println("Mise en place du Security Manager ...");
                System.setSecurityManager(new SecurityManager());
            }

            Registry registry = LocateRegistry.createRegistry(1200);
            if(registry == null) {
                System.err.println("Registry not found");
            } else {
                ICabinet cabinet = new Cabinet();
                registry.rebind("cabinet", cabinet);
                System.out.println("Registry binded");
            }


            System.out.println("Serveur lancé !");

        } catch (RemoteException e){
            e.printStackTrace();
        }
    }
}
