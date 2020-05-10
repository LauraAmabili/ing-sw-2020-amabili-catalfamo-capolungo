package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class PlayerOut extends MessageToClient {


    String nickname;

    public PlayerOut(String nickname, String target) {
        super(target);
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
