package deplacement;

import main.TypeBloc;

public class DeplacementRotation implements Deplacement {

	@Override
	public int[][] deplacement(int[][] positionsPrecedentes, TypeBloc piece) {
		return piece.getPieceSuivante().getCoordonnees();
		
	}

	@Override
	public boolean estRotation() {
		return true;
	}

}
