package it.polimi.ingsw.Network.Message;

public class Action extends Message {



    int OldRow;
    int OldCol;
    int NewRow;
    int NewCol;
    int worker;
    String player;

    public Action(String senderUsername, String token, int oldRow, int oldCol, int newRow, int newCol, int worker, String player) {
        super(senderUsername, token);
        OldRow = oldRow;
        OldCol = oldCol;
        NewRow = newRow;
        NewCol = newCol;
        this.worker = worker;
        this.player = player;
    }


    public int getOldRow() {
        return OldRow;
    }

    public int getOldCol() {
        return OldCol;
    }

    public int getNewRow() {
        return NewRow;
    }

    public int getNewCol() {
        return NewCol;
    }

    public int getWorker() {
        return worker;
    }
}
