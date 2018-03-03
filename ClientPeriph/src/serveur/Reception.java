package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public class Reception implements Runnable{

	private ArrayList<Socket> socketArray_ = null; //new ArrayList<Socket>();
	private LinkedList<JsonObject> jsonArray_ = null; //new LinkedList<JsonObject>();
	private static BufferedReader in_ = null;
	private static PrintWriter out_ = null;
	private String message_ = null;
	private int position_Socket;
	
	public Reception(BufferedReader in, PrintWriter out, LinkedList<JsonObject> json, int pos, String log, ArrayList<Socket> sockArray) {
		in_ = in;
		out_ = out;
		jsonArray_= json;
		position_Socket = pos;
		socketArray_ = sockArray;
	}
	
	public void run() {
		while(!socketArray_.isEmpty() && !socketArray_.get(position_Socket).isClosed()){ // Permet de parler sans cesse tant que la socket est ouverte
			try {
				message_ = in_.readLine();
				traiter(position_Socket, message_,jsonArray_, out_, in_ , socketArray_); // Repere a qui renvoyer les informations recu
				FichierLog.writeLog(message_); // enregistre tous qui transite sur le serveur SERIALISATION SI ON VEUT DES OBJETS
			} catch (IOException e) {
		    	e.printStackTrace();
		    }
		}
	}
	
	public void traiter(int pos, String msg, LinkedList<JsonObject> jsonArr, PrintWriter out, BufferedReader in, ArrayList<Socket> socketArray) {
		
		// Formatage de la chaine String en JSON
		JsonReader in_json = Json.createReader(new StringReader(msg));
		JsonObject objJson = in_json.readObject(); // On cree un objet depuis le Json recu
		in_json.close();
		
		String sayLogin = objJson.getString("name");
		System.out.println("position = "+pos+". taille de "+jsonArr.size());
		String realLogin = jsonArr.get(pos).getString("name");;
		
		out.println("Msg Recu"); // Au client qui a envoyé le msg
		out.flush();
		
		/* On s'assure bien que le client "gyroscope" parle en son nom.
		 * Sinon le client GPS pourrait se faire passé pour le gyroscope dans le JSON 
		 */
		if(realLogin.equals(sayLogin)) { // Verification que le CLIENT parle en son nom
			for(int i=0; i<jsonArr.size(); i++) { // On recherche si ce login existe et on agira en fonction
				if(sayLogin.equals(jsonArr.get(i).getString("name")) ) {// Si le DIT LOGIN existe deja alors on change
					if(objJson.getString("state").equals("disconnected")) {// PROCEDURE DE DECONNEXION
						out.println("Vous etes deconnecté");
						out.flush();
						System.out.println("> \""+realLogin+"\"Demande sa deconnexion !!");
						System.out.print("> \""+realLogin+"\" etat de connexion: ");
						System.out.println(socketArray.get(i).isClosed()?"fermé":"ouverte");
						jsonArr.remove(i);		// On supprime les données json enregistré.
						try {
							socketArray.get(i).close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.print("> \""+realLogin+"\" etat de connexion: ");
						System.out.println(socketArray.get(i).isClosed()?"fermé":"ouverte");
						System.out.println("> \""+realLogin+"\" est déconnecté !!");
						socketArray.remove(i); 	// On Coupe la connexion, en supprimant la socket lié au JSON.
					}
					else {// On met a jour les données du TABLEAU JSON
						
						jsonArr.remove(i);		// On supprime les données json enregistré pour ce peripherique.
						jsonArr.add(objJson);	// On ajoute la nouvelle donnée ecrit par le CLIENT dans le tableau JSON
						System.out.println("\""+realLogin+"\"["+i+"] dit: \t"+msg);
						
						/* On recherche les indices des sockets qui doivent recevoir les informations
						for(int i=0; i<sockArray.size(); i++) {
						  //Thread t4 = new Thread(new Emission(out_Receveur, msg));
						  //t4.start();
						}
						*/
						break; //QUAND ON SUPPRIME, PUIS RAJOUTE, ON RETRAITE LA MEME INFO D'OU LES DOUBLONS si je retire*/
					}
				}
				else { // Si ce login n'existe pas encore alors on l'ajoute 
					jsonArr.add(objJson);		// On ajoute la nouvelle donnée ecrit par le CLIENT dans le tableau JSON
					System.out.println("\""+realLogin+"\" dit: \t"+msg);
				}
			}// Fin FOR()
		}// Fin du S'il parle en son nom
		else {
			out.println("Veuillez recommencer, vous etes \""+realLogin+"\" et non: \""+sayLogin+"\"!!");
			out.flush();
		}
	}
}


/*
else if(jsonArr.get(1).getString("name").equals("gps")) {
	int position = -1;
	if(jsonArr.isEmpty()) {
		jsonArr.add(objJson);
		position = 0;
	}
	else {
		for(int i=0; i<jsonArr.size(); i++) {
			if(jsonArr.get(i).getString("name").equals("gps")) {
				position = i;// donne l'index a modifier
				jsonArr.add(i, Json.createReader(new StringReader(msg)).readObject());
			}
			else if(i == jsonArr.size()-1 && !jsonArr.get(i).getString("name").equals("gyroscope")){
				position = i;
				jsonArr.add(objJson);
			}
		}
	}
	System.out.println("AAAAAAAAAAA: "+jsonArr.get(position).getString("state"));
	System.out.println("AAAAAAAAAAA: "+jsonArr.get(position).getString("state"));
	if(jsonArr.get(position).getString("state").equals("disconnected")) {// PROCEDURE DE DECONNEXION
		out.println("Vous etes deconnecté");
		out.flush();
		System.out.println("> \""+login+"\" est deconnecté!!");
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		socketArray.remove(position); // On le supprime des ecoutes...
	}
	else {
		/* On recherche les indices des sockets qui doivent recevoir les informations
		for(int i=0; i<sockArray.size()-1; i++) {
		//Thread t4 = new Thread(new Emission(out_Rece, msg));
		//t4.start();
		}
		*/
	/*	System.out.println("\""+login+"\" dit: \t"+msg);
		System.out.println(jsonArr.get(position).getString("name")+"  A VOULU ENVOYER A PAUL");
	}
}
else if(jsonArr.get(2).getString("name").equals("capt_sons")) {
	for(int i=0; i<jsonArr.size(); i++) {
		if(jsonArr.get(i).getString("name").equals("capt_sons"))
			jsonArr.add(i, objJson);//return i;// donne l'index a modifier
		else
			jsonArr.add(objJson);
	}
	System.out.println(jsonArr.get(2).getString("name")+" A VOULU ENVOYER A GAEL");
	
}
else if(jsonArr.get(3).getString("name").equals("raspberry")) {
	for(int i=0; i<jsonArr.size(); i++) {
		if(jsonArr.get(i).getString("name").equals("raspberry"))
			jsonArr.add(i, objJson);//return i;// donne l'index a modifier
		else
			jsonArr.add(objJson);
	}
	System.out.println(jsonArr.get(3).getString("name")+" A VOULU ENVOYER A AUDIL");

}
else if(jsonArr.get(4).getString("name").equals("accelerometre")) {
	for(int i=0; i<jsonArr.size(); i++) {
		if(jsonArr.get(i).getString("name").equals("accelerometre"))
			jsonArr.add(i, objJson);//return i;// donne l'index a modifier
		else
			jsonArr.add(objJson);
	}
	System.out.println(jsonArr.get(4).getString("name") + " A VOULU ENVOYER A LUCIE");
}
*/