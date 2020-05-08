package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class NumberOfPlayerResponse extends MessageToServer {


    int numberOfPlayers;

    public NumberOfPlayerResponse(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorServer) throws IOException {
        gameMessageVisitorServer.visit(this);
    }
}
