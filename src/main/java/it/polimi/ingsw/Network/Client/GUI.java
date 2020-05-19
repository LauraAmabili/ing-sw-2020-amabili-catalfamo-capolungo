package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.View.GUI.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class GUI implements UserInterface {


    //private Client client;
   // UpdatesForMessages up;


    private Stage primaryStage;
    private SceneController ModeController;
    Client client;

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /*
    public GUI() throws IOException {
        client = new Client(this);
        up = new UpdatesForMessages(client);
        this.addObserver(up);
    }


     */
    @Override
    public void PlayerNumberRequest() throws IOException {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/GameModeScene.fxml"));
            ModeController controller = new ModeController(client);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void NicknameRequest() throws IOException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/insertNicknameScene.fxml"));
                NicknameSceneController controller = new NicknameSceneController(client);
                loader.setController(controller);
                Parent root = null;
                try {
                    root = loader.load();
                    primaryStage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/chosenCards.fxml"));
                ChosenCardsController controller = new ChosenCardsController(client);
                loader.setController(controller);
                Parent root = null;
                try {
                    root = loader.load();
                    primaryStage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
