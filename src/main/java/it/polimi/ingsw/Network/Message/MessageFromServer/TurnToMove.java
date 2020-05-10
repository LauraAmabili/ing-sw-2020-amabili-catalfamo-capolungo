package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class TurnToMove extends MessageToClient{


    String nickname;

    public TurnToMove(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
