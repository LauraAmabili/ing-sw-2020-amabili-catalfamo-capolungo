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


    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
