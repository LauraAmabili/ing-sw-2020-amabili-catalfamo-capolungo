package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;

public class NicknameAcceptedUpdate extends MessageFromServer {



    String color;
    String name;

    public NicknameAcceptedUpdate(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
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
