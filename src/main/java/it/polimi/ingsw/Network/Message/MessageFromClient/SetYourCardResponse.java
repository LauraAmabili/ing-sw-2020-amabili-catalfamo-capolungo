package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class SetYourCardResponse extends MessageFromClient {

    final String chosenGod;

    public SetYourCardResponse(String chosenGod) {
        this.chosenGod = chosenGod;
    }

    public String getChosenGod() {
        return chosenGod;
    }


    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
