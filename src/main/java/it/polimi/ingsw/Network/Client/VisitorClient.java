package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.ChosenCard;
import it.polimi.ingsw.Network.Message.MessageFromClient.FirstInput;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.CardNotPresent;
import it.polimi.ingsw.Network.Message.MessageFromServer.GodAdded;
import it.polimi.ingsw.Network.Message.MessageFromServer.TimeToChooseCards;

import java.io.IOException;

public interface VisitorClient {


    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException;

    public void visit(ConnectionResponse connectionResponse);

    void visit(MaxPlayerReach maxPlayerReach);
    public void visit(NicknameRequest nicknameRequest) throws IOException;
    public void visit(NicknameAccepted nicknameAccepted);
    public void visit(NicknameNotValid nicknameNotValid) throws IOException;
    public void visit(CardsName cardsName);
    public void visit(ChooseTheCard chooseTheCard) throws IOException;
    public void visit(GodAdded godAdded);
    public void visit(GodNotAdded godNotAdded);
    public void visit(TimeToSetCard timeToSetCard);
    public void visit(SetYourCard setYourCard) throws IOException;
    public void visit(SetCardUpdate setCardUpdate);
    public void visit(TimeToChooseCards timeToChooseCard);
    public void visit(CardNotPresent cardNotPresent);
    public void visit(TimeToPlaceWorkers timeToPlaceWorkers);
    public void visit(SetWorkerRequest setWorkerRequest) throws IOException;
    public void visit(WrongPositionForWorker wrongPositionForWorker) throws IOException;
    public void visit(PlayerOut playerOut);
    public void visit(BoardUpdate boardUpdate);
    public void visit(BoardUpdateWorker boardUpdateWorker);
    public void visit(TurnToMove turnToMove);
    public void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException;
    public void visit(ChooseRowAndColRequest chooseRowAndColRequest) throws IOException;





}
