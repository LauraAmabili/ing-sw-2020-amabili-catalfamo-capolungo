package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BuildRequest extends MessageFromServer {


    final int worker;

    public BuildRequest(int worker) {
        this.worker = worker;
    }

    public int getWorker() {
        return worker;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
