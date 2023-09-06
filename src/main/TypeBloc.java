package main;

public enum TypeBloc {
	
	Carre(new int[][]{{3,0},{3,1},{2,0},{2,1}},Couleur.BLUE,"Carre"),


	Ligne1(new int[][]{{3,0},{3,1},{3,2},{3,3}},Couleur.GREEN,"Ligne2"),
	Ligne2(new int[][]{{3,0},{2,0},{1,0},{0,0}},Couleur.GREEN,"Ligne1"),


	Zig(new int[][]{{1,0},{2,0},{2,1},{3,1}},Couleur.RED,"Zig1"),
	Zig1(new int[][]{{3,0},{3,1},{2,1},{2,2}},Couleur.RED,"Zig"),

	Zag(new int[][]{{3,0},{2,0},{2,1},{1,1}},Couleur.YELLOW,"Zag1"),
	Zag1(new int[][]{{2,0},{2,1},{3,1},{3,2}},Couleur.YELLOW,"Zag"),

	T1(new int[][]{{3,0},{3,1},{3,2},{2,1}},Couleur.PURPLE,"T2"),
	T2(new int[][]{{1,0},{2,0},{2,1},{3,0}},Couleur.PURPLE,"T3"),
	T3(new int[][]{{2,0},{2,1},{2,2},{3,1}},Couleur.PURPLE,"T4"),
	T4(new int[][]{{2,0},{1,1},{2,1},{3,1}},Couleur.PURPLE,"T1"),


	Coin11(new int[][]{{3,0},{3,1},{2,1},{1,1}},Couleur.ORANGE,"Coin12"),
	Coin12(new int[][]{{2,0},{3,0},{3,1},{3,2}},Couleur.ORANGE,"Coin13"),
	Coin13(new int[][]{{1,1},{1,0},{2,0},{3,0}},Couleur.ORANGE,"Coin14"),
	Coin14(new int[][]{{2,0},{2,1},{2,2},{3,2}},Couleur.ORANGE,"Coin11"),



	Coin21(new int[][]{{1,0},{3,1},{2,1},{1,1}},Couleur.CYAN,"Coin22"),
	Coin22(new int[][]{{2,2},{2,1},{2,0},{3,0}},Couleur.CYAN,"Coin23"),
	Coin23(new int[][]{{1,0},{2,0},{3,0},{3,1}},Couleur.CYAN,"Coin24"),
	Coin24(new int[][]{{3,0},{3,1},{3,2},{2,2}},Couleur.CYAN,"Coin21");



	
	
	int[][]coordonnees;
	Couleur couleur;
	String suivant;

	
	TypeBloc(int[][]coordonnees, Couleur couleur, String suivant){
		this.coordonnees = coordonnees;
		this.couleur = couleur;
		this.suivant = suivant;
	}


	public TypeBloc getPieceSuivante(){
		return TypeBloc.valueOf(suivant);
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
		if(piece.couleur.equals(TypeBloc.Carre.couleur) || piece.couleur.equals(Ligne1.couleur)) {
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
