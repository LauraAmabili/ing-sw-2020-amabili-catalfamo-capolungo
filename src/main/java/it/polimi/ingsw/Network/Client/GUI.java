package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.View.GUI.GUI_App;
import it.polimi.ingsw.View.GUI.SceneController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GUI implements UserInterface {

    private Stage primaryStage;
    private SceneController ModeController;

    public void run(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

    }

    @Override
    public void PlayerNumberRequest() throws IOException {

        FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/GameModeScene.fxml"));
        Parent root = loader.load();
        Scene primaryStage = new Scene(root);

    }

    @Override
    public void NicknameRequest() {

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
    public void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) {

    }

    @Override
    public void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) {

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
    public void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) {

    }

    @Override
    public void MoveRequest(MoveRequest moveRequest) {

    }

    @Override
    public void BuildTimeUpdate() {

    }

    @Override
    public void BuildRequest(BuildRequest buildRequest) throws IOException {

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
    public void ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) {

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
    public void SetYourCardRequest(SetYourCardRequest setYourCardRequest){
    }

    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {

    }

    @Override
    public void MaxPlayerReachedUpdate() {

    }

    @Override
    public void AskEffect() {

    }

    @Override
    public void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException {

    }

    @Override
    public void AskEffectBuild(AskEffectBuild askEffectBuild) {

    }

    @Override
    public void NumberOfPlayerWrong() {

    }

    @Override
    public void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) {

    }

    @Override
    public void MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) {

    }

    @Override
    public void WinMessage(String nickaname) {

    }

    @Override
    public void WorkerInputNotValid() {

    }


}
