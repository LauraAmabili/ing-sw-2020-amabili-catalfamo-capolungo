package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.CardNotFoundRequest;
import it.polimi.ingsw.Network.Message.MessageFromServer.CardAddedUpdate;
import it.polimi.ingsw.Network.Message.MessageFromServer.ChooseCardsUpdate;

import java.io.IOException;

public interface VisitorClient {


    public void visit(PlayerNumberRequest playerNumberRequest) throws IOException;

    public void visit(ConnectionResponse connectionResponse);

    void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate);
    public void visit(NicknameRequest nicknameRequest) throws IOException;
    public void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    public void visit(NicknameNotValidUpdate nicknameNotValidUpdate) throws IOException;
    public void visit(AvailableGodsUpdate availableGodsUpdate);
    public void visit(ChallengerCardsRequest challengerCardsRequest) throws IOException;
    public void visit(CardAddedUpdate cardAddedUpdate);
    public void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    public void visit(SetCardTimeUpdate setCardTimeUpdate) throws IOException;
    public void visit(SetYourCardRequest setYourCardRequest) throws IOException;
    public void visit(CardSetUpdate cardSetUpdate);
    public void visit(ChooseCardsUpdate timeToChooseCard);
    public void visit(CardNotFoundRequest cardNotFoundRequest);
    public void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    public void visit(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException;
    public void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException;
    public void visit(PlayerLockedUpdate playerLockedUpdate);
    public void visit(BoardUpdate boardUpdate);
    public void visit(PlayerTurnUpdate playerTurnUpdate);
    public void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException;
    public void visit(MoveRequest moveRequest) throws IOException;
    public void visit(BuildTimeUpdate buildTimeUpdate);
    public void visit(BuildRequest buildingRowAndCol) throws IOException;
    public void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest) throws IOException;
    public void visit(WrongWorkerUpdate wrongWorkerUpdate);
    public void visit(AskEffect askEffect) throws IOException;
    public void visit(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException;
    public void visit(AskEffectBuild askEffectBuild) throws IOException;
    public void visit(BuildTwoInputRequest buildTwoInputRequest) throws IOException;
    void visit(NumberOfPlayerWrong numberOfPlayerWrong);
    public void visit(MoveTwoInputRequest moveTwoInputRequest) throws IOException;
    public void visit(WinMessage winMessage);
    void visit(WorkerInputNotValid workerInputNotValid);
}
