package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class MockMessageToServer extends MessageToServer {
    public MockMessageToServer(String string) {
        this.string = string;
    }


    public String string;

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {

    }
}
