package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChosenCardsController extends SceneController implements Initializable {

    Client client;

    UpdatesForMessages up;

    public ChosenCardsController(Client client) {
        super(client);
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @FXML
    ImageView FirstCard = new ImageView();

    @FXML
    ImageView SecondCard = new ImageView();

    @FXML
    ImageView ThirdCard = new ImageView();

    @Override
    public Client getClient() {
        return client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpButtons();
    }

    public void setUpButtons() {
        FirstCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Apollo");
                    removeObserver(up);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
