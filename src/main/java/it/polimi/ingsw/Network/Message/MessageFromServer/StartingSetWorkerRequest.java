package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class StartingSetWorkerRequest extends MessageFromServer {


    final int worker;

    public StartingSetWorkerRequest(int worker) {

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
