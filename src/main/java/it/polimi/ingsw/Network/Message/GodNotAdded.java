package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class GodNotAdded extends MessageToClient {


    GodNotAdded(String senderUsername, String token) {
        super(senderUsername, token);
    }



    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }
}
