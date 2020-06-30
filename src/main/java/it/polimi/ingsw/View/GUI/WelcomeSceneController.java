package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WelcomeSceneController extends NotifyMessages implements Initializable {

    Client client;

    @FXML
    TextField IP = new TextField();

    @FXML
    TextField Port = new TextField();

    @FXML
    Button StartGameButton = new Button();

    public Client getClient() {
        return client;
    }

    public WelcomeSceneController(Client client) {
        this.client = client;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpButtons();
    }

    /**
     * Set up the button to connect to the server
     */
    public void setUpButtons() {
        StartGameButton.setOnAction(event -> {
            try {
                String host = IP.getText();
                String port = Port.getText();
                client.startClient(host, port);
                GUI gui = (GUI) client.getUserInterface();
                gui.setClient(client);
                Parent root = FXMLLoader.load(GUI_App.class.getResource("/Scenes/WaitingScene.fxml"));
                Stage primaryStage = gui.getPrimaryStage();
                primaryStage.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
