package it.polimi.ingsw.Network.Message;

public class PlacingWorkerRequest extends Message {

    int worker;
    int row;
    int col;


    public PlacingWorkerRequest(String senderUsername, String token, int row, int col, int worker) {

        super(senderUsername, token);
        this.row = row;
        this.col = col;
        this.worker = worker;
    }

    public int getWorker() {
        return worker;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


}
