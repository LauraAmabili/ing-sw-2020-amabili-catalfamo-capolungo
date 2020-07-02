package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChosenCardsUpdate extends MessageFromClient {


    final String chosenCard;

    public ChosenCardsUpdate(String chosenCard) {
        this.chosenCard = chosenCard;
    }

    public String getChosenCard() {
        return chosenCard;
    }


    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);
    }
}
