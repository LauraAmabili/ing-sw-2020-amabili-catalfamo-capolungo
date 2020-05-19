package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class chosenCardsController extends SceneController implements Initializable {
    Client client;

    UpdatesForMessages up;

    public chosenCardsController(Client client) {
        super(client);
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @FXML
    Button button1 = new Button();

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpButtons();
    }

    public void setUpButtons() {
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    notifyPlayerNumberResponse("2");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        );

    }
}
