// une commande concrète.
public class TransvaserBidon extends Commande {


    private Bidon src;
    private Bidon dest;

    public TransvaserBidon(Bidon b1, Bidon b2){
        src = b1;
        dest = b2;
    }

    @Override
    public void execute() {
        setVolumeDeplace(src.transvaser(dest));
    }

    @Override
    public void cancel() {
        src.setVolume(src.getVolume() + getVolumeDeplace());
        dest.setVolume(dest.getVolume() - getVolumeDeplace());
    }
}
