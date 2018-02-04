public abstract class Commande implements ICommande {

    private int volumeDeplace;

    public int getVolumeDeplace(){
        return volumeDeplace;
    }

    public void setVolumeDeplace(int vd){
        volumeDeplace = vd;
    }


}
