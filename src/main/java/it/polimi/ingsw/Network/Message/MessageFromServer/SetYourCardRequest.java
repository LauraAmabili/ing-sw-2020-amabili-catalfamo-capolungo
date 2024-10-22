package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class SetYourCardRequest extends MessageFromServer {


    List<String> availableGods;
    List<God> chosenGods;

    public List<God> getChosenGods() {
        return chosenGods;
    }

    public SetYourCardRequest(List<String> availableGods, List<God> chosenGods) {
        this.chosenGods = chosenGods;
        this.availableGods = availableGods;
    }

    public List<String> getAvailableGods() {
        return availableGods;
    }


    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);

    }
}
