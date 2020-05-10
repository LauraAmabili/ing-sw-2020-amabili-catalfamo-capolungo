package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class WrongPositionForWorker extends MessageToClient {


    int worker;

    public WrongPositionForWorker(int worker, String target) {
        super(target);
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
