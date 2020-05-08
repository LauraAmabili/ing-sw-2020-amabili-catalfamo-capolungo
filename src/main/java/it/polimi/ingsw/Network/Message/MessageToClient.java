package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;
import java.io.Serializable;

public abstract class MessageToClient implements Serializable {


    private final String senderUsername;
    private final String token;

    public MessageToClient(String senderUsername, String token) {
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

    public abstract void accept(VisitorClient gameMessageVisitorClient) throws IOException;
}