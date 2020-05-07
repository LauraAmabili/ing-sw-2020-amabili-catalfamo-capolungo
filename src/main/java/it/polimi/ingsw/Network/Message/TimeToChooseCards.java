package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

public class TimeToChooseCards extends Message {

    TimeToChooseCards(String senderUsername, String token) {
        super(senderUsername, token);
    }

    @Override
    public void accept(Visitor gameMessageVisitor) {
        gameMessageVisitor.visit(this);

    }
}
