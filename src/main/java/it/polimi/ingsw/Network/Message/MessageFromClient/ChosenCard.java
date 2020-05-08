package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChosenCard extends MessageToServer {


    String chosenCard;

    public ChosenCard(String chosenCard) {
        this.chosenCard = chosenCard;
    }

    public String getChosenCard() {
        return chosenCard;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {

    }
}
