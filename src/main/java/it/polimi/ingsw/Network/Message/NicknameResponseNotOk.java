package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public class NicknameResponseNotOk extends MessageToClient {


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);
    }

    //user nickname not correct ( already used )
}
