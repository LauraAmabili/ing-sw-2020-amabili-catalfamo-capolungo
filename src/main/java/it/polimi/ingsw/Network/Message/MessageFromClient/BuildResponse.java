package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class BuildResponse extends MessageFromClient {


    String row;
    String  col;
    int worker;

    public BuildResponse(String row, String col, int worker) {
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
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
            gameMessageVisitorClient.visit(this);
    }
}
