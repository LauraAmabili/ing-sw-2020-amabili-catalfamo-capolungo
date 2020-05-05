package it.polimi.ingsw.Network.Message;

public class SettingCardPlayerResponse extends Message {




    String nickname;
    String chosenGod;


    public SettingCardPlayerResponse(String senderUsername, String token, String nickname, String chosenGod) {
        super(senderUsername, token);
        this.nickname = nickname;
        this.chosenGod = chosenGod;
    }

    public String getNickname() {
        return nickname;
    }

    public String getChosenGod() {
        return chosenGod;
    }

    @Override
    public String toString(){

        return nickname + "has chosen " + chosenGod + "as God";

    }
}
