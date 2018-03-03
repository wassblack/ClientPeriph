package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Authentification  implements Runnable {

	private ArrayList<Socket> socketArray = null; //[] = null; 	// Tableau de Socket contient une sorte de canal reliant Serv / Clt par indice
	private static LinkedList<JsonObject> jsonArray = new LinkedList<JsonObject>(); // Parce qu'il y aura suppression a chaque entrée
	private PrintWriter out_ = null; //[] = null;   // Ecriture des données dedans afin d'envoyer sur le canal du client approprié
	private BufferedReader in_ = null; //= null; // Lecture des données, qui sont envoyé depuis un client PRECIS, vers le serveur
	private String login_ = null;
	public boolean authentifier = false;
	public Thread t2;
	

	public Authentification(ArrayList<Socket> sockArray){
		socketArray = sockArray;
		login_ = new String();  // Specifiquement login associé a la socket
	}
	

	public void run() {
		try {
			/* Pour communiquer sur (la socket (canal) client-serveur) on utilise 
			 * - "out" pour (ecrire des données vers le Client) 
			 * - "in"  pour (lire des données depuis le Client)
			 */
			in_ = new BufferedReader(new InputStreamReader(socketArray.get(socketArray.size()-1).getInputStream())); // Lecture de tous que dit client X  
			out_ = new PrintWriter(socketArray.get(socketArray.size()-1).getOutputStream()); // Ecriture vers le client X
			
			System.out.println("PHASE 3 - \tENREGISTREMENT CLIENT.");
			out_.println("...Presentez vous svp.");
			out_.flush(); // envoi du msg immediat au client qui tente de s'identifier
			
			while(!authentifier){
				String tmp_json_lue = in_.readLine();// Code en Json envoye par le client, stoké en STRING.
				JsonReader in_json = Json.createReader(new StringReader(tmp_json_lue));
				JsonObject objJson = in_json.readObject(); // On cree un objet depuis le Json recu
				in_json.close();
				
				/* On mémorise les champs de l'objet JSON RECU du client X.
				 * On peut alors faire transiter des informations entres clients.
				 */
				if(objValid(objJson, jsonArray)) { // verifie que le protocol de chaque objet soit respecté
					login_ = objJson.getString("name");
					out_.println("connecte");
					System.out.println("> \""+login_+"\" vient de se connecter ");
					authentifier = true;
					jsonArray.add(objJson);	
				}
				else 
					out_.println("Echec d'authentification, vous ne respectez pas la convention du FRAMAPAD!!!!!");
				out_.flush();
			}
			
			// On lance le TCHAT entre peripheriques (clients)
			t2 = new Thread(new Chat_ClientServeur(socketArray, login_, jsonArray));
			t2.start();
		} catch (IOException e) {
			System.err.println("Ce client ne répond pas !");
		}
	}
	
	
	static boolean objValid(JsonObject o, LinkedList<JsonObject> jsonArr) {
		
		// il faut rajouter  if(o.getInt("data.. x y z").equals("..."))
		if(o.getString("state").equals("connected")) {
			if(o.getString("name").equals("gyroscope")) {
				for(int i = 0; i<jsonArr.size(); i++)
					if(jsonArr.get(i).getString("name").equals("gyroscope") && jsonArr.get(i).getInt("id") == o.getInt("id")) 
						return false;
				return true;
			}
			
			if(o.getString("name").equals("gps")) {
				for(int i = 0; i<jsonArr.size(); i++)
						if(jsonArr.get(i).getString("name").equals("gps") && jsonArr.get(i).getInt("id") == o.getInt("id"))
							return false;
				return true;
			}
				
			if(o.getString("name").equals("accelerometre")) {
				for(int i = 0; i<jsonArr.size(); i++)
					if(jsonArr.get(i).getString("name").equals("accelerometre") && jsonArr.get(i).getInt("id") == o.getInt("id"))
						return false;
				return true;
			}
			
			if(o.getString("name").equals("raspberry")) {
				for(int i = 0; i<jsonArr.size(); i++)
					if(jsonArr.get(i).getString("name").equals("raspberry") && jsonArr.get(i).getInt("id") == o.getInt("id"))
						return false;
				return true;
			}
			
			if(o.getString("name").equals("capt_sons")) {
				for(int i = 0; i<jsonArr.size(); i++)
					if(jsonArr.get(i).getString("name").equals("capt_sons") && jsonArr.get(i).getInt("id") == o.getInt("id"))
						return false;
				return true;
			}
		}
		return false;
	}
	
}
