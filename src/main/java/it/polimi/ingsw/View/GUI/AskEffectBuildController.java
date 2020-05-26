package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AskEffectBuildController extends BoardController implements Initializable {

    String name;
    int worker;

    public AskEffectBuildController(Client client, String state, String name, int worker) {
        super(client, state);
        this.name = name;
        this.worker = worker;
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
                try {
                    gui.getMe().setEnableSpecialMove(true);
                    notifyAskeffectBuildResponse("y", name, worker);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        DontUseEffect.setDisable(false);
        DontUseEffect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    notifyAskeffectBuildResponse("n", name, worker);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}