package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PlaceWorkerController extends BoardController implements Initializable {

    int worker;
    String row;
    String column;

    public PlaceWorkerController(Client client, int worker, String state) {
        super(client, state);
        this.worker = worker;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setUpButtons();
        LoadNameAndCards();
        LoadBoard();
        StateText(state);
    }

    /**
     * Call a notify that return the row and column of the chosen pane.
     */
    public void setUpButtons() {
        for (Node node : Board.getChildren()) {
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(GridPane.getColumnIndex((Node)mouseEvent.getSource()) != null) {
                        column = (GridPane.getColumnIndex((Node) mouseEvent.getSource()) + 1) + "";
                    } else {
                        column = "1";
                    }
                    if(GridPane.getRowIndex((Node)mouseEvent.getSource()) != null) {
                        row = (GridPane.getRowIndex((Node) mouseEvent.getSource()) + 1) + "";
                    } else {
                        row = "1";
                    }
                    try {
                        notifyStartingSetWorkerResponse(row, column, worker);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
