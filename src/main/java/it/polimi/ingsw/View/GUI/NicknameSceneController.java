package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NicknameSceneController extends SceneController implements Initializable {

    Client client;

    @Override
    public Client getClient() {
        return client;
    }

    @FXML
    Button confirmNickButton = new Button();

    @FXML
    TextField nicknameTextField = new TextField();


    public NicknameSceneController(Client client) {
        super(client);
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
                } catch (IOException e) {
                    System.out.println("Errore send input nickname");
                }

            }
        });
    }
}
