package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class AskEffectReply extends MessageFromClient {

    final String effect;
    final String playerNickname;

    public String getEffect() {
        return effect;
    }
    public String getPlayerNickname() {return playerNickname;}

    public AskEffectReply(String effect, String playerNickname) {
        this.effect = effect;
        this.playerNickname = playerNickname;
    }

    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient)  {
        gameMessageVisitorClient.visit(this);
    }
}
