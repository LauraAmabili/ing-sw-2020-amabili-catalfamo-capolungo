package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Model.Worker;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class SetWorkerResponse extends MessageToServer{


    int row;
    int col;
    int worker;


    public SetWorkerResponse(int row, int col, int worker) {
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
