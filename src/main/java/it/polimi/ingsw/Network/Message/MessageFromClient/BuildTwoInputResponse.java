package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class BuildTwoInputResponse extends MessageFromClient {

    final String row1;
    final String row2;
    final String col1;
    final String col2;
    final int worker;

    public String  getRow1() {
        return row1;
    }

    public String  getRow2() {
        return row2;
    }

    public String  getCol1() {
        return col1;
    }

    public String getCol2() {
        return col2;
    }

    public int getWorker() {
        return worker;
    }

    public BuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) {
        this.row1 = row1;
        this.row2 = row2;
        this.col1 = col1;
        this.col2 = col2;
        this.worker = worker;
    }

    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);
    }
}
