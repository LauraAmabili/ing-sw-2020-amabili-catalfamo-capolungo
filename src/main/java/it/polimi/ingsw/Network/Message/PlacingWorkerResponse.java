package it.polimi.ingsw.Network.Message;

public class PlacingWorkerResponse  extends Message {



    String currentPlayer;
    int row;
    int col;
    int worker;


    public PlacingWorkerResponse(String senderUsername, String token, int row, int col, int worker) {
        super(senderUsername, token);
        this.row = row;
        this.col = col;
        this.worker = worker;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getWorker() {
        return worker;
    }

    @Override
    public String toString (){

        return currentPlayer + "  worker #" + worker + " has been placed in (" + row + "," + col+ ")";

    }


}
