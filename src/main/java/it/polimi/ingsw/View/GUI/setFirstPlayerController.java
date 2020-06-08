package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class setFirstPlayerController extends NotifyMessages implements Initializable {

    Client client;
    UpdatesForMessages up;
    GUI gui;
    List<PlayerInterface> list;

    @FXML
    ImageView Person1 = new ImageView();
    @FXML
    ImageView Person2 = new ImageView();
    @FXML
    ImageView Person3 = new ImageView();
    @FXML
    ImageView Stage1 = new ImageView();
    @FXML
    ImageView Stage2 = new ImageView();
    @FXML
    ImageView Stage3 = new ImageView();
    @FXML
    TextField PlayerNick1 = new TextField();
    @FXML
    TextField PlayerNick2 = new TextField();
    @FXML
    TextField PlayerNick3 = new TextField();
    @FXML
    TextField ActionText = new TextField();

    public setFirstPlayerController(Client client, List<PlayerInterface> list) {
        this.client = client;
        this.list = list;
        up = new UpdatesForMessages(client);
        addObserver(up);
        gui = (GUI) client.getUserInterface();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadScene();
        setUpButtons();
    }

    public void LoadScene() {
        String name = list.get(0).getNickname();
        PlayerNick1.setText(name);
        setColor(list.get(0), PlayerNick1);
        name = list.get(1).getNickname();
        PlayerNick3.setText(name);
        setColor(list.get(1), PlayerNick3);
        if(list.size() == 2) {
            PlayerNick2.setDisable(true);
            PlayerNick2.setVisible(false);
        } else {
            Stage2.setImage(new Image("Images/fg_panel4.png"));
            Person2.setImage(new Image("Images/Human.png"));
            name = list.get(2).getNickname();
            PlayerNick2.setText(name);
            setColor(list.get(2), PlayerNick2);
        }
        ActionText.setText("Choose first player");
    }

    public void setUpButtons() {
        Stage1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String numbPlayer = null;
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getNickname().equals(PlayerNick1.getText())) {
                            numbPlayer = i + 1 + "";
                        }
                    }
                    notifyPlayerThatStart(numbPlayer, list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        if(list.size() == 3) {
            Stage2.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    try {
                        String numbPlayer = null;
                        for(int i = 0; i < list.size(); i++) {
                            if(list.get(i).getNickname().equals(PlayerNick1.getText())) {
                                numbPlayer = i + 1 + "";
                            }
                        }
                        notifyPlayerThatStart(numbPlayer, list);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        Stage3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    String numbPlayer = null;
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getNickname().equals(PlayerNick1.getText())) {
                            numbPlayer = i + 1 + "";
                        }
                    }
                    notifyPlayerThatStart(numbPlayer, list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setColor(PlayerInterface player, TextField NameText) {
        if(player.getWorkerRef().get(0).getColor().equals("\u001B[34m") ) {
            NameText.setStyle("-fx-text-fill: blue");
        } else if(player.getWorkerRef().get(0).getColor().equals("\u001B[35m")) {
            NameText.setStyle("-fx-text-fill: darkmagenta");
        } else {
            NameText.setStyle("-fx-text-fill: goldenrod");
        }
    }

}
