package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class PlayerTurnUpdate extends MessageFromServer {


    private final PlayerInterface player;

    public PlayerTurnUpdate(PlayerInterface player) {
        this.player = player;
    }

    public PlayerInterface getPlayer() {
        return player;
    }

    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
    }
}
