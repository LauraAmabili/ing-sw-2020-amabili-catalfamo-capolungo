package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Controller.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;


import java.util.List;


public interface UserInterface {


    public String PlayerNumberRequest();
    public String NicknameRequest();
    public void ConnectionResponse();
    public void CardNotFoundRequest();
    public void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);
    public List<String> StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest);
    public List<String> WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate);
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate);
    public void BoardUpdate(BoardUpdate boardUpdate);
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate);
    public int ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest);
    public List<String> MoveRequest(MoveRequest moveRequest);
    public void BuildTimeUpdate();
    public List<String> BuildRequest();
    public void TryNewCoordinatesRequest();
    public void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate);
    public void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    public void NicknameNotValidUpdate();
    public void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate);
    public void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate);
    public String ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest);
    public void CardAddedUpdate(CardAddedUpdate cardAddedUpdate);
    public void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate);
    public String SetYourCardRequest(SetYourCardRequest setYourCardRequest);
    public void CardSetUpdate(CardSetUpdate cardSetUpdate);
    public void MaxPlayerReachedUpdate();
    public String AskEffect();
    public int ChooseYourWorkerEffectRequest();
    public String AskEffectBuild(AskEffectBuild askEffectBuild);
    public void NumberOfPlayerWrong();
    public List<String> BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest);
    public List<String> MoveTwoInputRequest (MoveTwoInputRequest moveTwoInputRequest);
    public void WinMessage(String nickaname);

}




