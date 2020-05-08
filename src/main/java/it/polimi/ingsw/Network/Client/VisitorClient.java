package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;

import java.io.IOException;

public interface VisitorClient {


    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException;
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
    public void visit(CardSet cardSet);
    public void visit(CardNotPresent cardNotPresent);
    public void visit(Welcome welcome);




}