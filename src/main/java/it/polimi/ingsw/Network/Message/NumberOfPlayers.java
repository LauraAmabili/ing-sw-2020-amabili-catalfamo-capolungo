package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

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

    @Override
    public void accept(Visitor gameMessageVisitor) {

    }

}
