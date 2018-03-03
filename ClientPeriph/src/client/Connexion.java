package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Connexion implements Runnable{
	/*
	* Creation des differents objet nécessaire
	*
	* */
	private Socket socket = null;
	public static Thread lancerCommunication;
	private PrintWriter printWriter = null;
	private BufferedReader bufferReader = null;
	private boolean connected = false;
	private Scanner scanner = new Scanner(System.in);
	
	public Connexion(Socket s){
		socket = s;
	}
	
	public void run() {
		try {
			/*
			* Recuperation des flux d'entrer et de sortie du canal
			*
			* */
			printWriter = new PrintWriter(socket.getOutputStream());
			bufferReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			/*
			* Ecriture du premier message du serveur.
			* */
			String identificationString = new String("");
			System.out.println("\t"+bufferReader.readLine()+"\n");
			/*
			* Attente active d'information pour identifer le peripherique
			* voulant se connecter
			*
			* */
			while(!connected) {
				/*
				* Demande d'information pour identifier le peripherique
				* */
				System.out.print("Entrer vos informations json: ");
				identificationString = scanner.nextLine();
				/*
				* Envoi des information du peripherique au serveur
				* pour identification et nettoyage du flux
				* */
				printWriter.println(identificationString);
				printWriter.flush();

				/* Lecture recuperation de la reponse du serveur identification ok ? : .
				*
				* */
				String serverResponse = bufferReader.readLine();
				/*Si reponse positive du serveur identification arret de la boucle
				* Sinon message d'erreur
				*  */
				if(serverResponse.equals("connecte")){
					System.out.println("\nPHASE 2 -\tAUTHENTIFICATION:\tReussie");
					connected = true;
				}
				else 
					System.err.println(serverResponse+"\n");

				/*
				* Petite pause pour affichage correct du message
				* A changer pour raison de vitesse
				* */
				try {
					Thread.sleep(800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			/*
			* Si ideentification reussie lancement du protocle de communication
			* Creation du gestionnaire de communication pour emmettre et recevoir
			* */
			lancerCommunication = new Thread(new Chat_ClientServeur(socket));
			lancerCommunication.start();
		} catch (IOException e) {
			/*
			* En cas de deconnexion du serveur
			* ou Exception lié au flux (Fermeture inattendu) Input ou Output
			* */
			System.err.println("Le serveur ne répond plus ");
		}
	}
}