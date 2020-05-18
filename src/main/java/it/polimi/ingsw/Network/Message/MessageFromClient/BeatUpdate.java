package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class BeatUpdate extends MessageFromClient{


    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
