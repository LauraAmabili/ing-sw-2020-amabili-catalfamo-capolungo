package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class SetCardTimeUpdate extends MessageFromServer {

    private final PlayerInterface currentPlayer;

    public SetCardTimeUpdate(PlayerInterface currentPlayer) {

        this.currentPlayer = currentPlayer;
    }

    public PlayerInterface getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);

    }
}
