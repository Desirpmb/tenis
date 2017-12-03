package seriali;

import java.io.Serializable;

public class Message  implements Serializable {

    private int code;
    private String donnee[][];

    public Message (int code, String[][] donnee){
        this.code = code;
        this.donnee = donnee;
    }
    public Message(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String[][] getDonnee() {
        return donnee;
    }

    public void setDonnee(String[][] donnee) {
        this.donnee = donnee;
    }
}
