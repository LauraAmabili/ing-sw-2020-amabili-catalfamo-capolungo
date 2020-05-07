package it.polimi.ingsw.Network.Message;

import it.polimi.ingsw.Network.Client.Visitor;

public class CardToBeAdded extends Message {




    String cardName;

    public CardToBeAdded(String senderUsername, String token, String cardName) {

        super(senderUsername, token);
        this.cardName = cardName;

    }


    public String getCardName() {
        return cardName;
    }

    @Override
    public void accept(Visitor gameMessageVisitor) {

       gameMessageVisitor.visit(this);

    }


}
