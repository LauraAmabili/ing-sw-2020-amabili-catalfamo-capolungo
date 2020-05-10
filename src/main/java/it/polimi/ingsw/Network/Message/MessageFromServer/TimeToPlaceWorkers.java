package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class TimeToPlaceWorkers extends MessageToClient {


    String currentPlayer;

    public TimeToPlaceWorkers(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);

    }
}
