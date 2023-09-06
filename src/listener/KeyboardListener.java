package listener;
import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.LinkedBlockingQueue;

import deplacement.Deplacement;
import deplacement.DeplacementBas;
import deplacement.DeplacementDroite;
import deplacement.DeplacementGauche;
import deplacement.DeplacementRotation;

public class KeyboardListener extends Thread {
	
	private LinkedBlockingQueue<Deplacement> queue;
	private boolean listening;
	private String configInitial;
	
	/**
	 * @param queue
	 */
	public KeyboardListener(LinkedBlockingQueue<Deplacement> queue, String configInitial) {
		super();
		this.queue = queue;
		this.configInitial = configInitial;
	}

	public void run() {
		//On active l'écoute du clavier
		this.listening = true;
        try {
        	//On récupère l'environnement d'exécution de l'application
        	Runtime environnementExecutionActuel = Runtime.getRuntime();
        	//On configure le terminal afin que chaque caractères soient reçu de manière brut par notre application
			environnementExecutionActuel.exec(new String[]{"/bin/sh", "-c", "stty raw </dev/tty"}).waitFor();
			
			//Lecture des touches
			Console console = System.console();
	        Reader reader = console.reader();
	        while (listening) {
	        	//GAUCHE = 27-91-68
				//DROITE = 27-91-67
	        	//BAS = 27-91-66
				//ESPACE = 32
	        	//Q = 113
	        	Deplacement deplacement = null;
				int toucheCourante = reader.ready() ? reader.read() : 0;
				if (toucheCourante == 27) {
					toucheCourante = reader.read();
					if (toucheCourante == 91) {
						toucheCourante = reader.read();
						if (toucheCourante == 68) {
							//GAUCHE
							deplacement = new DeplacementGauche();
						} else if (toucheCourante == 67) {
							//DROITE
							deplacement = new DeplacementDroite();
						} else if (toucheCourante == 66) {
							//BAS
							deplacement = new DeplacementBas();
						}
					}
				} else if (toucheCourante == 32) {
					//ESPACE
					deplacement = new DeplacementRotation();
				} else if (toucheCourante == 113) {
					//le joueur veut quitter
					this.listening = false;
				}
				//On peut ajouter l'action à la queue
				if (deplacement!= null && this.listening) {
					this.queue.offer(deplacement);
				}
				deplacement = null;
			}
	        //On réinitilialise le terminal dans une configuration normal
            Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", "stty " + this.configInitial + " </dev/tty"}).waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	public boolean isListening() {
		return this.listening;
	}
	
	public void interrupt() {
		//On stop l'écoute du clavier
		this.listening = false;
	}
}