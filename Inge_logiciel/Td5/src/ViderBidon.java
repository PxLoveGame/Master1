// une commande concrète.
public class ViderBidon extends Commande {

    private Bidon bidon;

    public ViderBidon(Bidon b){
        bidon = b;
    }

    @Override
    public void execute() {
        setVolumeDeplace(bidon.getVolume());
        bidon.vider();
    }

    @Override
    public void cancel() {
        bidon.setVolume(getVolumeDeplace());
    }
}
