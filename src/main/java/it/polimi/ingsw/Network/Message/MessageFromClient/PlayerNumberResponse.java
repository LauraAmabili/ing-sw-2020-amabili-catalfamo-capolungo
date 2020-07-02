package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class PlayerNumberResponse extends MessageFromClient {


    String numberOfPlayers;

    public PlayerNumberResponse(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

    }

    public String getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(String numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
