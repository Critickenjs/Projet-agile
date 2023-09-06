package deplacement;

import main.TypeBloc;

public class DeplacementGauche implements Deplacement{
    public int[][] deplacement(int[][] positionsPrecedentes, TypeBloc piece){
        int[][] result = new int[4][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = positionsPrecedentes[i][0];
            result[i][1] = positionsPrecedentes[i][1] - 1;
        }
        return result;
    }

	@Override
	public boolean estRotation() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
