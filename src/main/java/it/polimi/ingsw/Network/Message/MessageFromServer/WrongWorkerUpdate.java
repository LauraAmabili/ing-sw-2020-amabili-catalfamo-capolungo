package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class WrongWorkerUpdate extends MessageFromServer {

    final int worker;

    public WrongWorkerUpdate(int worker) {
        this.worker = worker;
    }

    public int getWorker() {
        return worker;
    }


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {

    }
}
