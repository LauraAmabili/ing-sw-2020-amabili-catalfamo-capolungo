package it.polimi.ingsw.Network.Message;


import it.polimi.ingsw.Network.Client.VisitorClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

import java.util.List;

public class ChallengerName extends MessageToClient {

    String challengerName;
    List godNames;

    public  ChallengerName(List godNames, String challengerName) {
        this.godNames = godNames;
        this.challengerName = challengerName;
    }

    public String getChallengerName() {
        return challengerName;
    }

    public void setChallengerName(String challengerName) {
        this.challengerName = challengerName;
    }

    public List getGodNames() {
        return godNames;
    }

    public void setGodNames(List godNames) {
        this.godNames = godNames;
    }


    @Override
    public void accept(VisitorClient gameMessageVisitorClient) {
        gameMessageVisitorClient.visit(this);

    }
}
