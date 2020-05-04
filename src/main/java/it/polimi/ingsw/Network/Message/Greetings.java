package it.polimi.ingsw.Network.Message;

public class Greetings extends Message {
    private int a = 0;
    public Greetings(String senderUsername, String token, int a) {
        super(senderUsername, token);
        this.a=a;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Greetings{" +
                "a=" + a +
                '}';
    }


}
