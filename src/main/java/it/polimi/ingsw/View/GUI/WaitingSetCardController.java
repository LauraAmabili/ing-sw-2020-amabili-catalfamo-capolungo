package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingSetCardController extends NotifyMessages implements Initializable {

    Client client;
    UpdatesForMessages up;
    String name;

    @FXML
    TextField PlayerName = new TextField();

    public WaitingSetCardController(Client client, String name) {
        this.client = client;
        this.name = name;
        up = new UpdatesForMessages(client);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUp();
    }

    public void setUp() {
        PlayerName.setText(name);
    }
}
