
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
    String toChar(Objet plateau[][]){
        String res="|";
        for(int i =0;i<TAILLE;i+=1){
            for(int y =0;y<LARGEUR;y+=1){
                if(plateau[i][j] != null){
                    res= res + "*";
                }
                else {
                    res= res + " ";
                }
            }
            res = res+ "|/n";
        }
         for(int i =0;i<TAILLE;i+=1){
            res = res+ "_";
         }
         return res;
     }



}