package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public class CardToBeAdded extends MessageToClient {




    String cardName;

    public CardToBeAdded(String senderUsername, String token, String cardName) {

        this.cardName = cardName;

    }


    public String getCardName() {
        return cardName;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {

       gameMessageVisitorClient.visit(this);

    }


}
