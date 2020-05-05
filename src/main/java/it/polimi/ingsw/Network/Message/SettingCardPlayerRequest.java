package it.polimi.ingsw.Network.Message;

import java.util.List;

public class SettingCardPlayerRequest  extends Message {




    String currentPlayer;
    List chosenGods;

    public SettingCardPlayerRequest(String senderUsername, String token, String currentPlayer, List chosenGods) {
        super(senderUsername, token);
        this.currentPlayer = currentPlayer;
        this.chosenGods = chosenGods;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public List getChosenGod() {
        return chosenGods;
    }

    @Override
    public String toString(){

        return currentPlayer + "it's your time to choose the God" + chosenGods;
    }



}
