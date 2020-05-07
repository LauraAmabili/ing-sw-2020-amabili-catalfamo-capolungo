package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

public class NicknameResponseOk extends Message {


    NicknameResponseOk(String senderUsername, String token) {
        super(senderUsername, token);
    }



    @Override
    public void accept(Visitor gameMessageVisitor) {
        gameMessageVisitor.visit(this);
    }

    //player Added
}
