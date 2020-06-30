package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import it.polimi.ingsw.Network.Client.Client;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

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

    /**
     * Set up buttons for the scene. Call a notify that return the row and column of the chosen pane.
     */
    public void setUpButtons() {
        Worker w = null;
        if(gui.getMe().isEnableSpecialMove()) {
            UseEffect.setDisable(false);
            UseEffect.setSelected(true);
        } else if(!gui.getMe().isEnableSpecialMove()) {
            DontUseEffect.setDisable(false);
            DontUseEffect.setSelected(true);
        }
        Board board = gui.getBoard();
        for (int i = 0; i < board.getGrid()[0].length; i++) {
            for (int j = 0; j < board.getGrid()[0].length; j++) {
                Pane cell = (Pane) Board.getChildren().get(j + (i * 5));
                if(board.getGrid()[i][j].getWorker() != null) {
                    if(board.getGrid()[i][j].getWorker().getPlayerWorker().getNickname().equals(client.getNickname())) {
                        w = board.getGrid()[i][j].getWorker().getPlayerWorker().getWorkerRef().get(worker - 1);
                    }
                    if (!board.getGrid()[i][j].getWorker().equals(w)) {
                        ImageView imageView = (ImageView) cell.getChildren().get(1);
                        imageView.setOpacity(0.5);
                    }
                }
            }
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
                        //TODO: check this null
                        notifyMoveResponse(rowString, colString, worker, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
