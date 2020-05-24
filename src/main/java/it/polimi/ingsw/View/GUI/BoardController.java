package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardController extends NotifyMessages implements Initializable {

    Client client;
    UpdatesForMessages up;

    @FXML
    GridPane Board = new GridPane();
    @FXML
    ImageView Card1 = new ImageView();
    @FXML
    ImageView Card2 = new ImageView();
    @FXML
    ImageView Card3 = new ImageView();
    @FXML
    Text NameText1 = new Text();
    @FXML
    Text NameText2 = new Text();
    @FXML
    Text NameText3 = new Text();
    @FXML
    Text CurrentTurnPlayer = new Text();

    public BoardController(Client client) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LoadNameAndCards();

    }


    public void LoadNameAndCards() {
        GUI gui = (GUI) client.getUserInterface();
        String url;
        String name = gui.getMe().getNickname();
        NameText3.setText(name);
        if(gui.getMe().getWorkerRef().get(0).getColor().equals("\u001B[34m") ) {
            NameText3.setFill(Color.BLUE);
        } else if(gui.getMe().getWorkerRef().get(0).getColor().equals("\u001B[35m")) {
            NameText3.setFill(Color.DARKMAGENTA);
        } else {
            NameText3.setFill(Color.GOLDENROD);
        }
        name = gui.getOpponentPlayers().get(0).getNickname();
        NameText1.setText(name);
        if(gui.getOpponentPlayers().get(0).getWorkerRef().get(0).getColor().equals("\u001B[34m") ) {
            NameText1.setFill(Color.BLUE);
        } else if(gui.getOpponentPlayers().get(0).getWorkerRef().get(0).getColor().equals("\u001B[35m")) {
            NameText1.setFill(Color.DARKMAGENTA);
        } else {
            NameText1.setFill(Color.GOLDENROD);
        }
        if(gui.getOpponentPlayers().size() > 2) {
            name = gui.getOpponentPlayers().get(1).getNickname();
            NameText2.setText(name);
            if(gui.getOpponentPlayers().get(1).getWorkerRef().get(0).getColor().equals("\u001B[34m") ) {
                NameText2.setFill(Color.BLUE);
            } else if(gui.getOpponentPlayers().get(1).getWorkerRef().get(0).getColor().equals("\u001B[35m")) {
                NameText2.setFill(Color.DARKMAGENTA);
            } else {
                NameText2.setFill(Color.GOLDENROD);
            }
        }
        url = "/godCards/" + gui.getMyCard() + ".png";
        Card3.setImage(new Image(url));
        url = "/godCards/" + gui.getOpponentPlayers().get(0).getActiveCard().getGodName() + ".png";
        Card1.setImage(new Image(url));
        if(gui.getOpponentPlayers().size() > 2) {
            url = "/godCards/" + gui.getOpponentPlayers().get(1).getActiveCard().getGodName() + ".png";
            Card2.setImage(new Image(url));
        }
        CurrentTurnPlayer.setText("Current Turn: " + gui.getCurrentPlayer());
    }


}
