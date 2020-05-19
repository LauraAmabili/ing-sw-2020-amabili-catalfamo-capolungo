package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ModeController extends SceneController implements Initializable {

    @FXML
    Button TwoPlayerButton = new Button();
    @FXML
    Button ThreePlayerButton = new Button();

    Client client;
    UpdatesForMessages up;

    public Client getClient() {
        return client;
    }

    public ModeController(Client client) {
        super(client);
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpButtons();
    }


    public void setUpButtons() {
        TwoPlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    notifyPlayerNumberResponse("2");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ThreePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    notifyPlayerNumberResponse("3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void LoadScene(Parent root, Stage stage) {
        stage.setScene(new Scene(root));
    }

}
