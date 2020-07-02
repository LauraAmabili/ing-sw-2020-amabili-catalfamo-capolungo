package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class ChooseYourWorkerResponse extends MessageFromClient {



    final String worker;

    public ChooseYourWorkerResponse(String worker) {
        this.worker = worker;
    }

    public String getWorker() {
        return worker;
    }


    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient)  {
        gameMessageVisitorClient.visit(this);
    }
}
