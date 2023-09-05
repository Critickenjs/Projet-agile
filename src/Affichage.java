import java.util.InputMismatchException;
import java.util.Scanner;

public class Affichage {
	
	private static final int NB_SCORE_AFFICHER = 10;
	private static Scanner scanner = new Scanner(System.in);
	
	private int hauteur;
	private int largeur;
	
	public Affichage(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	private static void effacerTerminal() {
		System.out.print("\033c");
	}
	
	private void changerTailleTerminal() {
	    System.out.print("\033[8;" + this.hauteur + ";" + this.largeur + "t");
	}
	
	public void init() {
		effacerTerminal();
		this.changerTailleTerminal();
	}
	
	public void rafraichir(String contenu) {
		effacerTerminal();
		System.out.print(contenu);
	}
	public static void Menu() {
		int nombre = 0;
		boolean problemeDeSaisie = false;
		while(nombre != 3){
			effacerTerminal();
			System.out.println("                                  ___           ___           ___           ___     \n"
					+ "     _____                       /  /\\         /  /\\         /__/|         /  /\\    \n"
					+ "    /  /::\\                     /  /::\\       /  /:/        |  |:|        /  /:/_   \n"
					+ "   /  /:/\\:\\    ___     ___    /  /:/\\:\\     /  /:/         |  |:|       /  /:/ /\\  \n"
					+ "  /  /:/~/::\\  /__/\\   /  /\\  /  /:/  \\:\\   /  /:/  ___   __|  |:|      /  /:/ /::\\ \n"
					+ " /__/:/ /:/\\:| \\  \\:\\ /  /:/ /__/:/ \\__\\:\\ /__/:/  /  /\\ /__/\\_|:|____ /__/:/ /:/\\:\\\n"
					+ " \\  \\:\\/:/~/:/  \\  \\:\\  /:/  \\  \\:\\ /  /:/ \\  \\:\\ /  /:/ \\  \\:\\/:::::/ \\  \\:\\/:/~/:/\n"
					+ "  \\  \\::/ /:/    \\  \\:\\/:/    \\  \\:\\  /:/   \\  \\:\\  /:/   \\  \\::/~~~~   \\  \\::/ /:/ \n"
					+ "   \\  \\:\\/:/      \\  \\::/      \\  \\:\\/:/     \\  \\:\\/:/     \\  \\:\\        \\__\\/ /:/  \n"
					+ "    \\  \\::/        \\__\\/        \\  \\::/       \\  \\::/       \\  \\:\\         /__/:/   \n"
					+ "     \\__\\/                       \\__\\/         \\__\\/         \\__\\/         \\__\\/    ");
			System.out.println("\n\n\n Bienvenue dans blocks un jeu cool.\n\n");
			System.out.println("1.Jouer\n");
			System.out.println("2.Tableau des scores\n");
			System.out.println("3.Quitter\n");
			if (problemeDeSaisie){
				problemeDeSaisie = false;
				System.out.print("\nErreur de saisie !\nVeuillez saissir un chiffre entre 1 et 3 ");
			}
			try {
				nombre = scanner.nextInt();
			} catch (InputMismatchException e){
				problemeDeSaisie = true;
				scanner.nextLine();
			}
			if(nombre == 1) {
				affichePlateau();
			}
			if(nombre == 2) {
				//tableau des scores
				String[] resultat = Joueur.recupeScore().split("\n");
				String scores = "";
				int limite;
				if (NB_SCORE_AFFICHER > resultat.length){
					limite = resultat.length;
				} else {
					limite = NB_SCORE_AFFICHER;
				}
				for (int i=0; i<limite; i++){
					scores += (i+1)+". "+resultat[i].replace(';', '|')+"\n\n";
				}
				effacerTerminal();
				System.out.print("Classement des Meilleurs Scores : \n\n"+scores+"\nAppuyer sur Entrer pour Revenir au Menu Principale ");
				scanner.nextLine();
				scanner.nextLine();
			}
		}
	}
	public static void affichePlateau() {
		System.out.println("Score:\n"
						 + "__________\n"
						+ "|          |\n"
						+ "|          |\n"
						+ "|__________|\n");
		System.out.println("____________\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "|            |\n"
						+ "--------------\n");
	}
	public static void main(String[] args) {
		Menu();
	}
}
