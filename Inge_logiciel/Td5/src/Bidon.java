public class Bidon {

    private int volume;
    private int volumeMax;

    public Bidon(int vMax){
        this.volumeMax = vMax;
    }

    public int getVolume(){
        return volume;
    }

    public void setVolume(int v){
        volume = v;
    }

    public int getVolumeMax(){return volumeMax;}

    public void vider(){
        setVolume(0);
    }

    public void remplir(){
        setVolume(volumeMax);
    }

    public int transvaser(Bidon b){
        int volumeDeplace;
        int sommeVolume = this.getVolume() + b.getVolume();

        if(sommeVolume <= b.getVolumeMax()){
            volumeDeplace = this.getVolume();
            b.setVolume(sommeVolume);
            this.vider();
        }
        else{
            volumeDeplace = b.getVolumeMax() - b.getVolume();
            b.setVolume(b.getVolumeMax());
            this.setVolume(this.getVolume() - volumeDeplace);
        }
        return volumeDeplace;
    }
}
