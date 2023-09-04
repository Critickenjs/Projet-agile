package src;

public class Bloc {     
    private int rotation;     
    private String[][]piece;     
    private int type;
    private Couleur couleur;          
    public Bloc(int type) {         
        this.type = type;         
        this.piece = initializeBloc(type);     
    }     
    public String[][] initializeBloc(int type){         
        String[][] res = null;         
        if(type == 1) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"x","x","v","v"},{"x","x","v","v"}};         
        }         
        if(type == 2) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"v","v","v","v"},{"x","x","x","x"}};         
        }         
        return res;     
    }
}
