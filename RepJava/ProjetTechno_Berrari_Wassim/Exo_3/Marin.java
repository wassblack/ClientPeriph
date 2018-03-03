/**
 * Created by Wass on 29/09/2017.
 */
public class Marin extends Drone {
    public Marin(Position p, int vitess, int autonomie, Position station) {
        super(p, 2, autonomie, station);
    }
    public Marin(Position p, int autonomie) {
        super(p, 2, autonomie, station);
    }

    @Override
    public double CapParcour(int autonomie) {
        return ((autonomie*5000)/100);
    }
    public String toString(){
        return "Marin";
    }
}
