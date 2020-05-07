package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;

public class ConcreteMethods implements Visitor {


    @Override
    public void visit(NumberOfPlayers numberOfPlayers) {

    }

    @Override
    public void visit(CardToBeAdded cardToBeAdded){

        System.out.println("This card " + cardToBeAdded.getCardName() + " has been chosen from " + cardToBeAdded.getSenderUsername());
    }

    @Override
    public void visit(ChallengerName challengerName){

        System.out.println("Challenger was random, "+ challengerName.getChallengerName() + "can now choose the Cards \n. Here are the cards " + challengerName.getGodNames());
    }
    @Override
    public void visit(NicknameResponseOk nicknameResponseOk){

    }
    @Override
    public void visit(NicknameResponseNotOk nicknameResponseNotOk){

    }

    @Override
    public void visit(Challenger challenger) {

    }

    @Override
    public void visit(TimeToChooseCards timeToChooseCards) {

    }

    @Override
    public void visit(GodAdded godAdded) {

    }

    @Override
    public void visit(GodNotAdded godNotAdded) {

    }

    @Override
    public void visit(PlayerSetCard playerSetCard) {

    }

    @Override
    public void visit(SetCard setCard) {

    }
    @Override
    public void visit(GameReady gameReady){}

    @Override
    public void visit(NicknameRequest nicknameRequest) {

    }

}
