package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardsToChooseController extends NotifyMessages implements Initializable {

    Client client;
    String CardName1;
    String CardName2;
    String CardName3;
    UpdatesForMessages up;
    int counter = 0;

    @FXML
    ImageView FirstCard = new ImageView();

    @FXML
    ImageView SecondCard = new ImageView();

    @FXML
    ImageView ThirdCard = new ImageView();

    public CardsToChooseController(Client client) {
        this.client = client;
        up = new UpdatesForMessages(client);
        addObserver(up);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUI gui = (GUI) client.getUserInterface();
        Stage primaryStage = gui.getPrimaryStage();
        File file;
        String tmpAddress;
        if(gui.getGodNames() != null) {
            for(String name : gui.getGodNames()) {
                if(FirstCard.getImage() == null) {
                    CardName1 = name;
                    file = new File(CardName1  + ".png");
                    try {
                        tmpAddress = file.toURI().toURL().toExternalForm();
                        FirstCard.setImage(new Image(tmpAddress));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else if(ThirdCard.getImage() == null) {
                    CardName3 = name;
                    file = new File(CardName3  + ".png");
                    try {
                        tmpAddress = file.toURI().toURL().toExternalForm();
                        ThirdCard.setImage(new Image(tmpAddress));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                } else {
                    CardName2 = name;
                    file = new File(CardName2  + ".png");
                    try {
                        tmpAddress = file.toURI().toURL().toExternalForm();
                        SecondCard.setImage(new Image(tmpAddress));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        setUp();
    }

    public void setUp() {
        GUI gui = (GUI) client.getUserInterface();
        FirstCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifySetYourCardResponse(CardName1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FirstCard.setOpacity(0.7);
                counter++;
                if(counter == client.getNumberOfPlayers()) {
                    removeObserver(up);
                }
            }
        });
        for(String chosenGod : gui.getChosenCards()) {
            if (gui.getGodNames().contains(chosenGod)) {
                FirstCard.setOpacity(0.7);
            }
        }
        if(CardName2 != null) {
            SecondCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        notifySetYourCardResponse(CardName2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SecondCard.setOpacity(0.7);
                    counter++;
                    if (counter == client.getNumberOfPlayers()) {
                        removeObserver(up);
                    }
                }
            });
            for(String chosenGod : gui.getChosenCards()) {
                if (gui.getGodNames().contains(chosenGod)) {
                    SecondCard.setOpacity(0.7);
                }
            }
        } else {
            SecondCard.disabledProperty();
        }
        ThirdCard.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    notifySetYourCardResponse(CardName3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ThirdCard.setOpacity(0.7);
                counter++;
                if(counter == client.getNumberOfPlayers()) {
                    removeObserver(up);
                }
            }
        });
        for(String chosenGod : gui.getChosenCards()) {
            if (gui.getGodNames().contains(chosenGod)) {
                ThirdCard.setOpacity(0.7);
            }
        }
    }
}
