package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class AvailableGodsUpdate extends MessageFromServer {




    List<God> cards;


    public AvailableGodsUpdate(List<God> cards) {

        this.cards = cards;
    }

    public List<God> getCards() {
        return cards;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
