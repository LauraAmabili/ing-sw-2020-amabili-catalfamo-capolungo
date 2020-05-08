package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.VisitorClient;

public class CardToBeAdded extends MessageToClient {




    String cardName;

    public CardToBeAdded(String senderUsername, String token, String cardName) {

        super(senderUsername, token);
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
