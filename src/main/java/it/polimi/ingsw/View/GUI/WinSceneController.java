package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WinSceneController extends BoardController implements Initializable {

    String Player;

    @FXML
    TextField WinPlayer = new TextField();

    public WinSceneController(Client client, String state, String Player) {
        super(client, state);
        this.Player = Player;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadNameAndCards();
        LoadBoard();
        setUpButtons();
    }

    public void setUpButtons() {
        WinPlayer.setText(Player + " wins");
    }
}
