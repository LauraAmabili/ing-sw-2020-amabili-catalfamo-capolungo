package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

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
        UseEffect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    gui.getMe().setEnableSpecialMove(true);
                    notifyAskEffectReply("y", name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        DontUseEffect.setDisable(false);
        DontUseEffect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    notifyAskEffectReply("n", name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
