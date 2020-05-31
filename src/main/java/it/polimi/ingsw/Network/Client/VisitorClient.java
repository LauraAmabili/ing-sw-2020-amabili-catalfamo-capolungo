package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;

public interface VisitorClient {


    void visit(PlayerNumberRequest playerNumberRequest) throws IOException;

    void visit(ConnectionResponse connectionResponse);

    void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate);
    void visit(NicknameRequest nicknameRequest) throws IOException;
    void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    void visit(NicknameNotValidUpdate nicknameNotValidUpdate);
    void visit(AvailableGodsUpdate availableGodsUpdate);
    void visit(ChallengerCardsRequest challengerCardsRequest) throws IOException;
    void visit(CardAddedUpdate cardAddedUpdate);
    void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    void visit(SetCardTimeUpdate setCardTimeUpdate) throws IOException;
    void visit(SetYourCardRequest setYourCardRequest) throws IOException;
    void visit(CardSetUpdate cardSetUpdate);
    void visit(ChooseCardsUpdate timeToChooseCard);
    void visit(CardNotFoundRequest cardNotFoundRequest);
    void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    void visit(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException;
    void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException;
    void visit(PlayerLockedUpdate playerLockedUpdate);
    void visit(BoardUpdate boardUpdate);
    void visit(PlayerTurnUpdate playerTurnUpdate);
    void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException;
    void visit(MoveRequest moveRequest) throws IOException;
    void visit(BuildTimeUpdate buildTimeUpdate);
    void visit(BuildRequest buildingRowAndCol) throws IOException;
    void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest);
    void visit(WrongWorkerUpdate wrongWorkerUpdate);
    void visit(AskEffect askEffect) throws IOException;
    void visit(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException;
    void visit(AskEffectBuild askEffectBuild) throws IOException;
    void visit(BuildTwoInputRequest buildTwoInputRequest) throws IOException;
    void visit(NumberOfPlayerWrong numberOfPlayerWrong);
    void visit(MoveTwoInputRequest moveTwoInputRequest) throws IOException;
    void visit(WinMessage winMessage);
    void visit(WorkerInputNotValid workerInputNotValid);
    void visit(DroppedConnection droppedConnection);
    void visit(SetFirstPlayer setFirstPlayer);
}
