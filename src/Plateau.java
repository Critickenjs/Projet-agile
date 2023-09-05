public class Plateau{
    public static final int LARGEUR = 8;
    public static final int TAILLE = 20;
    //This will be a matrix of colours (enum for each block)
    private Couleur plateau[][]; 
    private int[][] current_pos = new int[2][4];
    private int[][] next_pos = new int[2][4];
    private Affichage affi;
    private TypeBloc blocCourant;

    public Plateau(){
        plateau = new Couleur[LARGEUR][TAILLE];
        for(int i = 0; i<LARGEUR;i++){
            for(int j=0;j<TAILLE;j++){
                plateau[i][j] = Couleur.EMPTY;
                //Replace this with enum colour
            }
        }
    }
    
    public boolean ajouterBloc(TypeBloc bloc) {
    	this.blocCourant = bloc;
    	this.current_pos = bloc.getCoordonnees();
    	this.next_pos = bloc.getCoordonnees();
    	if (this.positionsSuivantesLibres() == false) {
			return false;
		}
    	this.colourCases();
    	return true;
    }

    public String toString(){
        String res = "";
        for (int i = 0; i < this.plateau.length; i++) {
			for (int j = 0; j < this.plateau[i].length; j++) {
				if (this.plateau[i][j] == Couleur.EMPTY) {
					res += " ";
				} else {
					res += "O";
				}
			}
			res += "\n";
		}
         return res;
     }

    private boolean positionsSuivantesLibres(){
    	//On regarde toutes les coordonnées de base
    	for (int i = 0; i < this.current_pos.length; i++) {
    		int x = this.next_pos[i][0];
    		int y = this.next_pos[i][1];
    		if (x > LARGEUR || y > TAILLE) {
				return false;
			}
    		if (this.plateau[x][y] != Couleur.EMPTY) {
				return false;
			}
		}
    	return true;
     }

    private void emptyCases(){
    	for (int i = 0; i < this.current_pos.length; i++) {
    		int x = this.current_pos[i][0];
    		int y = this.current_pos[i][1];
    		this.plateau[x][y] = Couleur.EMPTY;
		}
    }

    private void colourCases(){
        for (int i = 0; i < this.next_pos.length; i++) {
    		int x = this.next_pos[i][0];
    		int y = this.next_pos[i][1];
    		this.plateau[x][y] = this.blocCourant.getCouleur();
		}
    }
    
     public boolean goDown(){
    	 //Calcul des positions suivantes
    	 //Pour chaque ligne, on augmente de 1 le num de ligne (x)
    	 for (int i = 0; i < this.current_pos.length; i++) {
     		this.next_pos[i][0] = this.current_pos[i][0] + 1;
 		}
    	 
    	 if (this.positionsSuivantesLibres() == false) {
			return false;
		}
        emptyCases();
        colourCases();
        this.current_pos = this.next_pos;
        return true;
    }

     





}