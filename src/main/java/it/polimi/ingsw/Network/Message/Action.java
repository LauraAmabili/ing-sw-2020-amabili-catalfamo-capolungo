package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public class Action extends MessageToClient {



    int oldRow;
    int oldCol;
    int newRow;
    int newCol;
    int worker;
    String player;

    public Action(int oldRow, int oldCol, int newRow, int newCol, int worker, String player) {
        this.oldRow = oldRow;
        this.oldCol = oldCol;
        this.newRow = newRow;
        this.newCol = newCol;
        this.worker = worker;
        this.player = player;
    }


    public int getOldRow() {
        return oldRow;
    }

    public int getOldCol() {
        return oldCol;
    }

    public int getNewRow() {
        return newRow;
    }

    public int getNewCol() {
        return newCol;
    }

    public int getWorker() {
        return worker;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {

    }
}
