package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChosenCardsController extends NotifyMessages implements Initializable {

    final Client client;

    final UpdatesForMessages up;

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
        Apollo.setOnMouseClicked(mouseEvent -> {
            try {
                this.Apollo.setOpacity(0.7);
                notifyChosenCardsUpdate("Apollo");
                counter++;
                if(counter == client.getNumberOfPlayers()) {
                    removeObserver(up);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Artemis.setOnMouseClicked(mouseEvent -> {
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
        });
        Athena.setOnMouseClicked(mouseEvent -> {
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
        });
        Atlas.setOnMouseClicked(mouseEvent -> {
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
        });
        Demeter.setOnMouseClicked(mouseEvent -> {
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
        });
        Hephaestus.setOnMouseClicked(mouseEvent -> {
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
        });
        Minotaur.setOnMouseClicked(mouseEvent -> {
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
        });
        Pan.setOnMouseClicked(mouseEvent -> {
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
        });
        Prometheus.setOnMouseClicked(mouseEvent -> {
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
        });
    }
}
