package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class CardsName extends MessageToClient {




    List<String> cards;


    public CardsName(List<String> cards) {
        this.cards = cards;
    }

    public List<String> getCards() {
        return cards;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {

    }
}
