package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class AvailableGodsUpdate extends MessageFromServer {




    final List<String> cards;


    public AvailableGodsUpdate(List<String> cards) {

        this.cards = cards;
    }

    public List<String> getCards() {
        return cards;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);
    }
}
