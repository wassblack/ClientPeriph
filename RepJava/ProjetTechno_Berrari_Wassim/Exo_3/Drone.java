/**
 * Created by Wass on 29/09/2017.
 */
public class Drone {
    private Position p;
    private int vitess;
    private int autonomie;
    final static Position station=new Position(0,0,0);

    public Drone(){

    }
    public Drone(Position p, int vitess, int autonomie, Position station) {
        this.p = p;
        this.vitess = vitess;
        this.autonomie = autonomie;

    }
    public double CapParcour(int autonomie){
        return 0;
    }

    public Position getP() {
        return p;
    }

    public void setP(Position p) {
        this.p = p;
    }

    public int getVitess() {
        return vitess;
    }

    public void setVitess(int vitess) {
        this.vitess = vitess;
    }

    public int getAutonomie() {
        return autonomie;
    }

    public void setAutonomie(int autonomie) {
        this.autonomie = autonomie;
    }

    public static Position getStation() {
        return station;
    }
}
