package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.Message;

public class NumberOfPlayersRequest extends Message {






    public NumberOfPlayersRequest(String senderUsername, String token, int numberOfPlayers) {
        super(senderUsername, token);

    }


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);
    }

}
