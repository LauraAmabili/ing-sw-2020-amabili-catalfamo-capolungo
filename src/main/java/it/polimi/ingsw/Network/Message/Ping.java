package it.polimi.ingsw.Network.Message;

import java.io.Serializable;

public class Ping extends Message{

    String sender;

    public Ping(String senderUsername, String token, String sender) {
        super(senderUsername, token);
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Ping{" +
                "sender='" + sender + '\'' +
                '}';
    }
}
