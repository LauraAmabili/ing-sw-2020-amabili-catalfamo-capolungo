package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BoardUpdate extends MessageFromServer {

    //Board board;
    Board board;

    public BoardUpdate(Board board) {

        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {

            gameMessageVisitorClient.visit(this);
    }
}
