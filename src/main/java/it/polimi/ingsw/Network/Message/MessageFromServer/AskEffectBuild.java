package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class AskEffectBuild extends MessageFromServer {

    final int worker;

    public int getWorker() {
        return worker;
    }

    public AskEffectBuild(int worker) {
        this.worker = worker;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
