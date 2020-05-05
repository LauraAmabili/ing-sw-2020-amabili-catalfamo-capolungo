package it.polimi.ingsw.Network.Message;

public class NumberOfPlayers extends Message {




    int numberOfPlayers;

    String MessageContent = "Number of Players";




    public NumberOfPlayers(String senderUsername, String token, int numberOfPlayers) {
        super(senderUsername, token);
        this.numberOfPlayers = numberOfPlayers;
    }




    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }


    @Override
    public String toString(){
        return "numberOfPlayers (" + numberOfPlayers + ") set by " + getSenderUsername();
    }

}
