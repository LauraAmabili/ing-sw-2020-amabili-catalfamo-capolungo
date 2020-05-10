package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class ChooseTheCard extends MessageToClient {


    public ChooseTheCard(String target) {
        super(target);
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }


}
