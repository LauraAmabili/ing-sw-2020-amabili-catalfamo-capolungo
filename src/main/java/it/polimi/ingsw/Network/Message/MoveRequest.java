package it.polimi.ingsw.Network.Message;

public class MoveRequest extends Action {




    public MoveRequest(String senderUsername, String token, int oldRow, int oldCol, int newRow, int newCol, int worker, String player) {
        super(senderUsername, token, oldRow, oldCol, newRow, newCol, worker, player);
    }
}
