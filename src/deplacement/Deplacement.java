package deplacement;

import main.TypeBloc;

public interface Deplacement {
    public boolean estRotation();
    public int[][] deplacement(int[][] positionsPrecedentes, TypeBloc piece);

}
