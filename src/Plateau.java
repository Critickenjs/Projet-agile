eimport affichage.Affichage;

public class Plateau{
    private final int LARGEUR = 1;
    private final int TAILLE = 10;
    //This will be a matrix of colours (enum for each block)
    private Couleur plateau[][]; 
    private int[][] current_pos = new int[2][4];
    private int[][] next_pos = new int[2][4];
    private Affichage affi;

    public Plateau(){
        plateau = new Couleur[LARGEUR][TAILLE];
        for(int i = 0; i<LARGEUR;i++){
            for(int j=0;j<TAILLE;j++){
                plateau[i][j] = Couleur.EMPTY;
                //Replace this with enum colour
            }
        }
        affi = new Affichage(TAILLE+2, LARGEUR+2);
        affi.init();
    }

    String toChar(){
        String res="|";
        for(int i =0;i<TAILLE;i+=1){
            for(int y =0;y<LARGEUR;y+=1){
                if(plateau[i][y] != null){
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

    public boolean checkIfOccupied(){
        boolean occupied = false;
        int i = 0;
        while(!occupied && i<4){
            if(this.plateau[next_pos[0][i]][next_pos[1][i]] == Couleur.EMPTY){
                //Replace this if with if == empty 
                i++;
            }else{
                occupied = true;
            }
        }
        return occupied;
     }

    public void emptyCases(){
        for(int i=0;i<4;i++){
            this.plateau[current_pos[0][i]][current_pos[1][i]] = Couleur.EMPTY;
        }
    }

    public void colourCases(Couleur c){
        for(int i=0;i<4;i++){
            this.plateau[next_pos[0][i]][next_pos[1][i]] = c;
        }
    }
    
     public boolean goDown(){
        boolean success = checkIfOccupied();
        if(!success){
            return success;
        }
        emptyCases();
        colourCases(Couleur.RED);
        affi.rafraichir(toChar());
        return success;
    }

     





}