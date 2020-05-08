package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.Message;

public class NumberOfPlayerResponse extends Message {


    int numberOfPlayers;

    public NumberOfPlayerResponse(String senderUsername, String token, int numberOfPlayers) {
        super(senderUsername, token);
        this.numberOfPlayers = numberOfPlayers;

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }
}
