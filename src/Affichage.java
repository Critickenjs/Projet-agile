<<<<<<< HEAD:src/affichage/Affichage.java
package affichage;

import java.awt.print.Printable;
import java.util.Scanner;

=======
>>>>>>> 907dd3dbfa2ef8c8c5068ad6bc119a4dd813347a:src/Affichage.java
public class Affichage {
	
	private int hauteur;
	private int largeur;
	
	public Affichage(int hauteur, int largeur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
	}
	
	private void effacerTerminal() {
		System.out.print("\033c");
	}
	
	private void changerTailleTerminal() {
	    System.out.print("\033[8;" + this.hauteur + ";" + this.largeur + "t");
	}
	
	public void init() {
		this.effacerTerminal();
		this.changerTailleTerminal();
	}
	
	public void rafraichir(String contenu) {
		this.effacerTerminal();
		System.out.print(contenu);
	}
	public static void Menu() {
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
		System.out.println("2.Tableau des scores");
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        if(nombre.equals("1")) {
        	affichePlateau();
        }
        if(nombre.equals("2")) {
        	//tableau des scores
        	scanner.close();
        	System.out.println("scores\n");
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
