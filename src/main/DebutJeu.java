package main;
import java.util.concurrent.LinkedBlockingQueue;

import deplacement.Deplacement;
import listener.KeyboardListener;

public class DebutJeu{
	
	private LinkedBlockingQueue<Deplacement> queue;
	private Plateau plateauActuel;
	private int score;
	private String nomJoueur;

    public DebutJeu(String nomJoueur) {
        this.queue = new LinkedBlockingQueue<Deplacement>();
        this.nomJoueur = nomJoueur;
        this.plateauActuel = new Plateau(nomJoueur);
        this.score = 0;
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
    
    private void jouer(String configInitial) {
    	//Initialisation du jeu		
    	Affichage affichageCourant = new Affichage(Plateau.HAUTEUR+2, Plateau.LARGEUR+2);
    	affichageCourant.init();
        
        
        //Lancement du thread d'écoute
        KeyboardListener kl = new KeyboardListener(this.queue, configInitial);
		kl.start();
        
        int score = 0;
        boolean ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.genererPiece());
        affichageCourant.rafraichir(plateauActuel.toString());
      //Tant que l'on peut ajouter une pièce
        while (ajoutBlocOk) {
        	//On avance la pièce tant que l'on peut
        	boolean piecePeutAvancer = this.avancerPiece();
        	while (piecePeutAvancer) {
        		affichageCourant.rafraichir(plateauActuel.toString());
        		piecePeutAvancer = this.avancerPiece();
    		}
			//Efface ligne + calcule du score
			score += plateauActuel.calculateScore();
        	//Ajout d'une pièce
        	ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.genererPiece());
        	if (ajoutBlocOk) {
        		affichageCourant.rafraichir(plateauActuel.toString());
			}
		}
        //On peut interrompre le thread d'écoute
        kl.interrupt();
    }

    public static void main(String[] args) {
    	String configInitial = args.length == 1 ? args[0] : "";
    	//Il faut demander a l'utilisateur son nom
		//Scanner nomJoueur = new Scanner(System.in);
		//System.out.println("Entrez votre nom: ");
		//String joueur_nom = nomJoueur.nextLine();
		//nomJoueur.close();
    	DebutJeu partie = new DebutJeu("k");
    	partie.jouer(configInitial);
    }
}
