package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public abstract class MessageToServer {


    public abstract void accept(VisitorServer gameMessageVisitorClient) throws IOException;

}
