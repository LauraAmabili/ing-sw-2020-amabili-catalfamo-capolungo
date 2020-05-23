package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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

    public BoardController(Client client) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUI gui = (GUI) client.getUserInterface();
        NameText3.setText(client.getNickname());
        String name = gui.getOpponentPlayers().get(0);
        NameText1.setText(name);
        if(gui.getOpponentPlayers().get(1) != null) {
            name = gui.getOpponentPlayers().get(1);
            NameText2.setText(name);
        }

    }



}
