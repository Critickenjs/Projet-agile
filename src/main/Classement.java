package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Classement {
    private static final File FICHIER = new File("./res/sauvegarde.txt");

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
