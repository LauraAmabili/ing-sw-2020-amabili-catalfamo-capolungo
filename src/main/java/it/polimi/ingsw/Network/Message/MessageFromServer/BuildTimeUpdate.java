package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BuildTimeUpdate extends MessageFromServer {



    final String currentPlayer;

    public BuildTimeUpdate(String current) {
        this.currentPlayer = current;
    }

    public String getCurrent() {
        return currentPlayer;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
    }
}
