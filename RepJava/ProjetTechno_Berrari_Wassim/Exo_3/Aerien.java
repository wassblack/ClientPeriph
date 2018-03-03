/**
 * Created by Wass on 29/09/2017.
 */
public class Aerien extends Drone {



    public Aerien(Position p, int vitess, int autonomie, Position station) {
        super(p, 10, autonomie, station);
    }
    public Aerien(Position p ,int autonmie){

        super(p,10,autonmie,station);
    }

    @Override
    public double CapParcour(int autonomie) {
        return ((autonomie*10000)/100);

    }
    public String toString(){
        return "Aerien";
    }
}
