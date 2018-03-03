import java.util.Scanner;

/**
 * Created by Wass on 29/09/2017.
 */
public class Exo1 {


    public static void main (String arg[]){

        System.out.println("Entrer votre entier");
        Scanner sc= new Scanner(System.in);
//pour éviter les exception InputMismatch ou autre 
        try {
                int i = sc.nextInt();
                if(i%2==0&&i>0){
                         System.out.println("votre nombre est pair positif");
                }else if(i%2==0&&i<0){
                            System.out.println("votre nombre est pair negative");            }
                else if(i%2!=0&&i>0){
                    System.out.println("votre nombre est impair positif");
                }else{
                    System.out.println("votre nombre est impair negative");
                }
             }


        catch(Exception e){
            System.out.println("Vous n'avez pas entrer un entier");
        }




    }




}
