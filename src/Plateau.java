
public class Plateau{
    private final int LARGEUR = 1;
    private final int TAILLE = 10;
    //This will be a matrix of colours (enum for each block)
    private Object plateau[][]; 

    public Plateau(){
        plateau = new Object[LARGEUR][TAILLE];
        for(int i = 0; i<LARGEUR;i++){
            for(int j=0;j<TAILLE;j++){
                plateau[i][j] = new Object();
                //Replace this with enum colour
            }
        }
    }



}