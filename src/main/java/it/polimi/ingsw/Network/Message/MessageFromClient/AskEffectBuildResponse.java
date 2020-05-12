package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class AskEffectBuildResponse extends MessageFromClient {

    String effect;
    int worker;
    String playerNickname;

    public String getEffect() {
        return effect;
    }
    public String getPlayerNickname() {return playerNickname;}
    public int getWorker() {
        return worker;
    }

    public AskEffectBuildResponse(String effect, String playerNickname, int worker) {
        this.effect = effect;
        this.playerNickname = playerNickname;
        this.worker = worker;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
