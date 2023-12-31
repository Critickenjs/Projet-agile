package main;
import deplacement.Deplacement;
import deplacement.DeplacementBas;
import deplacement.DeplacementDroite;

public class Plateau {
    public static final int LARGEUR = 12;
    public static final int HAUTEUR = 20;
    //This will be a matrix of colours (enum for each block)
    private Couleur plateauActuel[][];
    private Couleur plateauSuivant[][];
    private int[][] current_pos = new int[2][4];
    private int[][] next_pos = new int[2][4];
    private TypeBloc blocCourant;
    private Joueur joueur;
    
    public Plateau(String joueur_nom){
        plateauActuel = new Couleur[HAUTEUR][LARGEUR];
        plateauSuivant = new Couleur[HAUTEUR][LARGEUR];
        for(int i = 0; i<HAUTEUR;i++){
            for(int j=0;j<LARGEUR;j++){
                plateauActuel[i][j] = Couleur.EMPTY;
                plateauSuivant[i][j] = Couleur.EMPTY;
                //Replace this with enum colour
            }
        }
        joueur = new Joueur(joueur_nom);
    }
    
    public boolean ajouterBloc(TypeBloc bloc) {
    	//On récupère les coordonnées de bases
    	this.blocCourant = bloc;
    	this.current_pos = bloc.getCoordonnees();
    	this.next_pos = bloc.getCoordonnees();
    	//Dispo dans le tableau actuel ?
    	if (this.positionsSuivantesLibres() == false) {
			return false;
		} else {
			//On l'ajoute dans le tableau suivant
	    	this.colourCases();
	    	return true;
		}
    	
    }
    
    public String toString() {
    	String res = "[q]";
    	int largeurCote = (Affichage.LARGEUR_JEU - LARGEUR - 4) / 2;
    	//Pour chaque ligne du plateau
    	for (int i = 0; i <= this.plateauSuivant.length; i++) {
    		//bouton quitter
			//On insert des espaces pour le cote gauche
    		if (i == 0) {
    			for (int j = 0; j <= largeurCote - 3; j++) {
    				res += " ";
    			}
			} else {
				for (int j = 0; j <= largeurCote; j++) {
					res += " ";
				}
			}
    		
    		//ajout des blocs
    		if (i < this.plateauSuivant.length) {
    			//bordure gauche
        		res += "▌";
    			for (int j = 0; j < this.plateauSuivant[i].length; j++) {
        			if (this.plateauSuivant[i][j] == Couleur.EMPTY) {
    					res += " ";
    				} else {
    					res += this.plateauSuivant[i][j].toString();
    				}
    			}
        		//bordure droite
        		res += "▐\r\n";
			} else {
				//ajout bordure du bas
				for (int j = 0; j <= LARGEUR + 1; j++) {
					res += "▔";
				}
				res += "\r\n\r\n";
			}
		}
    	res += "  [←]\t      [␣]\t [→]";
    	res += "\r\n\r\n";
    	res += "     \t      [↓]";
    	res += "\r\n\r\n";
    	res += joueur.getScore() + " pts";
    	return res;
    }
/*
    public String toString(){
        String res = "";
        for (int i = 0; i < this.plateauSuivant.length; i++) {
			res += "\t\t |";
            for (int j = 0; j < this.plateauSuivant[i].length; j++) {
                if (this.plateauSuivant[i][j] == Couleur.EMPTY) {
                    res += " ";
                } else {
                    res += this.plateauSuivant[i][j].toString();
                }
            
			}

            res += "| ";
            // if(i == 2 || i == 4){
            //     res += "\t --------";
            // }else if(i==3){
            //     res += "\t | " + joueur.getScore() + " ";
            // }
			res += "\r\n";
		}
        res += " ------- \r\n " + joueur.getScore() + "\r\n -------";
         return res;
     }*/

