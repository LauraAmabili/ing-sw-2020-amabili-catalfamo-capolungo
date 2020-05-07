package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

public class Challenger extends Message {

    Challenger(String senderUsername, String token) {
        super(senderUsername, token);
    }

    @Override
    public void accept(Visitor gameMessageVisitor) {
        gameMessageVisitor.visit(this);

    }

    //sending to all the client he name of the chosen Challenger
}
