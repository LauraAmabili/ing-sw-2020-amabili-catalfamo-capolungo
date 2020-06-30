package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AskEffectController extends BoardController implements Initializable {

    String name;

    public AskEffectController(Client client, String state, String name) {
        super(client, state);
        this.name = name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadNameAndCards();
        LoadBoard();
        setUpButtons();
        StateText(state);
    }

    public void setUpButtons() {

        UseEffect.setDisable(false);
        UseEffect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gui.getMe().setEnableSpecialMove(true);
                notifyAskEffectReply("y", name);
            }
        });
        DontUseEffect.setDisable(false);
        DontUseEffect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                notifyAskEffectReply("n", name);
            }
        });

    }

}
