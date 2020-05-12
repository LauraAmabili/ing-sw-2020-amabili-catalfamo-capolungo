package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BuildTwoInputRequest extends MessageFromServer {

    int worker;

    public int getWorker() {
        return worker;
    }

    public BuildTwoInputRequest(int worker) {
        this.worker = worker;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
