package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.NotifyMessages;
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

public class ChosenCardsController extends NotifyMessages implements Initializable {

    Client client;

    UpdatesForMessages up;

    public ChosenCardsController(Client client) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @FXML
    ImageView Apollo = new ImageView();
    @FXML
    ImageView Artemis = new ImageView();
    @FXML
    ImageView Athena = new ImageView();
    @FXML
    ImageView Atlas = new ImageView();
    @FXML
    ImageView Demeter = new ImageView();
    @FXML
    ImageView Hephaestus = new ImageView();
    @FXML
    ImageView Minotaur = new ImageView();
    @FXML
    ImageView Pan = new ImageView();
    @FXML
    ImageView Prometheus = new ImageView();

    int counter = 0;

    public Client getClient() {
        return client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpButtons();
    }

    public void setUpButtons() {
        Apollo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Apollo");
                    Apollo.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Artemis.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Artemis");
                    Artemis.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Athena.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Athena");
                    Athena.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Atlas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Atlas");
                    Atlas.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Demeter.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Demeter");
                    Demeter.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Hephaestus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Hephaestus");
                    Hephaestus.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Minotaur.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Minotaur");
                    Minotaur.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Pan.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Pan");
                    counter++;
                    Pan.setOpacity(0.7);
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Prometheus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifyChosenCardsUpdate("Prometheus");
                    Prometheus.setOpacity(0.7);
                    counter++;
                    if(counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
