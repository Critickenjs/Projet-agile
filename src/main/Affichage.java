package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Affichage {
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_WHITE = "\u001B[37m";

	private static Scanner scanner = new Scanner(System.in);

	private static final int NB_SCORE_AFFICHER = 10;

	private static boolean problemeDeSaisie = false;
	
	public static final int HAUTEUR_JEU = Plateau.HAUTEUR+8;
	public static final int LARGEUR_JEU = Plateau.LARGEUR+20;
	
	public static final int HAUTEUR_MENU = 25;
	public static final int LARGEUR_MENU = 85;
	
	public static final int HAUTEUR_GAMEOVER = 18;
	public static final int LARGEUR_GAMEOVER = 125;
	
	private static void effacerTerminal() {
		System.out.print("\033c");
	}
	
	public static void setCursorVisible(boolean visible) {
		if (visible) {
			System.out.print("\033[?25h");
		} else {
			System.out.print("\033[?25l");
		}
	}
	
	public static void changerTailleTerminal(int hauteur, int largeur, boolean effacer) {
	    System.out.print("\033[8;" + hauteur + ";" + largeur + "t");
	    if (effacer) {
			effacerTerminal();
		}
	}
	
	public static void rafraichir(String contenu, int hauteur, int largeur, boolean cursorVisible) {
		changerTailleTerminal(hauteur, largeur, true);
		System.out.print(contenu);
		setCursorVisible(cursorVisible);
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
					+"\n\n\n T BLOKE T TRIST.\n\n";
	}

	private static void attendre(int miliseconde){
		try {
			Thread.sleep(miliseconde);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void animationAllumage(){
		attendre(150);
		String[] logo = recupererLogo().split("\n");
		for (int i=logo.length; i>=0; i--){
			effacerTerminal();
			for(int y=i; y<logo.length; y++){
				System.out.print(logo[y]+"\n");
			}
			attendre(150);
		}
	}

	private static void animationExtinction(){
		attendre(150);
		String[] logo = recupererLogo().split("\n");
		for (int i=logo.length; i>=0; i--){
			effacerTerminal();
			for(int y=0; y<i; y++){
				System.out.print(logo[y]+"\n");
			}
			attendre(150);
		}
	}

	private static int recupererEntrerUtilisateur(int min, int max){
		int contenu = -1;
		try {
			contenu = scanner.nextInt();
			if (contenu < min || contenu > max){
				throw new SaisieInvalideException();
			}
		} catch (InputMismatchException | SaisieInvalideException e){
			problemeDeSaisie = true;
			scanner.nextLine();
		}
		return contenu;
	}

	private static String afficherErreurSaisie(){
		if (problemeDeSaisie){
			problemeDeSaisie = false;
			return ANSI_YELLOW+"Erreur de saisie !\nVeuillez saissir un chiffre entre 1 et 3.\n\n"+ANSI_RESET;
		}
		return "";
	}

	private static String demanderNomJoueur(){
		scanner.nextLine();
		effacerTerminal();
		animationMenu("Veuillez inscrire votre pseudo : ");
		String pseudo = scanner.nextLine();
		while (pseudo.length() > 32){
			animationMenu(ANSI_YELLOW+"Pseudo Trop Long ! Veuillez donner un pseudo inferieur ou égale à 32."+ANSI_RESET+"\n\nVeuillez inscrire vôtre pseudo : ");
			pseudo = scanner.nextLine();
		}
		return pseudo;
	}

	private static int chercheMaxLongueurNom(String[] listeDesNoms){
		int longueurNom;
		int indexSeparateur = listeDesNoms[0].lastIndexOf(";");
		int max = listeDesNoms[0].substring(0, indexSeparateur).length();
		for (int i=1; i<listeDesNoms.length; i++){
			indexSeparateur = listeDesNoms[i].lastIndexOf(";");
			longueurNom = listeDesNoms[i].substring(0, indexSeparateur).length();
			if (max < longueurNom){
				max = longueurNom;
			}
		}
		return max;
	}

	private static String[] recupeNom(String[] classement){
		for (int i=0; i<classement.length; i++){
			classement[i].substring(0,classement[i].lastIndexOf(";"));
		}
		return classement;
	}

	private static String repeteCaractere(int nbr, char caractere){
		String repetition = "";
		for (int i=0; i<nbr; i++){
			repetition += caractere;
		}
		return repetition;
	}

	private static String[] insertEspace(String[] classement){
		int indexSeparateur;
		String nom;
		String nouveauSeparateur = "  |  ";
		String score;
		int longueurMaxNom = chercheMaxLongueurNom(recupeNom(classement));
		int longueurMaxScore = classement[0].substring(classement[0].lastIndexOf(";")+1,classement[0].length()).length();
		for (int i=0; i<classement.length; i++){
			indexSeparateur = classement[i].lastIndexOf(";");
			nom = classement[i].substring(0,indexSeparateur);
			score = classement[i].substring(indexSeparateur+1,classement[i].length());
			if (i==9){
				nouveauSeparateur = " |  ";
			}
			classement[i] = nom+repeteCaractere(longueurMaxNom-nom.length(),' ')+nouveauSeparateur+repeteCaractere(longueurMaxScore-score.length(),' ')+score;
		}
		return classement;
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
		resultat = insertEspace(resultat);
		for (int i=0; i<limite; i++){
			scores += (i+1)+". "+resultat[i]+"\n\n";
		}
		effacerTerminal();
		animationMenu("Classement des Meilleurs Scores : \n\n"
					  +scores
					  +ANSI_YELLOW+"\nAppuyer sur Entrer pour Revenir au Menu Principale "+ANSI_RESET
		);
		scanner.nextLine();
		scanner.nextLine();
	}

	private static void animationFinPartie(int score){
		String finPartie = ANSI_RED+"      ___           ___           ___           ___                    ___                        ___           ___     \n" + 
				"     /  /\\         /  /\\         /__/\\         /  /\\                  /  /\\          ___         /  /\\         /  /\\    \n" + 
				"    /  /:/_       /  /::\\       |  |::\\       /  /:/_                /  /::\\        /__/\\       /  /:/_       /  /::\\   \n" + 
				"   /  /:/ /\\     /  /:/\\:\\      |  |:|:\\     /  /:/ /\\              /  /:/\\:\\       \\  \\:\\     /  /:/ /\\     /  /:/\\:\\  \n" +
				"  /  /:/_/::\\   /  /:/~/::\\   __|__|:|\\:\\   /  /:/ /:/_            /  /:/  \\:\\       \\  \\:\\   /  /:/ /:/_   /  /:/~/:/  \n" +
				" /__/:/__\\/\\:\\ /__/:/ /:/\\:\\ /__/::::| \\:\\ /__/:/ /:/ /\\          /__/:/ \\__\\:\\  ___  \\__\\:\\ /__/:/ /:/ /\\ /__/:/ /:/___\n" +
				" \\  \\:\\ /~~/:/ \\  \\:\\/:/__\\/ \\  \\:\\~~\\__\\/ \\  \\:\\/:/ /:/          \\  \\:\\ /  /:/ /__/\\ |  |:| \\  \\:\\/:/ /:/ \\  \\:\\/:::::/\n" +
				"  \\  \\:\\  /:/   \\  \\::/       \\  \\:\\        \\  \\::/ /:/            \\  \\:\\  /:/  \\  \\:\\|  |:|  \\  \\::/ /:/   \\  \\::/~~~~ \n" +
				"   \\  \\:\\/:/     \\  \\:\\        \\  \\:\\        \\  \\:\\/:/              \\  \\:\\/:/    \\  \\:\\__|:|   \\  \\:\\/:/     \\  \\:\\     \n" +
				"    \\  \\::/       \\  \\:\\        \\  \\:\\        \\  \\::/                \\  \\::/      \\__\\::::/     \\  \\::/       \\  \\:\\    \n" +
				"     \\__\\/         \\__\\/         \\__\\/         \\__\\/                  \\__\\/           ~~~~       \\__\\/         \\__\\/    \n\n\n"+
				ANSI_WHITE+"      VOTRE SCORE : "+score+"\n\n"+ANSI_YELLOW+"\nAppuyer sur Entrer pour Revenir au Menu Principale "+ANSI_RESET;
		for (int i=0; i<=finPartie.length(); i++){
			effacerTerminal();
			System.out.print(finPartie.substring(0, i));
			attendre(3000/finPartie.length());
		}
	}

	private static void afficherFinPartie(int score){
		animationFinPartie(score);
		scanner.nextLine();
	}

	private static void animationMenu(String diversChoix){
		for (int i=0; i<=diversChoix.length(); i++){
			effacerTerminal();
			System.out.print(recupererLogo()+diversChoix.substring(0, i));
			attendre(2000/diversChoix.length());
		}
	}

	public static void Menu(String configInitial) {
		changerTailleTerminal(HAUTEUR_MENU, LARGEUR_MENU, true);
		animationAllumage();
		animationMenu(ANSI_YELLOW+"CLIQUER SUR ENTRER "+ANSI_RESET);
		scanner.nextLine();
		int nombre = -1;
		while(nombre != 3){
			effacerTerminal();
			animationMenu("1.Jouer\n\n"
						  +"2.Tableau des scores\n\n"
						  +ANSI_RED+"3.Quitter\n\n"+ANSI_RESET
						  +afficherErreurSaisie()
						  +"Choix : "
			);
			nombre = recupererEntrerUtilisateur(1,3);
			if(nombre == 1) {
				int score;
				DebutJeu partie = new DebutJeu(demanderNomJoueur());
    			score = partie.jouer(configInitial);
				afficherFinPartie(score);
				changerTailleTerminal(HAUTEUR_MENU, LARGEUR_MENU, true);
			} else if(nombre == 2) {
				afficherClassement();
			}
		}
		animationExtinction();
		effacerTerminal();
	}
}