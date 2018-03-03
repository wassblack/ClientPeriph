import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Wass on 29/09/2017.
 */
public class Exo2 {
    void echanger(int tab[], int x, int y)
    {
        int tmp;

        tmp = tab[x];
        tab[x] = tab[y];
        tab[y] = tmp;
    }

    int max(int tab[], int taille)
    {
        // on considère que le plus grand élément est le premier
        int i=0, indice_max=0;

        while(i < taille)
        {
            if(tab[i] > tab[indice_max])
                indice_max = i;
            i++;
        }

        return indice_max;
    }
    void tri_selection(int tab[], int taille)
    {
        int indice_max;

        // à chaque tour de boucle, on va déplacer le plus grand élément
        // vers la fin du tableau, on diminue donc à chaque fois sa taille
        // car le dernier élément est obligatoirement correctement
        // placé (et n'a donc plus besoin d'être parcouru/déplacé)

        for(; taille > 1 ; taille--) // tant qu'il reste des éléments non triés
        {
            indice_max = max(tab, taille);

            echanger(tab, taille-1, indice_max); // on échange le dernier élément avec le plus grand
        }
    }

    public static void main (String arg[]){
        Exo2 e=new Exo2();
        Scanner sc= new Scanner(System.in);
        int count;
        int i=0;
        System.out.println("combien d'element voulez vous entrer");

        count=sc.nextInt();
        int tab[]=new int[count];
        //Remplissage du tab
        for(i=0;i<count;i++){
            System.out.println("Entrez votre element");
            tab[i]=sc.nextInt();
        }

            int indice_max=0;int taille=tab.length;
        //trie du tableau
        for(; taille > 1 ; taille--) // tant qu'il reste des éléments non triés
        {
            indice_max = e.max(tab, taille);

            e.echanger(tab, taille-1, indice_max); // on échange le dernier élément avec le plus grand
        }

        //affichage du tab
        System.out.println("le tableau apres le trie");

                for(i=0;i<count;i++) {
                        System.out.print("| "+tab[i]+" | ");
                    }


    }





}
