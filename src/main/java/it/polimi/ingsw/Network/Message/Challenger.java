package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class Challenger extends Message {

    Challenger(String senderUsername, String token) {
        super(senderUsername, token);
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }

    //sending to all the client he name of the chosen Challenger
}
