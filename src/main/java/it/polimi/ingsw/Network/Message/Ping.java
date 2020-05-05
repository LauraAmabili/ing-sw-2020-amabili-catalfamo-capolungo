package it.polimi.ingsw.Network.Message;

import java.io.Serializable;

public class Ping implements Serializable {
    String sender;

    public Ping(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        return "Ping{" +
                "sender='" + sender + '\'' +
                '}';
    }
}
