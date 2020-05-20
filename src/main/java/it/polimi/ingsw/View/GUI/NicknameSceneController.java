package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NicknameSceneController extends NotifyMessages implements Initializable {

    Client client;
    UpdatesForMessages up;

    public Client getClient() {
        return client;
    }

    @FXML
    Button confirmNickButton = new Button();

    @FXML
    TextField nicknameTextField = new TextField();


    public NicknameSceneController(Client client) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpButtons();
    }


    public void setUpButtons() {
        confirmNickButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String nick = nicknameTextField.getText();
                try {
                    notifyNicknameResponse(nick);
                    removeObserver(up);
                    Parent root = FXMLLoader.load(GUI_App.class.getResource("/Scenes/WaitingScene.fxml"));
                    GUI gui = (GUI) client.getUserInterface();
                    Stage primaryStage = gui.getPrimaryStage();
                    primaryStage.setScene(new Scene(root));
                } catch (IOException e) {
                    System.out.println("Error send input nickname");
                }

            }
        });
    }

}
