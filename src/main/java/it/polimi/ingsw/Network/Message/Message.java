package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

import java.io.Serializable;

public abstract class Message implements Serializable {


    private final String senderUsername;
    private final String token;

    Message(String senderUsername, String token) {
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

    public abstract void accept(Visitor gameMessageVisitor);
}
