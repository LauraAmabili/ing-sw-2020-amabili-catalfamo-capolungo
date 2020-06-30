package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TwoInputBuildingController extends BoardController implements Initializable {

    int col1;
    int row1;
    int col2;
    int row2;
    int worker;
    boolean firstInput = false;

    public TwoInputBuildingController(Client client, String state, int worker) {
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
        if(gui.getMe().isEnableSpecialBuild()) {
            UseEffect.setDisable(false);
            UseEffect.setSelected(true);
        } else {
            DontUseEffect.setDisable(false);
            DontUseEffect.setSelected(true);
        }
        for(Node node : Board.getChildren()) {
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    String rowString1 = null;
                    String colString1 = null;
                    if(firstInput) {
                        rowString1 = row2 + "";
                        colString1 = col2 + "";
                    }
                    if(GridPane.getColumnIndex((Node) mouseEvent.getSource()) != null) {
                        col2 = (GridPane.getColumnIndex((Node) mouseEvent.getSource()) + 1);
                    } else {
                        col2 = 1;
                    }
                    if((GridPane.getRowIndex((Node) mouseEvent.getSource())) != null) {
                        row2 = (GridPane.getRowIndex((Node) mouseEvent.getSource()) + 1);
                    } else {
                        row2 = 1;
                    }
                    String rowString2 = row2 + "";
                    String colString2 = col2 + "";
                    if(firstInput) {
                        notifyBuildTwoInputResponse(rowString1, colString1, rowString2, colString2, worker);
                    }
                    firstInput = true;
                }
            });
        }
    }

}
