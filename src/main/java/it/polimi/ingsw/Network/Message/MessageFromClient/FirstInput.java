package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class FirstInput extends MessageToServer {

    String input;

    public FirstInput(String input) {
        this.input = input;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
    gameMessageVisitorClient.visit(this);
    }
}
