package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Controller.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;


import java.io.IOException;
import java.util.List;


public interface UserInterface {


    public void PlayerNumberRequest() throws IOException;
    public void NicknameRequest() throws IOException;
    public void ConnectionResponse();
    public void CardNotFoundRequest();
    public void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    public void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException;
    public void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException;
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate);
    public void BoardUpdate(BoardUpdate boardUpdate);
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate);
    public void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException;
    public void MoveRequest(MoveRequest moveRequest) throws IOException;
    public void BuildTimeUpdate();
    public void BuildRequest(BuildRequest buildRequest) throws IOException;
    public void TryNewCoordinatesRequest();
    public void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate);
    public void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    public void NicknameNotValidUpdate();
    public void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate);
    public void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate);
    public void ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) throws IOException;
    public void CardAddedUpdate(CardAddedUpdate cardAddedUpdate);
    public void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate);
    public void SetYourCardRequest(SetYourCardRequest setYourCardRequest) throws IOException;
    public void CardSetUpdate(CardSetUpdate cardSetUpdate);
    public void MaxPlayerReachedUpdate();
    public void AskEffect() throws IOException;
    public void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException;
    public void AskEffectBuild(AskEffectBuild askEffectBuild) throws IOException;
    public void NumberOfPlayerWrong();
    public void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) throws IOException;
    public void MoveTwoInputRequest (MoveTwoInputRequest moveTwoInputRequest) throws IOException;
    public void WinMessage(String nickaname);
    public void WorkerInputNotValid();

}




