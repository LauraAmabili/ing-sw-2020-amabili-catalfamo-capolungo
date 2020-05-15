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

    @Override
    public void accept(VisitorServer gameMessageVisitorServer) throws IOException, InterruptedException {
        gameMessageVisitorServer.visit(this);
    }
}
