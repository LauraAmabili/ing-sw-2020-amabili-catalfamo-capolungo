package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class ChooseCardsUpdate extends MessageFromServer {

    String challenger;


    public ChooseCardsUpdate(String challenger) {
        this.challenger = challenger;
    }

    public String getChallenger() {
        return challenger;
    }

    @Override
        public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
        }
}
