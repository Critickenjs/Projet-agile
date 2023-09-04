
public class DebutJeu{

    public DebutJeu(){
        System.out.println("hi");
    }

    public static void main(String[] args) {
        Plateau plateauActuel = new Plateau();
        boolean chuteOk = false;
        boolean partieFini = false;
        while (!partieFini) {
        	//Ajout d'une pièce
        	plateauActuel.ajouterPiece(new Bloc(1));
        	while (!chuteOk) {
        		//On avance la pièce
        		chuteOk = plateauActuel.goDown();
        		Thread.sleep(1000);
    		}
        	partieFini = plateauActuel.partieEstFini();
		}
    }
}