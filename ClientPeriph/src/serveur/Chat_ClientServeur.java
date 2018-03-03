package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.json.JsonObject;


public class Chat_ClientServeur implements Runnable {

	private ArrayList<Socket> socketArray = null;
	private LinkedList<JsonObject> jsonArray = null; // Parce qu'il y aura suppression a chaque entrée
	private BufferedReader in_;
	private PrintWriter out_;
	private String login_;
	
	public Chat_ClientServeur(ArrayList<Socket> sockArray, String log, LinkedList<JsonObject> jsonArr){
		socketArray = sockArray;
		jsonArray = jsonArr;
		login_ = log;
	}
	

	public void run() {
		// On receptionne toute données provenant d'un client et on transmet.
		try {
			System.out.println("A cet instant on a une taille de "+jsonArray.size());
			for(int i=0; i<socketArray.size(); i++){
				in_ = new BufferedReader(new InputStreamReader(socketArray.get(i).getInputStream()));
				out_ = new PrintWriter(socketArray.get(i).getOutputStream());
				
				Thread t3 = new Thread(new Reception(in_, out_, jsonArray, i, login_, socketArray)); //Il faudrait envoyer la position du login
				t3.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}	
