package main;

public enum TypeBloc {
	
	Carre(new int[][]{{3,0},{3,1},{2,0},{2,1}},Couleur.BLUE),Ligne(new int[][]{{3,0},{3,1},{3,2},{2,3}},Couleur.GREEN),
	Zig(new int[][]{{3,0},{3,1},{2,1},{2,2}},Couleur.RED),Zag(new int[][]{{3,0},{2,0},{2,1},{1,1}},Couleur.YELLOW),
	T(new int[][]{{3,0},{3,1},{3,2},{2,1}},Couleur.PURPLE),Coin1(new int[][]{{3,0},{3,1},{2,1},{1,1}},Couleur.ORANGE),
	Coin2(new int[][]{{3,0},{3,1},{2,1},{1,1}},Couleur.CYAN),Ligne2(new int[][]{{3,0},{2,0},{1,0},{0,0}},Couleur.GREEN);
	
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

	/*public static String tabToString(int[][] tab) {
		String res =  "";
		for(int i=0 ; i<tab.length ; i++) {
			for(int j=0 ; j<tab[i].length ; j++) {
				res += tab[i][j];
			}
		}
		return res;
	}*/
	public static int[][] calculerRotation(TypeBloc piece) {
		int[][] res= null;
		if(piece.couleur.equals(TypeBloc.Carre.couleur) || piece.couleur.equals(Ligne.couleur)) {
			int[][] coordonneesOriginales = piece.getCoordonnees();
		    int[][] nouvellesCoordonnees = new int[coordonneesOriginales.length][2];
		    for (int i = 0; i < coordonneesOriginales.length; i++) {
		        int x = coordonneesOriginales[i][0];
		        int y = coordonneesOriginales[i][1];

		        // Appliquez la rotation de 90 degrés dans le sens horaire
		        int newX = y;
		        int newY = 3 - x;

		        nouvellesCoordonnees[i][0] = newX;
		        nouvellesCoordonnees[i][1] = newY;
		    }

		    res = nouvellesCoordonnees;
		}
		if(piece.toString() == "Ligne") {
		    res = new int[4][2];
			int x = piece.getCoordonnees()[0][0];
			int y = piece.getCoordonnees()[0][1];
			for(int i=0 ; i<piece.getCoordonnees().length ; i++) {
				res[i][0]=x-i;
				res[i][1]=y;
			}
		}
		if(piece.toString() == "Ligne2") {
			res = new int[4][2];
			int x = piece.getCoordonnees()[0][0];
			for(int i=0 ; i<piece.getCoordonnees().length ; i++) {
				res[i][0]= x;
				res[i][1]= i;
			}
		}
		return res;
	}
	public static TypeBloc genererPiece(){
		return TypeBloc.values()[(int) (Math.random() * (TypeBloc.values().length))];
	}
}
