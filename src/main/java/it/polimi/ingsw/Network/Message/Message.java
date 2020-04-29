package it.polimi.ingsw.Network.Message;

import java.io.Serializable;

public abstract class Message implements Serializable {
    private static final long serialVersionUID = -5411382756213360684L;

    private final String senderUsername;
    private final String token;
    private final MessageData data;

    Message(String senderUsername, String token, MessageData data) {
        this.senderUsername = senderUsername;
        this.token = token;
        this.data = data;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public MessageData getContent() {
        return data;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "Message{" +
                "senderUsername='" + senderUsername + '\'' +
                ", content=" + data +
                '}';
    }
}