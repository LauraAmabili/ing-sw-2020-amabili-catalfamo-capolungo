package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;

public interface VisitorClient {


    void visit(PlayerNumberRequest playerNumberRequest) throws IOException;

    void visit(ConnectionResponse connectionResponse);
    void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate);
    void visit(NicknameRequest nicknameRequest);
    void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    void visit(NicknameNotValidUpdate nicknameNotValidUpdate);
    void visit(AvailableGodsUpdate availableGodsUpdate);
    void visit(ChallengerCardsRequest challengerCardsRequest);
    void visit(CardAddedUpdate cardAddedUpdate);
    void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    void visit(SetCardTimeUpdate setCardTimeUpdate);
    void visit(SetYourCardRequest setYourCardRequest);
    void visit(CardSetUpdate cardSetUpdate);
    void visit(ChooseCardsUpdate timeToChooseCard);
    void visit(CardNotFoundRequest cardNotFoundRequest);
    void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    void visit(StartingSetWorkerRequest startingSetWorkerRequest);
    void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) ;
    void visit(PlayerLockedUpdate playerLockedUpdate);
    void visit(BoardUpdate boardUpdate);
    void visit(PlayerTurnUpdate playerTurnUpdate);
    void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) ;
    void visit(MoveRequest moveRequest) ;
    void visit(BuildTimeUpdate buildTimeUpdate);
    void visit(BuildRequest buildingRowAndCol) ;
    void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest);
    void visit(WrongWorkerUpdate wrongWorkerUpdate);
    void visit(AskEffect askEffect) ;
    void visit(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) ;
    void visit(AskEffectBuild askEffectBuild);
    void visit(BuildTwoInputRequest buildTwoInputRequest) ;
    void visit(NumberOfPlayerWrong numberOfPlayerWrong);
    void visit(MoveTwoInputRequest moveTwoInputRequest) ;
    void visit(WinMessage winMessage);
    void visit(WorkerInputNotValid workerInputNotValid);
    void visit(DroppedConnection droppedConnection);
    void visit(SetFirstPlayer setFirstPlayer) ;
    void visit(ServerRestart serverRestart);
    void visit(tooLongName tooLongName) ;
    void visit(VisitorClient gameMessageVisitorClient);
}
