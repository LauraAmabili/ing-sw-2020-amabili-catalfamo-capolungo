package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class CardNotPresent extends MessageToClient{


    public CardNotPresent(String target) {
        super(target);
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
