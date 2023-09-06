package main;
public enum Couleur {
    EMPTY(""), BLUE("\033[0;44m"), RED("\033[0;41m"), GREEN("\033[0;42m"), YELLOW("\033[0;43m"), PURPLE("\033[0;45m"), ORANGE("\033[0;47m"), CYAN("\033[0;46m");
    String car  = " ";
    String color;

    Couleur(String color){
        this.color = color;
    }
    public String toString() {
    	return this.color + this.car + "\033[0;0m";
    }
}