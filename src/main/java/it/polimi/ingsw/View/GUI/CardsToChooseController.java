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
import java.io.IOException;
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
        LoadCards();
        setUp();
    }

    public void setUp() {
        GUI gui = (GUI) client.getUserInterface();
        FirstCard.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

            }
        });
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
        if (gui.getChosenCards().contains(CardName1)) {
            FirstCard.setOpacity(0.7);
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
            if (gui.getChosenCards().contains(CardName2)) {
                SecondCard.setOpacity(0.7);
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
        if (gui.getChosenCards().contains(CardName3)) {
            ThirdCard.setOpacity(0.7);
        }
    }

    public void LoadCards() {
        GUI gui = (GUI) client.getUserInterface();
        String url;
        if(gui.getGodNames() != null) {
            for(String name : gui.getGodNames()) {
                if(FirstCard.getImage() == null) {
                    CardName1 = name;
                    url = "/godCards/" + CardName1 + ".png";
                    FirstCard.setImage(new Image(url));
                } else if(ThirdCard.getImage() == null) {
                    CardName3 = name;
                    url = "/godCards/" + CardName3  + ".png";
                    ThirdCard.setImage(new Image(url));
                } else {
                    CardName2 = name;
                    url = "/godCards/" + CardName2  + ".png";
                    SecondCard.setImage(new Image(url));
                }
            }
        }
    }
}
