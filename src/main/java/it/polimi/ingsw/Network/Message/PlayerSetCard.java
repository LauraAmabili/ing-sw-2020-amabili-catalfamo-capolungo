package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

public class PlayerSetCard extends Message {

    PlayerSetCard(String senderUsername, String token) {
        super(senderUsername, token);
    }

    @Override
    public void accept(Visitor gameMessageVisitor) {
            gameMessageVisitor.visit(this);
    }
}
