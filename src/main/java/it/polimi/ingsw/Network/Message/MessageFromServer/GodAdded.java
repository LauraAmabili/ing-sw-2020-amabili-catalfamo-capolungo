package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class GodAdded extends MessageToClient {


    List<String> addedGods;

    public GodAdded(List<String> addedGods, String target) {
        super(target);
        this.addedGods = addedGods;
    }

    public List<String> getAddedGods() {
        return addedGods;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);

    }
}
