package main;
import java.util.concurrent.LinkedBlockingQueue;

import deplacement.Deplacement;
import listener.KeyboardListener;

public class DebutJeu{
	
	private LinkedBlockingQueue<Deplacement> queue;
	private Plateau plateauActuel;
	private Joueur joueur;
	private boolean quitter;
	private KeyboardListener kl;

    public DebutJeu(String nomJoueur) {
        this.queue = new LinkedBlockingQueue<Deplacement>();
		this.joueur = new Joueur(nomJoueur);
        this.plateauActuel = new Plateau(nomJoueur);
        this.quitter = false;
    }
    
    //Cette méthode permet de faire avancer une pièce :
    //Vérifie s'il y a des actions de déplacements à effectuer
    //Au bout d'un délai, effectue un déplacement vers le bas
    private boolean avancerPiece() {
    	//Délai de descente : 500ms
    	//Effectue une action toutes les 50ms
    	for (int i = 0; i < 10; i++) {
    		try {
				Thread.sleep(50);
				//Si le thread n'écoute plus, le joueur a appuyé sur échape
				if (!this.kl.isListening()) {
					return false;
				}
				//On ne lance l'action que s'il y en a une disponible
				if (this.queue.size() > 0) {
					this.plateauActuel.deplacement(this.queue.take());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    	//On effectue un déplacement vers le bas pour finir
    	return this.plateauActuel.goDown();
    }
    
    public int jouer(String configInitial) {
    	//Initialisation du jeu		
    	Affichage.changerTailleTerminal(Affichage.HAUTEUR_JEU, Affichage.LARGEUR_JEU, true);
        
        //Lancement du thread d'écoute
        this.kl = new KeyboardListener(this.queue, configInitial);
		kl.start();
        
        boolean ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.genererPiece());
        Affichage.rafraichir(plateauActuel.toString(), Affichage.HAUTEUR_JEU, Affichage.LARGEUR_JEU, false);
      //Tant que l'on peut ajouter une pièce
        while (ajoutBlocOk) {
        	//On avance la pièce tant que l'on peut
        	boolean piecePeutAvancer = this.avancerPiece();
        	while (piecePeutAvancer) {
        		Affichage.rafraichir(plateauActuel.toString(), Affichage.HAUTEUR_JEU, Affichage.LARGEUR_JEU, false);
        		piecePeutAvancer = this.avancerPiece();
    		}
        	//Le joueur veut il quitter ?
        	if (!this.kl.isListening()) {
        		try {
        			Affichage.changerTailleTerminal(Affichage.HAUTEUR_GAMEOVER, Affichage.LARGEUR_GAMEOVER, true);
        			kl.interrupt();
        			kl.join();
        			return joueur.getScore();
        		} catch (Exception e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
			}
			//Efface ligne + calcule du score
			joueur.setScore(joueur.getScore()+plateauActuel.calculateScore());
        	//Ajout d'une pièce
        	ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.genererPiece());
        	if (ajoutBlocOk) {
        		Affichage.rafraichir(plateauActuel.toString(), Affichage.HAUTEUR_JEU, Affichage.LARGEUR_JEU, false);
			}
		}
        try {
			Affichage.changerTailleTerminal(Affichage.HAUTEUR_GAMEOVER, Affichage.LARGEUR_GAMEOVER, true);
			kl.interrupt();
			kl.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //On peut interrompre le thread d'écoute
        kl.interrupt();
		joueur.sauvegardeScore();
		Affichage.setCursorVisible(true);
		return joueur.getScore();
    }

    public static void main(String[] args) {
    	String configInitial = args.length == 1 ? args[0] : "sane";
		Affichage.Menu(configInitial);
    }
}
