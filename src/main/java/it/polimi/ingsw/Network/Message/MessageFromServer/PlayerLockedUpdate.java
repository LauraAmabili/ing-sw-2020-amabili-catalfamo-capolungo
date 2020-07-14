package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class PlayerLockedUpdate extends MessageFromServer {


    final String nickname;
    private String currentPlayer;

    public PlayerLockedUpdate(String nickname, String currentPlayer) {
        this.nickname = nickname;
        this.currentPlayer = currentPlayer;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Calls the correspondent visit method based on the type of Message from the Server
     * @param gameMessageVisitorClient gameMessage to be checked
     */
    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }
}
