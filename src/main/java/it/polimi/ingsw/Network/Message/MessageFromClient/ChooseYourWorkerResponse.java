package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChooseYourWorkerResponse extends MessageToServer {



    int worker;

    public ChooseYourWorkerResponse(int worker) {
        this.worker = worker;
    }

    public int getWorker() {
        return worker;
    }


    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
