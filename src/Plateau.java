<<<<<<< HEAD
eimport affichage.Affichage;

=======
>>>>>>> 907dd3dbfa2ef8c8c5068ad6bc119a4dd813347a
public class Plateau{
    public static final int LARGEUR = 8;
    public static final int HAUTEUR = 20;
    //This will be a matrix of colours (enum for each block)
    private Couleur plateauActuel[][];
    private Couleur plateauSuivant[][];
    private int[][] current_pos = new int[2][4];
    private int[][] next_pos = new int[2][4];
    private Affichage affi;
    private TypeBloc blocCourant;
	Joueur joueur;

    public Plateau(){
        plateauActuel = new Couleur[HAUTEUR][LARGEUR];
        plateauSuivant = new Couleur[HAUTEUR][LARGEUR];
        for(int i = 0; i<HAUTEUR;i++){
            for(int j=0;j<LARGEUR;j++){
                plateauActuel[i][j] = Couleur.EMPTY;
                plateauSuivant[i][j] = Couleur.EMPTY;
                //Replace this with enum colour
            }
        }
		joueur = new Joueur("test");
    }
    
    public boolean ajouterBloc(TypeBloc bloc) {
    	//On récupère les coordonnées de bases
    	this.blocCourant = bloc;
    	this.current_pos = bloc.getCoordonnees();
    	this.next_pos = bloc.getCoordonnees();
    	//Dispo dans le tableau actuel ?
    	if (this.positionsSuivantesLibres() == false) {
			return false;
		}
    	//On l'ajoute dans le tableau suivant
    	this.colourCases();
    	return true;
    }

    public String toString(){
        String res = "";
        for (int i = 0; i < this.plateauSuivant.length; i++) {
			for (int j = 0; j < this.plateauSuivant[i].length; j++) {
				if (this.plateauSuivant[i][j] == Couleur.EMPTY) {
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
    	//On regarde les coordonnées de chaque points
    	for (int i = 0; i < this.next_pos.length; i++) {
    		int numLigne = this.next_pos[i][0];
    		int numColonne = this.next_pos[i][1];
    		if (numLigne >= HAUTEUR || numColonne >= LARGEUR) {
				return false;
			}
    		if (this.plateauActuel[numLigne][numColonne] != Couleur.EMPTY) {
				return false;
			}
		}
    	return true;
     }

    private void emptyCases(){
    	for (int i = 0; i < this.current_pos.length; i++) {
    		int numLigne = this.current_pos[i][0];
    		int numColonne = this.current_pos[i][1];
    		this.plateauSuivant[numLigne][numColonne] = Couleur.EMPTY;
		}
    }

    private void colourCases(){
        for (int i = 0; i < this.next_pos.length; i++) {
    		int numLigne = this.next_pos[i][0];
    		int numColonne = this.next_pos[i][1];
    		this.plateauSuivant[numLigne][numColonne] = this.blocCourant.getCouleur();
		}
    }
    
    private int[][] calculerPositionsSuivante(int[][] positionsPrecedentes) {
    	int[][] result = new int[4][2];
    	for (int i = 0; i < result.length; i++) {
			result[i][0] = 1 + positionsPrecedentes[i][0];
			result[i][1] = positionsPrecedentes[i][1];
		}
    	return result;
    }
    
     public boolean goDown(){
    	 //Calcul des positions suivantes
    	 this.next_pos = this.calculerPositionsSuivante(this.current_pos);
    	 
    	 if (this.positionsSuivantesLibres() == false) {
    		 //Fin de la chute, on copie le plateau suivant
    		 this.plateauActuel = this.plateauSuivant;
			return false;
		}

        emptyCases();
        colourCases();
        this.current_pos = this.next_pos;
        return true;
    }

	
	///////////////////////// Lignes Pleines ////////////////////////////

	public boolean lignePleine(int numLigne){
        boolean pleine = true;
        int i = 0;
        while(pleine && i < LARGEUR){
            if(plateauActuel[i][numLigne] == Couleur.EMPTY){
                pleine = false;
            }else{
                i++;
            }
        }

        return pleine;
    }

    //Vide ligne
    public void videLigne(int numLigne){
        for(int i = 0; i< LARGEUR;i++){
            plateauActuel[i][numLigne] = Couleur.EMPTY;
        }
    }

    //Move rest down one
    public void descenteLignes(int numLigne){
        for(int i = numLigne;i>0;i--){
            for(int j = 0 ; j<LARGEUR;j++){
                plateauActuel[j][i] = plateauActuel[j][i-1];
            }
        }
    }

    //Loop through all the lines - count number of full lines for points later
    public int verifiePlateau(){
        int nbLignes = 0;
        for(int i=0;i<HAUTEUR;i++){
            if(lignePleine(i)){
                videLigne(i);
                descenteLignes(i);
                nbLignes++; 
            }
        }
        return nbLignes;
    }

    // public void displayPlateau(){
    //     for(int i=0;i<HAUTEUR;i++){
    //         System.out.print(i + ". ");
    //         for(int j=0;j<LARGEUR;j++){
    //             System.out.println(plateauActuel[j][i]);
    //         }
    //         System.out.println();
    //     }
    // }

	//Pour faire la partie score, on a besoin d'un joueur

	public int calculateScore(int oldScore){
        int toAdd = verifiePlateau();
        switch(toAdd){
            case 1:
                toAdd = 100;
            case 2:
                toAdd = 300;
            case 3:
                toAdd = 500;
            case 4:
                toAdd = 800;
        }

        joueur.setScore(oldScore + toAdd);
        return oldScore + toAdd;
    }

     





}