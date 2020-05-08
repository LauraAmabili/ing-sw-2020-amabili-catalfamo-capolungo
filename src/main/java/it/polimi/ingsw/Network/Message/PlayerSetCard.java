package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class PlayerSetCard extends MessageToClient {

    PlayerSetCard(String senderUsername, String token) {
        super(senderUsername, token);
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
    }
}
