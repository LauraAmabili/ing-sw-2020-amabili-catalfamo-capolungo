package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class BuildRequest extends MessageFromServer {


    final int worker;
    List<BoardCell> av;

    public BuildRequest(int worker, List<BoardCell> av) {
        this.worker = worker;
        this.av = av;
    }

    public List<BoardCell> getAv() {
        return av;
    }

    public void setAv(List<BoardCell> av) {
        this.av = av;
    }

    public int getWorker() {
        return worker;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
        gameMessageVisitorClient.visit(this);
    }
}
