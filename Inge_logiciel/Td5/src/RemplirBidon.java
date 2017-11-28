// une commande concrète.
public class RemplirBidon extends Commande {

    private Bidon bidon;

    public RemplirBidon(Bidon bd){
        bidon = bd;
    }
    @Override
    public void execute() {
        setVolumeDeplace(bidon.getVolumeMax() - bidon.getVolume());
        bidon.remplir();
    }

    @Override
    public void cancel() {
        bidon.setVolume(bidon.getVolume() - getVolumeDeplace());
    }
}
