/**
 * Created by Wass on 29/09/2017.
 */
public class SousMarin extends Drone {


    public SousMarin(Position p, int vitess, int autonomie, Position station) {
        super(p, 5, autonomie, station);
    }
    public SousMarin(Position p, int autonomie) {
        super(p,5, autonomie,station);
    }

    @Override
    public double CapParcour(int autonomie) {
        return ((autonomie*1500)/100);
    }
    public String toString(){
        return "SousMarin";
    }
}
