package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class SignalController extends NotifyMessages implements Initializable {

    Client client;
    GUI gui;
    UpdatesForMessages up;

    @FXML
    Text Text1 = new Text();


    public SignalController(Client client) {
        this.client = client;
        gui = (GUI) client.getUserInterface();
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void SetUp() {
        Text1.setText("Server is restarting.\nYou've been disconnected");
    }
}
