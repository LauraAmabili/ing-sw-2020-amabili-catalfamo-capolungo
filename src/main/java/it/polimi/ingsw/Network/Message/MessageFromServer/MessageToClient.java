package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;
import java.io.Serializable;

public abstract class MessageToClient implements Serializable {


    public MessageToClient() {
    }


    public abstract void accept(VisitorClient gameMessageVisitorClient) throws IOException;

}
