package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeSceneController extends SceneController implements Initializable {

    Client client;

    @FXML
    javafx.scene.control.Button StartGameButton = new Button();

    @Override
    public Client getClient() {
        return client;
    }

    public WelcomeSceneController(Client client) {
        super(client);
        this.client = client;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpButtons();
    }

    public void setUpButtons() {
        StartGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    client.startClient();
                    GUI gui = (GUI) client.getUserInterface();
                    gui.setClient(client);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
