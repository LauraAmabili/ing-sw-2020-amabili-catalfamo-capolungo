package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChooseYourWorkerEffectResponse extends MessageFromClient{

    boolean effect;
    int Worker;

    public boolean isEffect() {
        return effect;
    }

    public void setEffect(boolean effect) {
        this.effect = effect;
    }

    public int getWorker() {
        return Worker;
    }

    public void setWorker(int worker) {
        Worker = worker;
    }

    public ChooseYourWorkerEffectResponse(int worker, boolean effect) {
        this.effect = effect;
        Worker = worker;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
