package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public class ConnectionRequest extends MessageToClient {

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {

    }

    /*
    boolean isConnected;

    public ConnectionRequest(String senderUsername, boolean isConnected) {
        super(senderUsername, MessageContent.CONNECTION);
        this.isConnected = isConnected;
    }

    @Override
    public String toString() {
        return "ConnectionRequest{" + "senderUsername = " + getSenderUsername() + ", content = " + getContent() + "}";
    }


     */
}
