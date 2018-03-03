package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client_main {
	
	public static Socket socket = null;
	public static Thread connexionThread;

	
	public static void main(String[] args) {
		try {
			/*
			* Recuperation de l'address de connexion test en local a changer par l'address de la machine.
			*/
			InetAddress adrIPClient = InetAddress.getLocalHost();
			System.out.println("\n\t\tCOTE CLIENT\n");
			System.out.print("PHASE 1 -\tCONNEXION AU SERVEUR:");
			/*
			*
			* Creation de la socket de connexion : canal reliant le client au Serveur.
			* */
			socket = new Socket(adrIPClient,2009);
			System.out.println("\tConnexion établie avec le serveur.");
			System.out.print("PHASE 2 -\tAUTHENTIFICATION:");
			/*
			* Lancement du thread de connexion utilisant le canal crée.
			*
			* */
			connexionThread = new Thread(new Connexion(socket));
			connexionThread.start();
		} catch (UnknownHostException e) {
			/*
			* En cas d'address Host non valide ou errone
			* */
			System.err.println("Impossible de se connecter à l'adresse "+socket.getLocalAddress());
		} catch (IOException e) {
			/*
			* En cas de deconnexion du serveur ou port invalide
			* */
			System.err.println("Aucun serveur à l'écoute du port "+socket.getLocalPort());
		}
	}
}
