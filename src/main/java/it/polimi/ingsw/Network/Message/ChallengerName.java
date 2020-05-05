package it.polimi.ingsw.Network.Message;

import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.util.List;

public class ChallengerName extends Message {

    String challengerName;
    List godNames;

    public  ChallengerName(String senderUsername, String token, List godNames, String challengerName) {
        super(senderUsername, token);
        this.godNames = godNames;
        this.challengerName = challengerName;
    }

    @Override
    public String toString(){
        return "Challenger was random, "+ challengerName + "can now choose the Cards \n. Here are the cards " + godNames;
    }
}
