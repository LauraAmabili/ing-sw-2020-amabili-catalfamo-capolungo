package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController extends BoardController implements Initializable {

    public UpdateController(Client client, String state) {
        super(client, state);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LoadNameAndCards();
        LoadBoard();
        StateText(state);

    }

}
