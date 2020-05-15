package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BuildTimeUpdate extends MessageFromServer {



    String currentPlayer;

    public BuildTimeUpdate(String current) {
        this.currentPlayer = current;
    }

    public String getCurrent() {
        return currentPlayer;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
