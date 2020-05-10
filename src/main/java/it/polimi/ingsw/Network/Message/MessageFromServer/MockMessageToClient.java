package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class MockMessageToClient extends MessageToClient {
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {

    }

    public String string;

    public MockMessageToClient(String string) {

        this.string = string;
    }
}
