import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Joueur {
    private int score;
    private final String NOM;
    private static final File FICHIER = new File("./res/sauvegarde.txt");

    Joueur(String nom){
        this.NOM = nom;
        this.score = 0;
    }

    public void setScore(int nouveauScore){
        this.score = nouveauScore;
    }

    public int getScore() {
        return score;
    }

    public String getNom() {
        return this.NOM;
    }

    public void sauvegardeScore(){
        String scoreSuperieur = "";
        String scoreInferieur = "";
        boolean ecrireNouvelleValeur = true;
        try (Scanner sc = new Scanner(FICHIER);) {
            Boolean estSuperieur = true;
            String ligne;
            while(sc.hasNextLine() && estSuperieur){
                ligne = sc.nextLine();
                if (ligne.split(";").length == 2){
                    if (score <= Integer.parseInt(ligne.split(";")[1])){
                        if (NOM.equals(ligne.split(";")[0])){
                            ecrireNouvelleValeur = false;
                            estSuperieur = false;
                        }
                        scoreSuperieur += ligne+"\n";
                    } else {
                        estSuperieur = false;
                        scoreInferieur += ligne+"\n";
                    }
                } else if (ligne == ""){
                    scoreSuperieur += ligne+"\n";
                }
            }
            while(sc.hasNextLine() && !estSuperieur){
                ligne = sc.nextLine();
                if(ligne.split(";").length == 2 && !NOM.equals(ligne.split(";")[0])){
                    scoreInferieur += ligne+"\n";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FICHIER))){
            if (ecrireNouvelleValeur){
                bw.write(scoreSuperieur+NOM+";"+score+"\n"+scoreInferieur);
            } else {
                bw.write(scoreSuperieur+scoreInferieur);
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String recupeScore(){
        String content = "";
        try (Scanner sc = new Scanner(FICHIER);) {
            while(sc.hasNextLine()){
                content += sc.nextLine()+"\n";
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content;
    }
}