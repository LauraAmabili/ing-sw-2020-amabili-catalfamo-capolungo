package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BuildTimeUpdate extends MessageFromServer {



    String current;

    public BuildTimeUpdate(String current) {
        this.current = current;
    }

    public String getCurrent() {
        return current;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
