package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class CardSetUpdate extends MessageFromServer {

    String currentPlayer;
    String godName;

    public CardSetUpdate(String currentPlayer, String godName ) {
        this.currentPlayer = currentPlayer;
        this.godName = godName;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getGodName() {
        return godName;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);

    }
}
