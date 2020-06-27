package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class BoardController extends NotifyMessages {

    Client client;
    UpdatesForMessages up;
    GUI gui;
    String state;

    public BoardController(Client client, String state) {
        this.client = client;
        this.state = state;
        gui = (GUI) client.getUserInterface();
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @FXML
    GridPane Board = new GridPane();
    @FXML
    ImageView Card1 = new ImageView();
    @FXML
    ImageView Card2 = new ImageView();
    @FXML
    ImageView Card3 = new ImageView();
    @FXML
    TextField NameText1 = new TextField();
    @FXML
    TextField NameText2 = new TextField();
    @FXML
    TextField NameText3 = new TextField();
    @FXML
    Text CurrentTurnPlayer = new Text();
    @FXML
    Text ActionText = new Text();
    @FXML
    ToggleButton UseEffect = new ToggleButton();
    @FXML
    ToggleButton DontUseEffect = new ToggleButton();
    @FXML
    Text Text1 = new Text();
    @FXML
    Text Text2 = new Text();
    @FXML
    Text Text3 = new Text();
    @FXML
    Text Text4 = new Text();


    public void LoadNameAndCards() {
        String url;
        gui = (GUI) client.getUserInterface();
        String name = gui.getMe().getNickname();
        NameText1.setText(name);
        setColor(gui.getMe(), NameText1);
        name = gui.getOpponentPlayers().get(0).getNickname();
        NameText3.setText(name);
        setColor(gui.getOpponentPlayers().get(0), NameText3);
        if(gui.getOpponentPlayers().size() > 1) {
            name = gui.getOpponentPlayers().get(1).getNickname();
            NameText2.setText(name);
            setColor(gui.getOpponentPlayers().get(1), NameText2);
        } else {
            NameText2.setVisible(false);
            NameText2.setDisable(true);
        }
        url = "/godCards/" + gui.getMyCard() + ".png";
        Card1.setImage(new Image(url));
        url = "/godCards/" + gui.getOpponentPlayers().get(0).getActiveCard().getGodName() + ".png";
        Card3.setImage(new Image(url));
        if(gui.getOpponentPlayers().size() > 1) {
            url = "/godCards/" + gui.getOpponentPlayers().get(1).getActiveCard().getGodName() + ".png";
            Card2.setImage(new Image(url));
        }
        CurrentTurnPlayer.setText("Current Turn: " + gui.getCurrentPlayer());
    }

    public void setColor(PlayerInterface player, TextField NameText) {
        if(player.getWorkerRef().get(0).getColor().equals("\u001B[34m") ) {
            NameText.setStyle("-fx-text-fill: blue");
        } else if(player.getWorkerRef().get(0).getColor().equals("\u001B[35m")) {
            NameText.setStyle("-fx-text-fill: darkmagenta");
        } else {
            NameText.setStyle("-fx-text-fill: goldenrod");
        }
    }

    public void LoadBoard() {
        String color;
        Board board = gui.getBoard();
        if(board != null) {
            for (int i = 0; i < board.getGrid()[0].length; i++) {
                for(int j = 0; j < board.getGrid()[0].length; j++) {
                    Pane cell = (Pane) Board.getChildren().get(j + (i * 5));
                    if(!board.getGrid()[i][j].getDome()) {
                        if (board.getGrid()[i][j].getLevel() == 1) {
                            ImageView imageView = (ImageView) cell.getChildren().get(2);
                            imageView.setImage(new Image("/Images/LevelNumberOne.PNG"));
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelOne.jpg"));
                        } else if (board.getGrid()[i][j].getLevel() == 2) {
                            ImageView imageView = (ImageView) cell.getChildren().get(2);
                            imageView.setImage(new Image("/Images/LevelNumberTwo.PNG"));
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelTwo.jpg"));;
                        } else if (board.getGrid()[i][j].getLevel() == 3) {
                            ImageView imageView = (ImageView) cell.getChildren().get(2);
                            imageView.setImage(new Image("/Images/LevelNumberThree.PNG"));
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelThree.jpg"));
                        }
                    } else {
                        if (board.getGrid()[i][j].getLevel() == 0) {
                            ImageView imageView;
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelZeroDome.jpg"));
                        } else if (board.getGrid()[i][j].getLevel() == 1) {
                            ImageView imageView = (ImageView) cell.getChildren().get(2);
                            imageView.setImage(new Image("/Images/LevelNumberOne.PNG"));
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelOneDome.jpg"));
                        } else if (board.getGrid()[i][j].getLevel() == 2) {
                            ImageView imageView = (ImageView) cell.getChildren().get(2);
                            imageView.setImage(new Image("/Images/LevelNumberTwo.PNG"));
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelTwoDome.jpg"));
                        } else if (board.getGrid()[i][j].getLevel() == 3) {
                            ImageView imageView = (ImageView) cell.getChildren().get(2);
                            imageView.setImage(new Image("/Images/LevelNumberThree.PNG"));
                            imageView = (ImageView) cell.getChildren().get(0);
                            imageView.setImage(new Image("/Images/BuildingLevelThreeDome.jpg"));
                        }
                    }
                    if (board.getGrid()[i][j].getWorker() != null) {
                        color = board.getGrid()[i][j].getWorker().getColor();
                        if (color.equals("\u001B[34m")) {
                            ImageView imageView = (ImageView) cell.getChildren().get(1);
                            imageView.setImage(new Image("/Images/BlueHammer.png"));
                        } else if (color.equals("\u001B[35m")) {
                            ImageView imageView = (ImageView) cell.getChildren().get(1);
                            imageView.setImage(new Image("/Images/PurpleHammer.png"));
                        } else {
                            ImageView imageView = (ImageView) cell.getChildren().get(1);
                            imageView.setImage(new Image("/Images/YellowHammer.png"));
                        }
                    }
                }
            }
        }
    }

    public void StateText(String state) {
        String text = "Current Action: " + state;
        ActionText.setText(text);
    }

}
