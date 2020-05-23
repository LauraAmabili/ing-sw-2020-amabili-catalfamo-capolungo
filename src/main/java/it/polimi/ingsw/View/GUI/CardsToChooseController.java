package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.God.God;
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
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CardsToChooseController extends NotifyMessages implements Initializable {

    final Client client;
    String CardName1;
    String CardName2;
    String CardName3;
    final UpdatesForMessages up;
    int counter = 0;

    @FXML
    ImageView FirstCard = new ImageView();
    @FXML
    ImageView SecondCard = new ImageView();
    @FXML
    ImageView ThirdCard = new ImageView();
    @FXML
    Text Description1 = new Text();
    @FXML
    Text Description2 = new Text();
    @FXML
    Text Description3 = new Text();

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
        FirstCard.setOnMouseEntered(mouseEvent -> {
            for(int i = 0; i < gui.getGodNames().size(); i++) {
                if(gui.getGodNames().get(i).getGodName().equals(CardName1)) {
                    Description1.setText(gui.getGodNames().get(i).getDescriptionEffect());
                }
            }
        });
        FirstCard.setOnMouseClicked(mouseEvent -> {
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
        });
        if (gui.getChosenCards().contains(CardName1)) {
            FirstCard.setOpacity(0.7);
        }
        if(CardName2 != null) {
            SecondCard.setOnMouseEntered(mouseEvent -> {
                for(int i = 0; i < gui.getGodNames().size(); i++) {
                    if(gui.getGodNames().get(i).getGodName().equals(CardName2)) {
                        Description2.setText(gui.getGodNames().get(i).getDescriptionEffect());
                    }
                }
            });
            SecondCard.setOnMouseClicked(mouseEvent -> {
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
            });
            if (gui.getChosenCards().contains(CardName2)) {
                SecondCard.setOpacity(0.7);
            }
        } else {
            SecondCard.disabledProperty();
        }
        ThirdCard.setOnMouseEntered(mouseEvent -> {
            for(int i = 0; i < gui.getGodNames().size(); i++) {
                if(gui.getGodNames().get(i).getGodName().equals(CardName3)) {
                    Description3.setText(gui.getGodNames().get(i).getDescriptionEffect());
                }
            }
        });
        ThirdCard.setOnMouseClicked(mouseEvent -> {
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
        });
        if (gui.getChosenCards().contains(CardName3)) {
            ThirdCard.setOpacity(0.7);
        }
    }

    public void LoadCards() {
        GUI gui = (GUI) client.getUserInterface();
        String url;
        if(gui.getGodNames() != null) {
            for(God name : gui.getGodNames()) {
                if(FirstCard.getImage() == null) {
                    CardName1 = name.getGodName();
                    url = "/godCards/" + CardName1 + ".png";
                    FirstCard.setImage(new Image(url));
                } else if(ThirdCard.getImage() == null) {
                    CardName3 = name.getGodName();
                    url = "/godCards/" + CardName3  + ".png";
                    ThirdCard.setImage(new Image(url));
                } else {
                    CardName2 = name.getGodName();
                    url = "/godCards/" + CardName2  + ".png";
                    SecondCard.setImage(new Image(url));
                }
            }
        }
    }
}
