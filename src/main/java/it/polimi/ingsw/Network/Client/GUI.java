package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.View.GUI.*;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUI implements UserInterface {


    //private Client client;
   // UpdatesForMessages up;


    private Stage primaryStage;
    private Client client;
    private List<God> chosenCards;
    private List<String> opponentChosenCards;
    private final List<String> opponentPlayerName = new ArrayList<>();
    private final List<PlayerInterface> opponentPlayers = new ArrayList<>();
    private List<String> cards = new ArrayList<>();
    private String MyCard;
    private String currentPlayer;
    private PlayerInterface Me;
    private Board board;
    private String state;

    public List<String> getCards() {
        return cards;
    }

    public String getState() {
        return state;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayerInterface getMe() {
        return Me;
    }

    public void setMe(PlayerInterface me) {
        Me = me;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getMyCard() {
        return MyCard;
    }

    public void setMyCard(String myCard) {
        MyCard = myCard;
    }

    public List<String> getOpponentPlayerName() {
        return opponentPlayerName;
    }

    public List<String> getOpponentChosenCards() {
        return opponentChosenCards;
    }

    public List<God> getChosenCards() {
        return chosenCards;
    }

    public List<PlayerInterface> getOpponentPlayers() {
        return opponentPlayers;
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

    @Override
    public void PlayerNumberRequest() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/GameModeScene.fxml"));
            ModeController controller = new ModeController(client);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
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
                primaryStage.getScene().setRoot(root);
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

        state = "Placing workers";
        currentPlayer = startingSetWorkerTimeUpdate.getCurrentPlayer();
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new UpdateController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) {
        int worker = startingSetWorkerRequest.getWorker();
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            PlaceWorkerController controller = new PlaceWorkerController(client, worker, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) {

        int worker = wrongCoordinatesUpdate.getWorker();
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            PlaceWorkerController controller = new PlaceWorkerController(client, worker, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate) {

    }

    @Override
    public void BoardUpdate(BoardUpdate boardUpdate) {

        setBoard(boardUpdate.getBoard());
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new UpdateController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate) {

        currentPlayer = playerTurnUpdate.getNickname();
        state = "Moving";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new UpdateController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) {

        state = "Choosing Worker";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new ChooseWorkerController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void MoveRequest(MoveRequest moveRequest) {

        int worker = moveRequest.getWorker();
        state = "Moving";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new MovingController(client, state, worker);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void BuildTimeUpdate() {

        state = "Building";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new UpdateController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void BuildRequest(BuildRequest buildRequest) {

        int worker = buildRequest.getWorker();
        state = "Building";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new BuildingController(client, state, worker);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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

        String name = chooseCardsUpdate.getChallenger();
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/ChallengerNameScene.fxml"));
            TimeToChallengerController controller = new TimeToChallengerController(client, name);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

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
                primaryStage.getScene().setRoot(root);
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
        //non necessario
    }

    @Override
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate) {
        Platform.runLater(() -> {
            if (client.getNickname().equals(setCardTimeUpdate.getCurrentPlayer())) {
                opponentPlayerName.add(setCardTimeUpdate.getCurrentPlayer());
                FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/CardsToChooseScene.fxml"));
                CardsToChooseController controller = new CardsToChooseController(client);
                loader.setController(controller);
                Parent root = null;
                try {
                    root = loader.load();
                    primaryStage.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/playerSettingCardScene.fxml"));
                    WaitingSetCardController controller = new WaitingSetCardController(client, setCardTimeUpdate.getCurrentPlayer());
                    loader.setController(controller);
                    Parent root = loader.load();
                    primaryStage.getScene().setRoot(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void SetYourCardRequest(SetYourCardRequest setYourCardRequest){

        chosenCards = setYourCardRequest.getChosenGods();
        opponentChosenCards = setYourCardRequest.getAvailableGods();

    }

    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {

        if(!client.getNickname().equals(cardSetUpdate.getCurrentPlayer().getNickname())) {
            opponentPlayers.add(cardSetUpdate.getCurrentPlayer());
        } else {
            setMe(cardSetUpdate.getCurrentPlayer());
        }

    }

    @Override
    public void MaxPlayerReachedUpdate() {
        //non necesssario
    }

    @Override
    public void AskEffect() {

        String name = client.getNickname();
        state = "Choosing effect";
        Me.setEnableSpecialMove(false);
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new AskEffectController(client, state, name);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) {

        state = "Choosing Worker";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new ChooseWorkerController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void AskEffectBuild(AskEffectBuild askEffectBuild) {

        String name = client.getNickname();
        Me.setEnableSpecialMove(false);
        state = "Choosing effect";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new AskEffectBuildController(client, state, name, askEffectBuild.getWorker());
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void NumberOfPlayerWrong() {
        //non necessario
    }

    @Override
    public void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) {

        int worker = buildTwoInputRequest.getWorker();
        state = "Building";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new TwoInputBuildingController(client, state, worker);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) {

        int worker = moveTwoInputRequest.getWorker();
        state = "Moving";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new TwoInputMovingController(client, state, worker);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void WinMessage(String nickaname) {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/WinScene.fxml"));
            BoardController controller = new WinSceneController(client, state, nickaname);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        client.setActive(false);
    }

    @Override
    public void WorkerInputNotValid() {
        state = "Choosing Worker";
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/BoardViewScene.fxml"));
            BoardController controller = new ChooseWorkerController(client, state);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void DroppedConnection(DroppedConnection droppedConnection) {
        //TODO
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/chosenCards.fxml"));
            ChosenCardsController controller = new ChosenCardsController(client);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void SetFirstPlayer(List<PlayerInterface> onlinePlayers) {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(GUI_App.class.getResource("/Scenes/setFirstPlayerScene.fxml"));
            setFirstPlayerController controller = new setFirstPlayerController(client, onlinePlayers);
            loader.setController(controller);
            Parent root = null;
            try {
                root = loader.load();
                primaryStage.getScene().setRoot(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}
