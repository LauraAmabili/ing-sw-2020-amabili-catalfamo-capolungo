package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;
import java.util.List;

public class BuildResponse extends MessageFromClient {


    final String row;
    final String  col;
    final int worker;
    List<BoardCell> av;

    public List<BoardCell> getAv() {
        return av;
    }

    public void setAv(List<BoardCell> av) {
        this.av = av;
    }

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


    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) {
            gameMessageVisitorClient.visit(this);
    }
}
