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


    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
    }
}
