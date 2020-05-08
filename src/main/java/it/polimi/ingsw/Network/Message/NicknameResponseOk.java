package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromClient.MessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;

public class NicknameResponseOk extends MessageToServer {

    public NicknameResponseOk() {
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException {

    }
}
