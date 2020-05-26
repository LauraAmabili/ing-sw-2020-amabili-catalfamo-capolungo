package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Network.Client.Client;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MovingController extends BoardController implements Initializable {

    int col;
    int row;
    int worker;

    public MovingController(Client client, String state,int worker) {
        super(client, state);
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
        if(gui.getMe().isEnableSpecialMove()) {
            UseEffect.setDisable(false);
            UseEffect.setSelected(true);
        } else if(!gui.getMe().isEnableSpecialMove()) {
            DontUseEffect.setDisable(false);
            DontUseEffect.setSelected(true);
        }
        for(Node node : Board.getChildren()) {
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(GridPane.getColumnIndex((Node) mouseEvent.getSource()) != null) {
                        col = (GridPane.getColumnIndex((Node) mouseEvent.getSource()) + 1);
                    } else {
                        col = 1;
                    }
                    if((GridPane.getRowIndex((Node) mouseEvent.getSource())) != null) {
                        row = (GridPane.getRowIndex((Node) mouseEvent.getSource()) + 1);
                    } else {
                        row = 1;
                    }
                    String rowString = row + "";
                    String colString = col + "";
                    try {
                        notifyMoveResponse(rowString, colString, worker);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
