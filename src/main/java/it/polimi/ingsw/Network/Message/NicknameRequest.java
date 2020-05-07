package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

public class NicknameRequest extends Message{

    NicknameRequest(String senderUsername, String token) {
        super(senderUsername, token);
    }


    @Override
    public void accept(Visitor gameMessageVisitor) {
            gameMessageVisitor.visit(this);
    }

    //asking the Client to insert nickname
}
