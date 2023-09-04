package affichage;

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
}
