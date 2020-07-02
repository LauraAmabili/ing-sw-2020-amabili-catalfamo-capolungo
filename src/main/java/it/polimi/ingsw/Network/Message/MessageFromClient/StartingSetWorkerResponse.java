package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class StartingSetWorkerResponse extends MessageFromClient {


    final String row;
    final String col;
    final int worker;


    public StartingSetWorkerResponse(String row, String col, int worker) {
        this.row = row;
        this.col = col;
        this.worker = worker;
    }

    public String getRow() {
        return row;
    }

    public String getCol() {
        return col;
    }

    public int getWorker() {
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
