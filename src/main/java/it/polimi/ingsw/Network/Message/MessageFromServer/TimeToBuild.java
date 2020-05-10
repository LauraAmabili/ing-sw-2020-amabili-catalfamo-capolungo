package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class TimeToBuild extends MessageToClient {



    String current;

    public TimeToBuild(String current) {
        this.current = current;
    }

    public String getCurrent() {
        return current;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
