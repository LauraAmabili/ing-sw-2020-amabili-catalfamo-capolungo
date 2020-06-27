package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class WinSceneController extends BoardController implements Initializable {

    String Player;

    @FXML
    Text WinPlayer = new Text();
    @FXML
    ImageView WinBanner = new ImageView();

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
        if(Player.equals(client.getNickname())) {
            WinPlayer.setText(Player + " wins");
            WinBanner.setImage(new Image("/Images/gameGodFrameName1.png"));
        } else {
            WinPlayer.setText(Player + " wins\nYou lose");
            WinBanner.setImage(new Image("/Images/gameGodFrameName2.png"));
        }
    }
}
