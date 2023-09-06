package main;
public enum Couleur {
    EMPTY(""), BLUE("\033[0;34m"), RED("\033[0;31m"), GREEN("\033[0;32m"), YELLOW("\033[0;33m"), PURPLE("\033[0;35m"), ORANGE("\033[0;37m"), CYAN("\033[0;36m");
    String car  = "◙";
    String color;

    Couleur(String color){
        this.color = color;
    }
    public String toString() {
    	return this.color + this.car;
    }
}