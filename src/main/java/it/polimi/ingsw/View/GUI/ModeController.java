package it.polimi.ingsw.View.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


public class ModeController extends SceneController {

    @FXML
    Button TwoPlayerButton = new Button();
    @FXML
    Button ThreePlayerButton = new Button();



    public void TwoPlayerMode() throws IOException {

        notifyPlayerNumberResponse("2");

    }

    public void ThreePlayerMode() throws IOException {

        notifyPlayerNumberResponse("3");

    }

}
