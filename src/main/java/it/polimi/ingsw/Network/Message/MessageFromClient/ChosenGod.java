package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChosenGod extends MessageToServer {

    String chosenGod;

    public ChosenGod(String chosenGod) {
        this.chosenGod = chosenGod;
    }

    public String getChosenGod() {
        return chosenGod;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
