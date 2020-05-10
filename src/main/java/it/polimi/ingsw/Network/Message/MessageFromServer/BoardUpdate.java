package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BoardUpdate extends MessageToClient {

    //Board board;
    String boardUpdate;
    Board board;

    public BoardUpdate(String target, String boardUpdate, Board board) {
        super(target);
        this.board = board;
        this.boardUpdate = boardUpdate;
    }

    public String getBoardUpdate() {
        return boardUpdate;
    }

    public void setBoardUpdate(String boardUpdate) {
        this.boardUpdate = boardUpdate;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
