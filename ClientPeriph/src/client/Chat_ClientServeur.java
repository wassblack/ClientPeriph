package client;
import java.io.*;
import java.net.*;


public class Chat_ClientServeur implements Runnable {
	/*
	* Creation des differents objet nécessaire
	*/
	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	public Chat_ClientServeur(Socket s){
		socket = s;
	}
	
	public void run() {
		try {
			System.out.println("PHASE 3 -\tMODE TCHAT. \t\t Actif\n");
			/*
			* Recuperation des flux d'entrer et de sortie du canal de la connexion
			* Déja crée en PHASE 2
			* */
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			/*
			* Creation des thread d'envoie et de reception
			* Avec le canal et le flux qu'il faut en parametre pour permettre
			* L'envoie et reception simultaneé.
			* */
			Thread receptionThread = new Thread(new Reception(in, socket));
			receptionThread.start();
			Thread sendingThread = new Thread(new Emission(out, socket));
			sendingThread.start();

		}catch (UnknownHostException e) {
			/*
			* En cas d'address Host non valide ou errone
			* */
			e.printStackTrace();
		}
		catch (IOException e) {
			/*
			* En cas de deconnexion du Serveur ou
			* Exception lié au flux (Fermeture inattendu) Input ou Output.
			* */
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}

}
