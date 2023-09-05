
public enum TypeBloc {
	
	Carre(new int[][]{{3,0},{3,1},{2,0},{2,1}},Couleur.BLUE),Ligne(new int[][]{{3,0},{3,1},{3,2},{2,3}},Couleur.GREEN),
	Zig(new int[][]{{3,0},{3,1},{2,1},{2,2}},Couleur.RED),Zag(new int[][]{{3,0},{2,0},{2,1},{1,1}},Couleur.YELLOW),
	T(new int[][]{{3,0},{3,1},{3,2},{2,1}},Couleur.PURPLE),L1(new int[][]{{3,0},{3,1},{2,1},{1,1}},Couleur.ORANGE),
	L2(new int[][]{{1,0},{2,0},{3,0},{3,1}},Couleur.CYAN);
	
	int[][]coordonnees;
	Couleur couleur;
	
	TypeBloc(int[][]coordonnees, Couleur couleur){
		this.coordonnees = coordonnees;
		this.couleur = couleur;
	}

	public int[][] getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(int[][] coordonnees) {
		this.coordonnees = coordonnees;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}	
}