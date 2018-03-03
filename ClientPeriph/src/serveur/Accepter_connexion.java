package serveur;

import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Accepter_connexion implements Runnable{

	private ServerSocket socketserver_ = null;
	private static ArrayList<Socket> socketArray = new ArrayList<Socket>();  //Canal reliant Serveur/Clients
	public Thread t1;
	
	public Accepter_connexion(ServerSocket ss){
	 socketserver_ = ss;
	}
	
	public void run() {
		try {
			while(true){
				if(socketArray.size() <= 10) { //Nombre de client connecté
					socketArray.add(socketserver_.accept());
					System.out.println("PHASE 2 - \tDETECTION CLIENT.");
					
					t1 = new Thread(new Authentification(socketArray) );
					t1.start();
				}
				else
					System.out.println("Echec car la limite de clients connectés est atteinte!");
			}
		} catch (IOException e) {
			System.err.println("Erreur serveur");
			e.printStackTrace();
		}
	}
}
