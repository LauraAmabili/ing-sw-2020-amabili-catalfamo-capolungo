package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.ConnectionResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;

import java.io.IOException;

public interface VisitorClient {


    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException;
    public void visit(CardToBeAdded cardToBeAdded);
    public void visit(ChallengerName challengerName);
    void visit(ConnectionResponse connectionResponse);
    public void visit(GameReady gameReady);
    public void visit(NicknameRequest nicknameRequest) throws IOException;
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
