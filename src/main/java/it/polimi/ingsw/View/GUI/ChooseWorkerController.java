package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.Board;
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

public class ChooseWorkerController extends BoardController implements Initializable {

    String worker;
    BoardCell[][] grid;
    int row;
    int col;

    public ChooseWorkerController(Client client, String state) {
        super(client, state);
        grid = gui.getBoard().getGrid();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LoadNameAndCards();
        LoadBoard();
        setUpButtons();
        StateText(state);

    }

    public void setUpButtons() {
        for(Node node : Board.getChildren()) {
            node.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if(GridPane.getColumnIndex((Node) mouseEvent.getSource()) != null) {
                        col = (GridPane.getColumnIndex((Node) mouseEvent.getSource()));
                    } else {
                        col = 0;
                    }
                    if((GridPane.getRowIndex((Node) mouseEvent.getSource())) != null) {
                        row = (GridPane.getRowIndex((Node) mouseEvent.getSource()));
                    } else {
                        row = 0;
                    }
                    if (grid[row][col].getWorker() != null && gui.getMe().getNickname().equals(grid[row][col].getWorker().getPlayerWorker().getNickname())) {
                        if (grid[row][col].getWorker().getPlayerWorker().getWorkerRef().get(0).getIdWorker() == grid[row][col].getWorker().getIdWorker()) {
                            worker = 1 + "";
                        } else {
                            worker = 2 + "";
                        }
                        if(!gui.getMe().isEnableSpecialMove()) {
                            notifyChooseYourWorkerResponse(worker);
                        } else {
                            notifyChooseYourWorkerEffectResponse(worker, gui.getMe().isEnableSpecialMove());
                        }
                    }
                }
            });
        }

    }


}
