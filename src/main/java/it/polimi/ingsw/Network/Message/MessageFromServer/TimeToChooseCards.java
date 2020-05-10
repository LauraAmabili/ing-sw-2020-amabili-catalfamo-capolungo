package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class TimeToChooseCards extends MessageToClient{

    String challenger;


    public TimeToChooseCards(String challenger) {
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
