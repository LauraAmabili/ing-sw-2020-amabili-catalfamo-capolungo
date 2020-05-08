package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public abstract class MessageToServer {

    private final String senderUsername;
    private final String token;

    public MessageToServer(String senderUsername, String token) {
        this.senderUsername = senderUsername;
        this.token = token;

    }

    public String getSenderUsername() {
        return senderUsername;
    }


    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Message{" +
            "senderUsername='" + senderUsername + '\'' +
            '}';
    }

    public abstract void accept(VisitorServer gameMessageVisitorClient) throws IOException;

}
