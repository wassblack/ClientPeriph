

/**
 * Created by Wass on 29/09/2017.
 */
public class TestDrone {
    public static void main (String arg[])
    {
        Drone []drone=new Drone[5];
        // creation des drones  et affectation dans le tab
        Position pa=new Position(-400,800,150);
        Aerien a=new Aerien(pa,78);
        drone[0]=a;
        Position pb=new Position(160,-230,420);
        Aerien b=new Aerien(pb,22);
        drone[1]=b;
        Position pm=new Position(442,242,0);
        Marin ma=new Marin(pm,50);
        drone[2]=ma;
        Position psma=new Position(-112,358,-132);
        SousMarin sma=new SousMarin(psma,47);
        drone[3]=sma;
        Position psmb=new Position(134,558,-914);
        SousMarin smb=new SousMarin(psma,93);
        drone[4]=smb;

        for(int i=0;i<drone.length;i++){

            /* test pour savoir si le drone peut ou pas se rendre sur la station si oui il calcule le temp d'arrivee
            */
            if(drone[i].getP().Distance(a.getStation())<drone[i].CapParcour(drone[i].getAutonomie())){
                
                System.out.println(drone[i].toString()+"  "+i+ " peut se rendre a la station en "+drone[i].CapParcour(drone[i].getAutonomie())/drone[i].getVitess()+" seconde");
            }   else     {
                    System.out.println(drone[i].toString()+"  "+i+ " ne peut pas se rendre a la station");
            }
        }

    }

}
