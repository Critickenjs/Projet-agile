import java.util.Scanner;

public class DebutJeu{

    public DebutJeu(){
        System.out.println("hi");
    }

    public static void main(String[] args) {
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
        boolean ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.Carre);
        affichageCourant.rafraichir(plateauActuel.toString());
      //Tant que l'on peut ajouter une pièce
        while (ajoutBlocOk) {
        	//On avance la pièce tant que l'on peut
        	boolean piecePeutAvancer = plateauActuel.goDown();
        	while (piecePeutAvancer) {
        		affichageCourant.rafraichir(plateauActuel.toString());
        		try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		piecePeutAvancer = plateauActuel.goDown();
    		}
			//Efface ligne + calcule du score
			score += plateauActuel.calculateScore(score);
			System.out.println(joueur_nom + " : " + score);
        	//Ajout d'une pièce
        	ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.Carre);
        	if (ajoutBlocOk) {
        		affichageCourant.rafraichir(plateauActuel.toString());
			}
		}
    }
}

