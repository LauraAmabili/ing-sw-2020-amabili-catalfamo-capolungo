package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class PlayerNumberResponse extends MessageFromClient {


    int numberOfPlayers;

    public PlayerNumberResponse(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorServer) throws IOException, InterruptedException {
        gameMessageVisitorServer.visit(this);
    }
}
