package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.util.List;

public class GUI implements UserInterface {


    @Override
    public String PlayerNumberRequest() {
        return null;
    }

    @Override
    public String NicknameRequest() {
        return null;
    }

    @Override
    public void ConnectionResponse() {

    }

    @Override
    public void CardNotFoundRequest() {

    }

    @Override
    public void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {

    }

    @Override
    public List<String> StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) {
        return null;
    }

    @Override
    public List<String> WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) {
        return null;
    }

    @Override
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate) {

    }

    @Override
    public void BoardUpdate(BoardUpdate boardUpdate) {

    }

    @Override
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate) {

    }

    @Override
    public int ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) {
        return 0;
    }

    @Override
    public List<String> MoveRequest(MoveRequest moveRequest) {
        return null;
    }

    @Override
    public void BuildTimeUpdate() {

    }

    @Override
    public List<String> BuildRequest() {
        return null;
    }

    @Override
    public void TryNewCoordinatesRequest() {

    }

    @Override
    public void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate) {

    }

    @Override
    public void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate) {

    }

    @Override
    public void NicknameNotValidUpdate() {

    }

    @Override
    public void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate) {

    }

    @Override
    public void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate) {

    }

    @Override
    public String ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) {
        return null;
    }

    @Override
    public void CardAddedUpdate(CardAddedUpdate cardAddedUpdate) {

    }

    @Override
    public void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

    }

    @Override
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate) {

    }

    @Override
    public String SetYourCardRequest(SetYourCardRequest setYourCardRequest) {
        return null;
    }

    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {

    }

    @Override
    public void MaxPlayerReachedUpdate() {

    }

    @Override
    public String AskEffect() {
        return null;
    }

    @Override
    public int ChooseYourWorkerEffectRequest() {
        return 0;
    }

    @Override
    public String AskEffectBuild(AskEffectBuild askEffectBuild) {
        return null;
    }

    @Override
    public void NumberOfPlayerWrong() {

    }

    @Override
    public List<String> BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) {
        return null;
    }

    @Override
    public List<String> MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) {
        return null;
    }

    @Override
    public void WinMessage(String nickaname) {

    }


}
