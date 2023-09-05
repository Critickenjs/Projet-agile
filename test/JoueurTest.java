import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.AssertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JoueurTest {

    @BeforeEach
	void initialization() {
        Joueur j1 = new Joueur("toto");
        Joueur j2 = new Joueur("tata");
        Joueur j3 = new Joueur("titi");
        Joueur j4 = new Joueur("toto");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./res/sauvegarde.txt")))){
            bw.write(""); // Efface toutes les sauvegardes
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void setScoreTest(){
        j1.setScore(110);
        AssertTrue(j1.getScore == 110);
    }

    @Test
    void sauvegardeAvecNomDoubleSuperieurTest(){
        j1.setScore(110);
        j2.setScore(120);
        j3.setScore(130);
        j4.setScore(140);
        AssertEquals(Joueur.recupeScore(),"toto:140\ntiti:130\ntata:120\n");
    }

    @Test
    void sauvegardeAvecNomDoubleInferieurTest(){
        j1.setScore(110);
        j2.setScore(120);
        j3.setScore(130);
        j4.setScore(100);
        AssertEquals(Joueur.recupeScore(),"titi:130\ntata:120\ntoto:110\n");
    }

    @Test
    void sauvegardeAvecNomDoubleEgaleTest(){
        j1.setScore(110);
        j2.setScore(120);
        j3.setScore(130);
        j4.setScore(110);
        AssertEquals(Joueur.recupeScore(),"titi:130\ntata:120\ntoto:110\n");
    }
}
