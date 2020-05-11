package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class StartingSetWorkerResponse extends MessageFromClient {


    int row;
    int col;
    int worker;


    public StartingSetWorkerResponse(int row, int col, int worker) {
        this.row = row;
        this.col = col;
        this.worker = worker;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
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
