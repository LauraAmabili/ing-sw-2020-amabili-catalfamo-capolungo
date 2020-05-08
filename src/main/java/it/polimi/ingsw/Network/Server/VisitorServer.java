package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.NicknameResponse;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;

public interface VisitorServer {

    public void visit(CardToBeAdded cardToBeAdded);
    public void visit(ChallengerName challengerName);
    public void visit(GameReady gameReady);
    public void visit(NicknameRequest nicknameRequest);
    void visit(NicknameResponse nicknameResponse);
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
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse);




}
