package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class AskEffectReply extends MessageFromClient {

    String effect;
    String playerNickname;

    public String getEffect() {
        return effect;
    }
    public String getPlayerNickname() {return playerNickname;}

    public AskEffectReply(String effect, String playerNickname) {
        this.effect = effect;
        this.playerNickname = playerNickname;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}