import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Dossier extends UnicastRemoteObject implements IDossier {

    private String contenu = "";
    private String currentState;

    public Dossier(String c) throws RemoteException{
        this.contenu = c;
        this.currentState = "New";
    }

    public void setContent(String c){
        this.contenu = c;
    }

    public String toString(){
        return "Etat du dossier : " + this.currentState + "\n contenu : " + this.contenu;
    }

    @Override
    public void setState(String newState) throws RemoteException {
        this.currentState = newState;
    }

    @Override
    public String getState() throws RemoteException {
        return this.currentState;
    }
}