    private boolean positionsSuivantesLibres(){
    	//On regarde les coordonnées de chaque points
    	for (int i = 0; i < this.next_pos.length; i++) {
    		int numLigne = this.next_pos[i][0];
    		int numColonne = this.next_pos[i][1];
    		if (numLigne >= HAUTEUR || numColonne >= LARGEUR || numLigne < 0 || numColonne < 0) {
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
    
    // private int[][] calculerPositionsSuivante(int[][] positionsPrecedentes) {
    // 	int[][] result = new int[4][2];
    // 	for (int i = 0; i < result.length; i++) {
	// 		result[i][0] = 1 + positionsPrecedentes[i][0];
	// 		result[i][1] = positionsPrecedentes[i][1];
	// 	}positionsSuivantesLibres
    // 	return result;
    // }
    
    private void copiePlateau() {
    	for (int i = 0; i < this.plateauActuel.length; i++) {
			for (int j = 0; j < plateauActuel[i].length; j++) {
				this.plateauActuel[i][j] = this.plateauSuivant[i][j];
			}
		}
    }

    public void rotation(int ligne, int colonne){
        Deplacement d1 = new DeplacementBas();
        Deplacement d2 = new DeplacementDroite();
        // int[][] temp = this.next_pos;
        for(int l = 0;l<colonne;l++){
            this.next_pos = d2.deplacement(this.next_pos, blocCourant);
        }
        for(int k = 0;k<ligne;k++){
            this.next_pos = d1.deplacement(this.next_pos, blocCourant);
        }
    }
    
    public boolean deplacement(Deplacement d){
        //Calcul des positions suivantes
       if(d.estRotation()){
            int ligne = HAUTEUR;
            int colonne = LARGEUR;
            // boolean found = false;
            // int i = 0;
            // while(!found && i<HAUTEUR){
            //     int j = 0;
            //     while(!found && j<LARGEUR){
            //         if(plateauActuel[i][j]!=Couleur.EMPTY){
            //             ligne = i;
            //             colonne = j;
            //         }
            //         j++;
            //     }
            //     i++;
            // }
            for(int i=0;i<current_pos.length;i++){
                int numLigne = this.current_pos[i][0];
                int numColonne = this.current_pos[i][1];
                if(numLigne<ligne){
                    ligne = numLigne;
                }
                if(numColonne<colonne){
                    colonne = numColonne;
                }
            }


            this.next_pos = d.deplacement(current_pos, blocCourant);
            rotation(ligne, colonne);
            if (this.positionsSuivantesLibres() == true) {
                this.blocCourant = this.blocCourant.getPieceSuivante();
            }
            

            // // GET NEW SHAPE AT THE TOP OF THE PLATEAU

            // return this.deplacement(d1);
        }else{
            this.next_pos = d.deplacement(current_pos, this.blocCourant);
            //  this.next_pos = this.calculerPositionsSuivante(current_pos); 
        }
        if (this.positionsSuivantesLibres() == false) {
            return false;
        }
        emptyCases();
        colourCases();
        this.current_pos = this.next_pos;
        return true;
        
    }
    
    public boolean goDown() {
    	if (this.deplacement(new DeplacementBas()) == false) {
    		//Fin de la chute, on copie le plateau suivant
   		 	this.copiePlateau();
   		 	return false;
		}
    	return true;
    }

    //  public boolean goDown(){
    // 	 //Calcul des positions suivantes
    //      this.next_pos = deplacementBas.deplacement(current_pos);
    // 	//  this.next_pos = this.calculerPositionsSuivante(current_pos); 
    // 	 if (this.positionsSuivantesLibres() == false) {
    // 		 //Fin de la chute, on copie le plateau suivant
    // 		 this.copiePlateau();
	// 		return false;
	// 	}

    //     emptyCases();
    //     colourCases();
    //     this.current_pos = this.next_pos;
    //     return true;
    // }

    // public boolean goRight(){
    //     this.next_pos = deplacementDroite.deplacement(current_pos);
    //     if (this.positionsSuivantesLibres() == false) {
    // 		 //Fin de la chute, on copie le plateau suivant
    // 		 this.copiePlateau();
	// 		return false;
	// 	}

    //     emptyCases();
    //     colourCases();            for(int i = 0;i<HAUTEUR;i++){
    //     this.current_pos = this.next_pos;
    //     return true;
    // }

    // public boolean goLeft(){
    //     this.next_pos = deplacementGauche.deplacement(current_pos);
    //     if (this.positionsSuivantesLibres() == false) {
    // 		 //Fin de la chute, on copie le plateau suivant
    // 		 this.copiePlateau();
	// 		return false;
	// 	}

    //     emptyCases();
    //     colourCases();
    //     this.current_pos = this.next_pos;
    //     return true;
    // }
     
 	///////////////////////// Lignes Pleines ////////////////////////////
     /// Pour commencer la verification des lignes, appel Calculat

    public boolean lignePleine(int numLigne){
        boolean pleine = true;
        int i = 0;
        while(pleine && i < LARGEUR){
            if(plateauActuel[numLigne][i] == Couleur.EMPTY){
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
            plateauSuivant[numLigne][i] = Couleur.EMPTY;
        }
    }

   //Move rest down one
     public void descenteLignes(int numLigne){
         for(int i = numLigne;i>0;i--){
             for(int j = 0 ; j<LARGEUR;j++){
                 plateauSuivant[i][j] = plateauSuivant[i-1][j];
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
         this.copiePlateau();
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

     public int calculateScore(){
         int oldScore = joueur.getScore();
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