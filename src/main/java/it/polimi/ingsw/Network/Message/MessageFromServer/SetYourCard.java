package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class SetYourCard extends MessageToClient {


    List<String> chosenGods;

    public SetYourCard(List<String> chosenGods) {
        this.chosenGods = chosenGods;
    }

    public List<String> getChosenGods() {
        return chosenGods;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {

    }
}
