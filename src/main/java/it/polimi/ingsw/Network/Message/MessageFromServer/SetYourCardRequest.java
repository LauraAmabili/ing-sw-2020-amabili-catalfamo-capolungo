package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class SetYourCardRequest extends MessageFromServer {


    final List<String> chosenGods;

    public SetYourCardRequest(List<String> chosenGods) {
        this.chosenGods = chosenGods;
    }

    public List<String> getChosenGods() {
        return chosenGods;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);

    }
}
