
public class DebutJeu{

    public DebutJeu(){
        System.out.println("hi");
    }

    public static void main(String[] args) {
    	Affichage affichageCourant = new Affichage(Plateau.TAILLE+2, Plateau.LARGEUR+2);
    	affichageCourant.init();
        Plateau plateauActuel = new Plateau();
        boolean ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.Carre);
        plateauActuel.ajouterBloc(TypeBloc.Carre);
        affichageCourant.rafraichir(plateauActuel.toString());
      //Tant que l'on peut ajouter une pièce
        while (ajoutBlocOk) {
        	//On avance la pièce tant que l'on peut
        	boolean piecePeutAvancer = plateauActuel.goDown();
        	while (piecePeutAvancer) {
        		affichageCourant.rafraichir(plateauActuel.toString());
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		piecePeutAvancer = plateauActuel.goDown();
    		}
        	//Ajout d'une pièce
        	ajoutBlocOk = plateauActuel.ajouterBloc(TypeBloc.Carre);
		}
    }
}