package it.polimi.ingsw.Network.Message;

public class ConnectionRequest extends Message{
    ConnectionRequest(String senderUsername, String token) {
        super(senderUsername, token);
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
