package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class NicknameResponse extends MessageToServer {

    String nickname;

    public NicknameResponse(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
            gameMessageVisitorClient.visit(this);
    }
}
