public class DeplacementGauche implements Deplacement{
    public int[][] deplacement(int[][] positionsPrecedentes){
        int[][] result = new int[4][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = positionsPrecedentes[i][0];
            result[i][1] = positionsPrecedentes[i][1] - 1;
        }
        return result;
    }
    
}
