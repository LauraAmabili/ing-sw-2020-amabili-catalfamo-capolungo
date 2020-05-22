package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChooseYourWorkerResponse extends MessageFromClient {



    final String worker;

    public ChooseYourWorkerResponse(String worker) {
        this.worker = worker;
    }

    public String getWorker() {
        return worker;
    }


    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
