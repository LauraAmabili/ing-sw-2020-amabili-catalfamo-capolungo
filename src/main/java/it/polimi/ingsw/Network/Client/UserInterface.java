package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;


import java.io.IOException;
import java.util.List;


public interface UserInterface {


    void PlayerNumberRequest() ;
    void NicknameRequest();
    void ConnectionResponse();
    void CardNotFoundRequest();
    void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) ;
    void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) ;
    void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate);
    void BoardUpdate(BoardUpdate boardUpdate);
    void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate);
    void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) ;
    void MoveRequest(MoveRequest moveRequest) ;
    void BuildTimeUpdate();
    void BuildRequest(BuildRequest buildRequest) ;
    void TryNewCoordinatesRequest();
    void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate);
    void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    void NicknameNotValidUpdate();
    void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate);
    void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate);
    void ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) ;
    void CardAddedUpdate(CardAddedUpdate cardAddedUpdate);
    void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate);
    void SetYourCardRequest(SetYourCardRequest setYourCardRequest);
    void CardSetUpdate(CardSetUpdate cardSetUpdate);
    void MaxPlayerReachedUpdate();
    void AskEffect() ;
    void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) ;
    void AskEffectBuild(AskEffectBuild askEffectBuild) ;
    void NumberOfPlayerWrong();
    void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest);
    void MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest);
    void WinMessage(String nickname);
    void WorkerInputNotValid();
    void DroppedConnection(DroppedConnection droppedConnection);
    void SetFirstPlayer(List<PlayerInterface> onlinePlayers) ;
    void ServerRestart();
    void lengthNameError();
}




