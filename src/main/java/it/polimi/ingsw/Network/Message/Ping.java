package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public class Ping extends MessageToClient {

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

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {

    }
}
