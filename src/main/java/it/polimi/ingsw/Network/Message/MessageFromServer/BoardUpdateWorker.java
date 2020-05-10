package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BoardUpdateWorker extends MessageToClient{


    int row;
    int col;
    int worker;

    public BoardUpdateWorker(String target, int row, int col, int worker) {
        super(target);
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
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
