/**
 * Created by Wass on 29/09/2017.
 */
public class Position {
    int positionX;
    int positionY;
    int positionZ;

    public Position(int positionX, int positionY, int positionZ) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
    }
    public double Distance(Position p){
        double d=Math.sqrt(Math.pow(this.positionX-p.positionX,2)+Math.pow(this.positionY-p.positionY,2)+Math.pow(this.positionZ-p.positionZ,2));
        return d;
    }
}
