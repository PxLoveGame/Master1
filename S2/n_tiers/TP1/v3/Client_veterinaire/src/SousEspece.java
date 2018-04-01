import java.rmi.RemoteException;

public class SousEspece extends Espece implements ISousEspece {

    public SousEspece(String name) throws RemoteException
    {
        super(name);
    }
}
