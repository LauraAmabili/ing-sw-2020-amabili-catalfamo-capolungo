package it.polimi.ingsw.Network.Message;

import java.io.Serializable;

public abstract class Message implements Serializable {

    private final String senderUsername;
    private final MessageData data;

    Message(String senderUsername, MessageData data) {
        this.senderUsername = senderUsername;
        this.data = data;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public MessageData getContent() {
        return data;
    }


}