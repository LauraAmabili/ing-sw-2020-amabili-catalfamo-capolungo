package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;

import java.sql.Time;

public interface Visitor {


    public void visit(NumberOfPlayers numberOfPlayers);
    public void visit(CardToBeAdded cardToBeAdded);
    public void visit(ChallengerName challengerName);
    public void visit(GameReady gameReady);
    public void visit(NicknameRequest nicknameRequest);
    public void visit(NicknameResponseOk nicknameResponseOk);
    public void visit(NicknameResponseNotOk nicknameResponseNotOk);
    public void visit(Challenger challenger);
    public void visit(TimeToChooseCards timeToChooseCards);
    public void visit(GodAdded godAdded);
    public void visit(GodNotAdded godNotAdded);
    public void visit(PlayerSetCard playerSetCard);
    public void visit(SetCard setCard);




}
