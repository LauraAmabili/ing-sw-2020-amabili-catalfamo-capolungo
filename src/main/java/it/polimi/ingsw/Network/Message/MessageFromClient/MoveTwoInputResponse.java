package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class MoveTwoInputResponse extends MessageFromClient {

    String row1;
    String col1;
    String row2;
    String col2;
    int worker;

    public String getRow1() {
        return row1;
    }

    public String getCol1() {
        return col1;
    }

    public String getRow2() {
        return row2;
    }

    public String getCol2() {
        return col2;
    }

    public int getWorker() {
        return worker;
    }

    public MoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) {
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
        this.worker = worker;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
