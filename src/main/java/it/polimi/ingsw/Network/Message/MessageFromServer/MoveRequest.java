package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class MoveRequest extends MessageFromServer {


    final int worker;
    List<BoardCell> availableCess;


    public MoveRequest(int worker, List<BoardCell> availableCess) {
        this.worker = worker;
        this.availableCess = availableCess;
    }

    public int getWorker() {
        return worker;
    }

    public List<BoardCell> getAvailableCess() {
        return availableCess;
    }


    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
