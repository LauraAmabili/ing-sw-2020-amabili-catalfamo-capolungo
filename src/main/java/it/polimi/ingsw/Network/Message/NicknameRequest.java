package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class NicknameRequest extends Message{

    NicknameRequest(String senderUsername, String token) {
        super(senderUsername, token);
    }


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
    }

    //asking the Client to insert nickname
}
