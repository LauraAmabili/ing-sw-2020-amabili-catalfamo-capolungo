package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenCard;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.GodAdded;
import it.polimi.ingsw.Network.Message.MessageFromServer.TimeToChooseCards;

import java.io.IOException;

public interface VisitorClient {


    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException;
    public void visit(CardToBeAdded cardToBeAdded);
    public void visit(ChallengerName challengerName);
    public void visit(ConnectionResponse connectionResponse);
    public void visit(GameReady gameReady);
    public void visit(NicknameRequest nicknameRequest) throws IOException;
    public void visit(Challenger challenger);
    public void visit(PlayerSetCard playerSetCard);
    public void visit(CardSet cardSet);
    public void visit(CardNotPresent cardNotPresent);
    public void visit(Welcome welcome);
    public void visit(NicknameAccepted nicknameAccepted);
    public void visit(NicknameNotValid nicknameNotValid) throws IOException;
    public void visit(CardsName cardsName);
    public void visit(ChooseTheCard chooseTheCard) throws IOException;
    public void visit(GodAdded godAdded);
    public void visit(GodNotAdded godNotAdded);
    public void visit(TimeToSetCard timeToSetCard);
    public void visit (SetYourCard setYourCard) throws IOException;
    public void visit(SetCardUpdate setCardUpdate);
    public void visit(TimeToChooseCards timeToChooseCard);



}
