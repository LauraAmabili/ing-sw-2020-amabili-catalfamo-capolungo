package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
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

    Client client;
    UpdatesForMessages up;
    GUI gui;

    public ChosenCardsController(Client client) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
        gui = (GUI) client.getUserInterface();
    }

    @FXML
    ImageView Apollo = new ImageView();
    @FXML
    ImageView Ares = new ImageView();
    @FXML
    ImageView Artemis = new ImageView();
    @FXML
    ImageView Athena = new ImageView();
    @FXML
    ImageView Atlas = new ImageView();
    @FXML
    ImageView Charon = new ImageView();
    @FXML
    ImageView Chronus = new ImageView();
    @FXML
    ImageView Demeter = new ImageView();
    @FXML
    ImageView Hephaestus = new ImageView();
    @FXML
    ImageView Hestia = new ImageView();
    @FXML
    ImageView Minotaur = new ImageView();
    @FXML
    ImageView Pan = new ImageView();
    @FXML
    ImageView Prometheus = new ImageView();
    @FXML
    ImageView Zeus = new ImageView();

    int counter = 0;

    public Client getClient() {
        return client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setUpButtons();
    }

    public void setUpButtons() {
        if(gui.getCards().contains("Apollo")) {
            Apollo.setOpacity(0.7);
        }
        Apollo.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Apollo");
            this.Apollo.setOpacity(0.7);
            gui.getCards().add("Apollo");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Ares")) {
            Ares.setOpacity(0.7);
        }
        Ares.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Ares");
            this.Ares.setOpacity(0.7);
            gui.getCards().add("Ares");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Artemis")) {
            Artemis.setOpacity(0.7);
        }
        Artemis.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Artemis");
            Artemis.setOpacity(0.7);
            gui.getCards().add("Artemis");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Athena")) {
            Athena.setOpacity(0.7);
        }
        Athena.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Athena");
            Athena.setOpacity(0.7);
            gui.getCards().add("Athena");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Atlas")) {
            Atlas.setOpacity(0.7);
        }
        Atlas.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Atlas");
            Atlas.setOpacity(0.7);
            gui.getCards().add("Atlas");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Charon")) {
            Charon.setOpacity(0.7);
        }
        Charon.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Charon");
            this.Charon.setOpacity(0.7);
            gui.getCards().add("Charon");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Chronus")) {
            Chronus.setOpacity(0.7);
        }
        Chronus.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Chronus");
            this.Chronus.setOpacity(0.7);
            gui.getCards().add("Chronus");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Demeter")) {
            Demeter.setOpacity(0.7);
        }
        Demeter.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Demeter");
            Demeter.setOpacity(0.7);
            gui.getCards().add("Demeter");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Hephaestus")) {
            Hephaestus.setOpacity(0.7);
        }
        Hephaestus.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Hephaestus");
            Hephaestus.setOpacity(0.7);
            gui.getCards().add("Hephaestus");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Hestia")) {
            Hestia.setOpacity(0.7);
        }
        Hestia.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Hestia");
            this.Hestia.setOpacity(0.7);
            gui.getCards().add("Hestia");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Minotaur")) {
            Minotaur.setOpacity(0.7);
        }
        Minotaur.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Minotaur");
            Minotaur.setOpacity(0.7);
            gui.getCards().add("Minotaur");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Pan")) {
            Pan.setOpacity(0.7);
        }
        Pan.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Pan");
            Pan.setOpacity(0.7);
            gui.getCards().add("Pan");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Prometheus")) {
            Prometheus.setOpacity(0.7);
        }
        Prometheus.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Prometheus");
            Prometheus.setOpacity(0.7);
            gui.getCards().add("Prometheus");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
        if(gui.getCards().contains("Zeus")) {
            Zeus.setOpacity(0.7);
        }
        Zeus.setOnMouseClicked(mouseEvent -> {
            notifyChosenCardsUpdate("Zeus");
            this.Zeus.setOpacity(0.7);
            gui.getCards().add("Zeus");
            counter++;
            if(counter == client.getNumberOfPlayers()) {
                removeObserver(up);
            }
        });
    }
}
