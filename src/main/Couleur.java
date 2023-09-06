public enum Couleur {
    EMPTY(""), BLUE("\033[0;34m"), RED("\033[0;31m"), GREEN("\033[0;32m"), YELLOW("\033[0;33m"), PURPLE("\033[0;35m"), ORANGE(""), CYAN("\033[0;36m");
    String car  = "■";

    Couleur(String color){
        this.car = color;
    }
    public String toString(){
    	return "";
    }
}