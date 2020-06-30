package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;


import java.io.IOException;
import java.util.List;


public interface UserInterface {


    void PlayerNumberRequest() throws IOException;
    void NicknameRequest() throws IOException;
    void ConnectionResponse();
    void CardNotFoundRequest();
    void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException;
    void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException;
    void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate);
    void BoardUpdate(BoardUpdate boardUpdate);
    void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate);
    void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException;
    void MoveRequest(MoveRequest moveRequest) throws IOException;
    void BuildTimeUpdate();
    void BuildRequest(BuildRequest buildRequest) throws IOException;
    void TryNewCoordinatesRequest();
    void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate);
    void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    void NicknameNotValidUpdate();
    void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate);
    void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate);
    void ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) throws IOException;
    void CardAddedUpdate(CardAddedUpdate cardAddedUpdate);
    void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate);
    void SetYourCardRequest(SetYourCardRequest setYourCardRequest) throws IOException;
    void CardSetUpdate(CardSetUpdate cardSetUpdate);
    void MaxPlayerReachedUpdate();
    void AskEffect() throws IOException;
    void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException;
    void AskEffectBuild(AskEffectBuild askEffectBuild) throws IOException;
    void NumberOfPlayerWrong();
    void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) throws IOException;
    void MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) throws IOException;
    void WinMessage(String nickname);
    void WorkerInputNotValid();
    void DroppedConnection(DroppedConnection droppedConnection);
    void SetFirstPlayer(List<PlayerInterface> onlinePlayers) throws IOException;
    void ServerRestart();
}




