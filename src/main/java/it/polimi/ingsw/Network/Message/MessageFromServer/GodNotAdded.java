package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class GodNotAdded extends MessageToClient {


    public GodNotAdded(String target) {
        super(target);
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
