package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageToClient;

import java.io.IOException;

public class NumberOfPlayersRequest extends MessageToClient {


    public NumberOfPlayersRequest(String senderUsername, String token) {
        super(senderUsername, token);

    }


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }

}
