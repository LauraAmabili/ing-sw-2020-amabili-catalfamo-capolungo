package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TimeToChallengerController extends NotifyMessages implements Initializable {


    Client client;
    UpdatesForMessages up;
    String name;

    @FXML
    Text ChallengerName = new Text();

    public TimeToChallengerController(Client client, String name) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
        this.name = name;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        set();
    }

    /**
     * Load the name of the challenger.
     */
    public void set(){
        ChallengerName.setText(name);
    }
}
