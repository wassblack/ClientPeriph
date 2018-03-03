package serveur;

import java.io.PrintWriter;


public class Emission implements Runnable {

	private PrintWriter out_;
	private String message_ = null;
	
	public Emission(PrintWriter out, String msg) {
		this.out_ = out;
		message_ = msg;
	}
	
	public void run() {
		while(true){
			out_.println(message_);
			out_.flush();
			System.out.println("Transmission du Msg"); // Au serveur
		}
	}
}
