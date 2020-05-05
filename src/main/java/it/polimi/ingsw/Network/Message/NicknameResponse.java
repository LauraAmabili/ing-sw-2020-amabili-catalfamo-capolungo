package it.polimi.ingsw.Network.Message;

public class NicknameResponse extends Message {



    private String playerNickname;



    public NicknameResponse(String senderUsername, String token, String playerNickname) {
        super(senderUsername, token);
        this.playerNickname = playerNickname;

    }

    public String getPlayerNickname() {
        return playerNickname;
    }


    @Override
    public String toString() {
        return "Nickname accepted" + playerNickname;
    }





}
