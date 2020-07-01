package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class TryNewCoordinatesRequest extends MessageFromServer {


    final int worker;

    public TryNewCoordinatesRequest(int worker) {
        this.worker = worker;
    }

    public int getWorker() {
        return worker;
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
