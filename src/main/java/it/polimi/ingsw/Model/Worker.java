package it.polimi.ingsw.Model;

public class Worker {

    private int idWorker;
    private Player playerWorker;
    private BoardCell oldCell;
    private BoardCell curCell;
    private Board board;

    public Worker(int idWorker, Board board) {
        this.idWorker = idWorker;
        this.board = board;
    }

    public int getIdWorker() {
        return idWorker;
    }

    public Player getPlayerWorker() {
        return playerWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    public void setPlayerWorker(Player playerWorker) {
        this.playerWorker = playerWorker;
    }

    public BoardCell getOldCell() {
        return oldCell;
    }

    public void setOldCell(BoardCell oldCell) {
        this.oldCell = oldCell;
    }

    public BoardCell getCurCell() {
        return curCell;
    }

    public void setCurCell(BoardCell curCell) {
        this.curCell = curCell;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
