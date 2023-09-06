package main;
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
        if(type == 3) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"x","v","v","v"},{"x","x","x","v"}};         
        }
        if(type == 4) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"v","v","x","v"},{"x","x","x","v"}};         
        }
        if(type == 5) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"v","x","v","v"},{"x","x","x","v"}};         
        }
        if(type == 6) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"v","x","x","v"},{"x","x","v","v"}};         
        }
        if(type == 7) {             
            res = new String[][] {{"v","v","v","v"},{"v","v","v","v"},{"x","x","v","v"},{"v","x","x","v"}};         
        }
        return res;     
    }
    public String[][] TournerPiece(int type, int indiceRotation){
    	String [][] res = null;
    	
    	return res;
    }
}
