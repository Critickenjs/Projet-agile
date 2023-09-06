import java.util.Scanner;

public class DebutJeu{

    public DebutJeu(){
        System.out.println("hi");
    }

    public static void main(String[] args) {
		int i = 0;
		int speedUp = 1500;
    	//Initialisation du jeu

		//Il faut demander a l'utilisateur son nom, mettre dans 
		Scanner nomJoueur = new Scanner(System.in);
		System.out.println("Entrez votre nom: ");
		String joueur_nom = nomJoueur.nextLine();
		nomJoueur.close();
		
    	Affichage affichageCourant = new Affichage(Plateau.HAUTEUR+2, Plateau.LARGEUR+2);
    	affichageCourant.init();
        Plateau plateauActuel = new Plateau(joueur_nom);
        
        int score = 0;
		Deplacement deplacementBas = new DeplacementBas();
		Deplacement deplacementDroite = new DeplacementDroite();
		Deplacement deplacementGauche = new DeplacementGauche();
        boolean ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.Carre);
        affichageCourant.rafraichir(plateauActuel.toString());
      //Tant que l'on peut ajouter une pièce
        while (ajoutBlocOk) {
        	//On avance la pièce tant que l'on peut
        	boolean piecePeutAvancer = plateauActuel.deplacement(deplacementBas);
        	while (piecePeutAvancer) {
        		affichageCourant.rafraichir(plateauActuel.toString());
        		try {
					Thread.sleep(plateauActuel.getSpeed());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		piecePeutAvancer = plateauActuel.deplacement(deplacementBas);
    		}
			//Efface ligne + calcule du score
			// int nbLignes = plateauActuel.verifiePlateau();
			// affichageCourant.rafraichir(plateauActuel.toString());
			score = plateauActuel.calculateScore();
			affichageCourant.rafraichir(plateauActuel.toString());
			System.out.println(joueur_nom + " : " + score);
			//Test for speed up - every 1500 points, it speeds up by 10
			if(score>speedUp){
				if(plateauActuel.getSpeed() > 50){
					plateauActuel.setSpeed(plateauActuel.getSpeed()-10);
				}
				speedUp+=1500;
			}
        	//Ajout d'une pièce
        	ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.Carre);
        	if (ajoutBlocOk) {
        		affichageCourant.rafraichir(plateauActuel.toString());
			}
			i++;
		}
    }
}

