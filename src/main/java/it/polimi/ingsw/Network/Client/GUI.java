package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.View.GUI.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI implements UserInterface {


    //private Client client;
   // UpdatesForMessages up;


    private Stage primaryStage;
    private Client client;
    private List<String> godNames;
    private final List<String> chosenCards = new ArrayList<>();
    private String opponentChosenCard;
    private String currentPlayer;

    public List<String> getChosenCards() {
        return chosenCards;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public List<String> getGodNames() {
        return godNames;
    }

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
    public void PlayerNumberRequest() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/GameModeScene.fxml"));
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
    public void NicknameRequest() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/insertNicknameScene.fxml"));
            NicknameSceneController controller = new NicknameSceneController(client);
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

        //TODO: create Board Scene
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/chosenCards.fxml"));
            ChosenCardsController controller = new ChosenCardsController(client);
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
    public void BuildRequest(BuildRequest buildRequest) {

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
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/chosenCards.fxml"));
            ChosenCardsController controller = new ChosenCardsController(client);
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
    public void CardAddedUpdate(CardAddedUpdate cardAddedUpdate) {

    }

    @Override
    public void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

    }

    @Override
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate) {
        Platform.runLater(() -> {
            if (client.getNickname().equals(setCardTimeUpdate.getCurrentPlayer())) {
                FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/CardsToChooseScene.fxml"));
                CardsToChooseController controller = new CardsToChooseController(client);
                loader.setController(controller);
                Parent root = null;
                try {
                    root = loader.load();
                    primaryStage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Parent root = FXMLLoader.load(GUI_App.class.getResource("/Scenes/WaitingScene.fxml"));
                    primaryStage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        currentPlayer = setCardTimeUpdate.getCurrentPlayer();
    }

    @Override
    public void SetYourCardRequest(SetYourCardRequest setYourCardRequest){
        if(godNames == null) {
            godNames = setYourCardRequest.getAvailableGods();
            if(opponentChosenCard != null) {
                godNames.add(opponentChosenCard);
            }
        }
    }

    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {
        opponentChosenCard = cardSetUpdate.getGodName();
        chosenCards.add(cardSetUpdate.getGodName());
    }

    @Override
    public void MaxPlayerReachedUpdate() {

    }

    @Override
    public void AskEffect() {

    }

    @Override
    public void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) {

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
