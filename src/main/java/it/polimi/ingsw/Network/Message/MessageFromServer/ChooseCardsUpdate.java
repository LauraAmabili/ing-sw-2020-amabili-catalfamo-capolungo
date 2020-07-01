package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class ChooseCardsUpdate extends MessageFromServer {

    final String challenger;
    final String color;


    public ChooseCardsUpdate(String challenger, String color) {
        this.challenger = challenger;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getChallenger() {
        return challenger;
    }

    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
        public void accept(VisitorClient gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
        }
}
