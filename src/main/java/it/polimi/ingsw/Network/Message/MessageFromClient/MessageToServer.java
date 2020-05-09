package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;
import java.io.Serializable;

public abstract class MessageToServer implements Serializable {


    public abstract void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException;

}
