package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class ConnectionRequest extends MessageToClient {
    ConnectionRequest(String senderUsername, String token) {
        super(senderUsername, token);
    }

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
