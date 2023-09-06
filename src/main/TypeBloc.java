<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;
=======
package main;
import java.util.ArrayList;
import java.util.List;
>>>>>>> cb3f1f0d812d4e8c52cfb213c658df8ed94ee338

public enum TypeBloc {
	
	Carre(new int[][]{{3,0},{3,1},{2,0},{2,1}},Couleur.BLUE),Ligne(new int[][]{{3,0},{3,1},{3,2},{2,3}},Couleur.GREEN),
	Zig(new int[][]{{3,0},{3,1},{2,1},{2,2}},Couleur.RED),Zag(new int[][]{{3,0},{2,0},{2,1},{1,1}},Couleur.YELLOW),
	T(new int[][]{{3,0},{3,1},{3,2},{2,1}},Couleur.PURPLE),Coin1(new int[][]{{3,0},{3,1},{2,1},{1,1}},Couleur.ORANGE),
<<<<<<< HEAD
	Coin2(new int[][]{{1,0},{2,0},{3,0},{3,1}},Couleur.CYAN),Ligne2(new int[][]{{3,0},{2,0},{1,0},{0,0}},Couleur.GREEN),
	Zig2(new int[][]{{3,1},{2,1},{2,0},{1,0}},Couleur.RED),Zag2(new int[][]{{3,2},{3,1},{2,1},{2,0}},Couleur.YELLOW),
	T1(new int[][]{{3,0},{2,0},{1,0},{0,0}},Couleur.PURPLE),T2(new int[][]{{3,1},{2,0},{2,1},{2,2}},Couleur.PURPLE),
	T3(new int[][]{{3,1},{2,1},{2,0},{1,1}},Couleur.PURPLE),Coin11(new int[][]{{3,0},{3,1},{3,2},{2,0}},Couleur.ORANGE),
	Coin12(new int[][]{{3,0},{2,0},{1,0},{1,1}},Couleur.ORANGE),Coin13(new int[][]{{3,2},{2,0},{2,1},{2,2}},Couleur.ORANGE),
	Coin21(new int[][]{{3,0},{2,0},{2,1},{2,2}},Couleur.CYAN),Coin22(new int[][]{{1,0},{1,1},{2,1},{3,1}},Couleur.CYAN),
	Coin23(new int[][]{{3,0},{3,1},{3,2},{2,2}},Couleur.CYAN);
=======
	Coin2(new int[][]{{1,0},{2,0},{3,0},{3,1}},Couleur.CYAN),Ligne2(new int[][]{{3,0},{2,0},{1,0},{0,0}},Couleur.GREEN);
>>>>>>> cb3f1f0d812d4e8c52cfb213c658df8ed94ee338
	
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
	
<<<<<<< HEAD
	public static TypeBloc recupererType(int[][] tab) {
		TypeBloc res = null;
		Map<TypeBloc ,int[][]> coordonnees = new HashMap<TypeBloc,int[][]>();
		for (TypeBloc i : TypeBloc.values()) {
			coordonnees.put(i, i.getCoordonnees());
		}
		for(Map.Entry<TypeBloc, int[][]> entry : coordonnees.entrySet()) {
			TypeBloc type = entry.getKey();
			int[][] coor = entry.getValue();
			if(coor == tab) {
				res = type;
			}
		}
		return res;
	}
=======
>>>>>>> cb3f1f0d812d4e8c52cfb213c658df8ed94ee338
	public static String tabToString(int[][] tab) {
		String res =  "";
		for(int i=0 ; i<tab.length ; i++) {
			for(int j=0 ; j<tab[i].length ; j++) {
				res += tab[i][j];
			}
		}
		return res;
	}
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
<<<<<<< HEAD
	
	public static void main(String[] args) {
		TypeBloc piece = TypeBloc.Ligne;
		System.out.println(piece.toString());
		System.out.println(tabToString(Ligne2.getCoordonnees()));
		System.out.println(tabToString(calculerRotation(Ligne2)));
	}	
=======
	public static int[][] genererPiece(){
		List<int[][]> pieces = new ArrayList<>();
		for(TypeBloc i : values()) {
			pieces.add(i.getCoordonnees());
		}
		return pieces.get((int) (Math.random() * (pieces.size()-0)));
	}
>>>>>>> cb3f1f0d812d4e8c52cfb213c658df8ed94ee338
}
