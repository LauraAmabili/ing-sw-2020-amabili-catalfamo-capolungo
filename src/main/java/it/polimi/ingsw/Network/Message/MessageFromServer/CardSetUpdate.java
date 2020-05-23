package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class CardSetUpdate extends MessageFromServer {

    private final PlayerInterface currentPlayer;
    private final String godName;

    public CardSetUpdate(PlayerInterface currentPlayer, String godName ) {
        this.currentPlayer = currentPlayer;
        this.godName = godName;
    }

    public PlayerInterface getCurrentPlayer() {
        return currentPlayer;
    }

    public String getGodName() {
        return godName;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }
}
