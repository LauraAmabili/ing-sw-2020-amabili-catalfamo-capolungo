package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class CardAddedUpdate extends MessageFromServer {


    final List<String> addedGods;

    public CardAddedUpdate(List<String> addedGods) {

        this.addedGods = addedGods;
    }

    public List<String> getAddedGods() {
        return addedGods;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }
}
