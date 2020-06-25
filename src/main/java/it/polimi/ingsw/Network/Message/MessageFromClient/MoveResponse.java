package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;
import java.util.List;

public class MoveResponse extends MessageFromClient {

    final String row;
    final String col;
    final int worker;
    List<BoardCell> available;

    public MoveResponse(String row, String col, int worker) {
        this.row = row;
        this.col = col;
        this.worker = worker;
    }

    public void setAvailable(List<BoardCell> available) {
        this.available = available;
    }

    public List<BoardCell> getAvailable() {
        return available;
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
