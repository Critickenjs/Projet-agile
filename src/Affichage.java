import java.util.InputMismatchException;
import java.util.Scanner;

public class Affichage {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_RED = "\u001B[31m";

	private static Scanner scanner = new Scanner(System.in);

	private static final int NB_SCORE_AFFICHER = 10;

	private static boolean problemeDeSaisie = false;
	
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
		this.init();
		System.out.print(contenu);
	}

	private static String recupererLogo(){
		return "                                  ___           ___           ___           ___     \n"
					+ "     _____                       /  /\\         /  /\\         /__/|         /  /\\    \n"
					+ "    /  /::\\                     /  /::\\       /  /:/        |  |:|        /  /:/_   \n"
					+ "   /  /:/\\:\\    ___     ___    /  /:/\\:\\     /  /:/         |  |:|       /  /:/ /\\  \n"
					+ "  /  /:/~/::\\  /__/\\   /  /\\  /  /:/  \\:\\   /  /:/  ___   __|  |:|      /  /:/ /::\\ \n"
					+ " /__/:/ /:/\\:| \\  \\:\\ /  /:/ /__/:/ \\__\\:\\ /__/:/  /  /\\ /__/\\_|:|____ /__/:/ /:/\\:\\\n"
					+ " \\  \\:\\/:/~/:/  \\  \\:\\  /:/  \\  \\:\\ /  /:/ \\  \\:\\ /  /:/ \\  \\:\\/:::::/ \\  \\:\\/:/~/:/\n"
					+ "  \\  \\::/ /:/    \\  \\:\\/:/    \\  \\:\\  /:/   \\  \\:\\  /:/   \\  \\::/~~~~   \\  \\::/ /:/ \n"
					+ "   \\  \\:\\/:/      \\  \\::/      \\  \\:\\/:/     \\  \\:\\/:/     \\  \\:\\        \\__\\/ /:/  \n"
					+ "    \\  \\::/        \\__\\/        \\  \\::/       \\  \\::/       \\  \\:\\         /__/:/   \n"
					+ "     \\__\\/                       \\__\\/         \\__\\/         \\__\\/         \\__\\/    "
					+"\n\n\n Bienvenue dans blocks un jeu cool.\n\n";
	}

	private static void attendre(){
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void animationAllumage(){
		attendre();
		String[] logo = recupererLogo().split("\n");
		for (int i=logo.length; i>0; i--){
			effacerTerminal();
			for(int y=i; y<logo.length; y++){
				System.out.print(logo[y]+"\n");
			}
			attendre();
		}
	}

	private static void animationExtinction(){
		attendre();
		String[] logo = recupererLogo().split("\n");
		for (int i=logo.length; i>0; i--){
			effacerTerminal();
			for(int y=0; y<i; y++){
				System.out.print(logo[y]+"\n");
			}
			attendre();
		}
	}

	private static int recupererEntrerUtilisateur(int min, int max){
		int contenu = -1;
		try {
			contenu = scanner.nextInt();
			if (contenu < min || contenu > max){
				throw new SaisieInvalide();
			}
		} catch (InputMismatchException | SaisieInvalide e){
			problemeDeSaisie = true;
			scanner.nextLine();
		}
		return contenu;
	}

	private static void afficherErreurSaisie(){
		if (problemeDeSaisie){
			problemeDeSaisie = false;
			System.out.print(ANSI_YELLOW+"Erreur de saisie !\nVeuillez saissir un chiffre entre 1 et 3.\n\n"+ANSI_RESET);
		}
	}

	private static void menuJouer(){
		int nombre = -1;
		while(nombre != 3){
			effacerTerminal();
			System.out.print(recupererLogo());
			System.out.println("1.Mode Simple\n");
			System.out.println("2.Mode Difficile\n");
			System.out.println(ANSI_RED+"3.Retour\n"+ANSI_RESET);
			afficherErreurSaisie();
			System.out.print("Choix : ");
			nombre = recupererEntrerUtilisateur(1,3);
			if (nombre == 1){
				// Mode Simple
			} else if (nombre == 2){
				// Mode Difficile
			}
			affichePlateau();
		}
	}

	private static void afficherClassement(){
		String[] resultat = Classement.recupeScore().split("\n");
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
		System.out.print("Classement des Meilleurs Scores : \n\n"+scores+ANSI_YELLOW+"\nAppuyer sur Entrer pour Revenir au Menu Principale "+ANSI_RESET);
		scanner.nextLine();
		scanner.nextLine();
	}

	public static void Menu() {
		animationAllumage();
		int nombre = -1;
		while(nombre != 3){
			effacerTerminal();
			System.out.print(recupererLogo());
			System.out.println("1.Jouer\n");
			System.out.println("2.Tableau des scores\n");
			System.out.println(ANSI_RED+"3.Quitter\n"+ANSI_RESET);
			afficherErreurSaisie();
			System.out.print("Choix : ");
			nombre = recupererEntrerUtilisateur(1,3);
			if(nombre == 1) {
				menuJouer();
			} else if(nombre == 2) {
				afficherClassement();
			}
		}
		animationExtinction();
		effacerTerminal();
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