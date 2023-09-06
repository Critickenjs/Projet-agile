package deplacement;

import main.TypeBloc;

public class DeplacementRotation implements Deplacement {

	@Override
	public int[][] deplacement(int[][] positionsPrecedentes, TypeBloc piece) {
		return piece.getPieceSuivante().getCoordonnees();
	}
	/*
	public static int[][] calculerRotation(TypeBloc piece) {
		int[][] res= null;
		if(piece.couleur.equals(TypeBloc.Carre.getCouleur()) || piece.couleur.equals(Ligne.couleur)) {
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
	}*/

	@Override
	public boolean estRotation() {
		return true;
	}

}
