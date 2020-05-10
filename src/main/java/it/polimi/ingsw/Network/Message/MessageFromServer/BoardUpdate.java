package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class BoardUpdate extends MessageToClient {


    public String getBoardUpdate() {
        return boardUpdate;
    }

    //Board board;
    String boardUpdate;

    public BoardUpdate(String target, String boardUpdate) {
        super(target);
       // this.board = board;
        this.boardUpdate = boardUpdate;
    }

   // public Board getBoard() {
    //    return board;
   // }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {

            gameMessageVisitorClient.visit(this);
    }
}
