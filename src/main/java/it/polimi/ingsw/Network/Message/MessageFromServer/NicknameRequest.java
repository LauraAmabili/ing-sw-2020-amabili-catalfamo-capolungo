package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

import java.io.IOException;

public class NicknameRequest extends MessageToClient {


    public NicknameRequest(String target) {
        super(target);
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }

    //asking the Client to insert nickname
}
