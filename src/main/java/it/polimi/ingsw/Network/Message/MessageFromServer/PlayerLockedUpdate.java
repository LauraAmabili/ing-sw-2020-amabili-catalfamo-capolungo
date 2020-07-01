package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class PlayerLockedUpdate extends MessageFromServer {


    final String nickname;

    public PlayerLockedUpdate(String nickname) {

        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
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
