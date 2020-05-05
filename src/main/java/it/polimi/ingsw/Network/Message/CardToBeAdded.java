package it.polimi.ingsw.Network.Message;

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
    public String toString(){
        return "This card " + cardName + " has been chosen from " + getSenderUsername();
    }



}
