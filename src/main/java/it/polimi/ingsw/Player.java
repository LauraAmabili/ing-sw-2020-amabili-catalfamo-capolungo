package it.polimi.ingsw;

public class Player {

    private String nickname;
    private String status;
    private God activeCard;



    public void setPlayerNick(String nickname){
        this.nickname = nickname;
    }

    public String getPlayerNick(){
        return nickname;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setActiveCard(God activeCard) {
        this.activeCard = activeCard;
    }

    public God getActiveCard() {
        return activeCard;
    }

}
