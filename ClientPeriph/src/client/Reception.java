package client;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.json.JsonArray;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;


public class Reception implements Runnable {
    //creation d'un objet Socket pour recuperer canal de connexion.
	private Socket socket;
    //Creation d'un objet Flux d'entree pour recuperer le flux du canal.
	private BufferedReader bufferedReader;
	//Creation d'une chaine de caracteres pour recuperer les messages du serveur.
	private String receivedMessage = null;
	//Creation d'une liste pour stockés les valeur des differents peripheriques connecteé.
	public static JSONArray values;
	/*
	* initialisation des objets reception
	* */

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}

	public String getReceivedMessage() {
		return receivedMessage;
	}

	public void setReceivedMessage(String receivedMessage) {
		this.receivedMessage = receivedMessage;
	}

	public static JSONArray getValues() {
		return values;
	}

	public static void setValues(JSONArray values) {
		Reception.values = values;
	}

	public Reception(BufferedReader in, Socket socket){
		this.socket = socket;
		this.bufferedReader = in;
	}
	public static JSONObject getPeriph(String name){
		JSONArray jsonArray=getValues();
		Iterator iterator=jsonArray.iterator();
		int counter=0;
		while(iterator.hasNext()){
			JSONObject jsonObject= (JSONObject)values.get(counter);
			if (jsonObject.get("name").equals(name)){
				return jsonObject;
			}
			counter++;
			iterator.next();
		}
		return null;
	}
	
	public void run() {
		while(!socket.isClosed()) {
			try {
				values=new JSONArray();
                JsonHandle jsonHandle=new JsonHandle();
                //Message recu par le serveur Broadcast
				//receivedMessage = bufferedReader.readLine();
                receivedMessage="{\"id\":1,\"name\":\"gyroscope\", \"data\":{\"x\":2.1,\"y\":5.4, \"z\":6.0}, \"state\":\"connected\"}";
                //receivedMessage="{\"id\":1,\"name\":\"gps\", \"data\":{\"x\":2.1,\"y\":5.4, \"z\":6.0}, \"state\":\"connected\"}";
                //receivedMessage="{\"id\":1,\"name\":\"acc\", \"data\":{\"x\":2.1,\"y\":5.4, \"z\":6.0}, \"state\":\"connected\"}";
				System.out.println("Serveur dit > " +receivedMessage+"\n");
				if (JsonHandle.isValideResponseJSON(receivedMessage))
					JsonHandle.addOrReplace(values,JsonHandle.toJSON(receivedMessage));

                System.out.println("Valeur retourner par fct :"+getPeriph("gyroscope"));
                if(receivedMessage.equals("Vous etes deconnecté")) {
					System.out.print("> etat de connexion: ");
					System.out.println(socket.isClosed()?"fermé":"ouverte");
					socket.close();
					System.out.print("> etat de connexion: ");
					System.out.println(socket.isClosed()?"fermé":"ouverte");
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
