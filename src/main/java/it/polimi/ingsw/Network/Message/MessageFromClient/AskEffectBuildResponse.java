package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class AskEffectBuildResponse extends MessageFromClient {

    final String effect;
    final int worker;
    final String playerNickname;

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

    /**
     * Calls the correspondent visit method based on the type of Message from the Client
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);
    }
}
