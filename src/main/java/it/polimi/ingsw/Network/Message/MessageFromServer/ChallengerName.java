package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;


public class ChallengerName extends MessageToClient {


    public String getChallengerName() {
        return challengerName;
    }

    String challengerName;
    List<String > godNames;


    public ChallengerName(String challengerName, List<String> godNames) {

        this.challengerName = challengerName;
        this.godNames = godNames;
    }

    public List<String> getGodNames() {
        return godNames;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {

    }
}
