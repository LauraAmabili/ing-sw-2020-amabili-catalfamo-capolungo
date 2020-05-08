package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public class Challenger extends MessageToClient {


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }

    //sending to all the client he name of the chosen Challenger
}
