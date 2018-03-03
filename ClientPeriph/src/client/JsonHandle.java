package client;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.util.Iterator;


public class JsonHandle {


    public JsonHandle() {
    }

/*
* Verifie si une chaine de caracteres est un objet JSON
* Renvoi true si la conversion est possible false sinon
*
* */
    public static boolean isValideResponseJSON(String message){
        try{
            /*
            * Conversion du Json avec Parseur
            * */
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(message);
            return true;
        }catch (Exception e){
            System.out.println("message non valide :"+ message);
            return false;
        }
    }
/*
* Convertie si possinle une chaine de caracteres en objet JSON
* Leve une Exception si conversion impossible
* */

    public static JSONObject toJSON(String message){
        JSONObject jsonMessage;
        try {
            if (isValideResponseJSON(message)) {
                JSONParser parser = new JSONParser();
                jsonMessage = (JSONObject) parser.parse(message);
                return jsonMessage;
            } else {
                return null;
            }
        }catch(Exception e){
            e.printStackTrace();
            return  null;
        }
    }
/*
* Verifie dans un tableau donnée si un objet est contenue ou non
* si objet est contenue dans la liste la fonction renvoi son indice dans le tableau
* sinon la valeur retourner est -1
* */
   public static int checkValue(JSONArray array,String keySearch){
       Iterator iterator= array.iterator();
       int counter=0;
       while (iterator.hasNext()){
           JSONObject jsonObject= (JSONObject) array.get(counter);
           if (jsonObject.get("name").equals(keySearch)){
               return counter;
           }
           counter++;
           iterator.next();

       }
       return -1;
    }
/*
* Ajoute ou remplace un Objet Json au tableau passee en paramtere
* la position d'ajout est celle du l'ancienne valeur du meme objet
* si il existait deja longeur+1 sinon
*
* */
    public static boolean addOrReplace(JSONArray jsonValues,JSONObject messageJson){
       try {

           if (checkValue(jsonValues,messageJson.get("name").toString()) != -1) {
               jsonValues.set(checkValue(jsonValues, messageJson.get("name").toString()), messageJson);
               return true;
           } else {
               jsonValues.add(messageJson);
               return true;
           }

       }catch (Exception e){
           e.printStackTrace();
           return false;
       }



    }


}



