package client;

import javax.json.JsonObject;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Emission implements Runnable {
	//Creation d'un objet Flux de sortie pour recuperer le flux du canal
	private PrintWriter printWriter;
	//Creation d'un objet Scanner pour recuperer valeur au clavier si besoin
	private Scanner scanner = null;
	//creation d'un objet Socket pour recuperer canal de connexion.
	private Socket socket;
	/*
	* Constructeur pour crée des objets emission
	* sur le canal "socket" passee en param
	* et emmetre des message en utilisant le flux de sortie passe en param
	*
	* */
	public Emission(PrintWriter printWriter,Socket socket){
		this.socket = socket;
		this.printWriter = printWriter;
	}
	/*
	* Emmet le message passee en param sur le Flux printwriter du canal
	* de connexion passee en param retourne Vrai si reussi
	* Faux sinon.
	* */
	public boolean put(JsonObject messageJson,PrintWriter printWriter){
		try {
			printWriter.print(messageJson);
			printWriter.flush();
		}catch (Exception e) {
			e.printStackTrace();
			return  false;

		}
		return  false;
	}
	
	public void run() {
		//Initialisation du scanner
		scanner = new Scanner(System.in);
		// Tant que la socket est ouverte
		while(!socket.isClosed()){
			try {
				// Pause pour affichage correct
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.print("Moi:> ");
			String msg = scanner.nextLine();
			//put(msg,printWriter);
		}
	}
}
