package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public interface PlayerInterface extends Serializable {


    String nickname = null;
    List<Worker> workerRef = null;

    God activeCard = null;

    Board board = null;

    String color = null;

    String getColor();

    void setColor(String color);

    void setWorkerRef(List<Worker> list);

    List<Worker> getWorkerRef();

    void setActiveCard(God ActiveCard);

    God getActiveCard();

    Board getBoard();

    void setBoard(Board board);

    void setNickname(String nickname);

    String getNickname();

    boolean isMoveUp();

    void setMoveUp(boolean moveUp);

    boolean addWorker(int row, int col, Worker worker);


    /**
     * Checking the available cells for the moving based on the player
     * @param worker number of the worker
     * @return list of BoardCells available for the move
     */
    List<BoardCell> availableCellsToMove(@NotNull Worker worker);


    /**
     * Checking the available cells for the moving based on the effect of the player
     * @param worker number of the worker
     * @return list of BoardCells available for the move
     */
    List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect);


    /**
     * This method moves the worker based on the player that uses it
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @return boolean if ends correctly
     */
    boolean move(int row, int col, @NotNull Worker worker);


    /**
     * Checking the available cells for the building based on the player
     * @param worker number of the worker
     * @return list of BoardCells available for the build
     */
    List<BoardCell> availableCellsToBuild(@NotNull Worker worker);


    /**
     * Checking the available cells for the building based on the effect of the player
     * @param worker number of the worker
     * @return list of BoardCells available for the build
     */
    List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect);


    /**
     * This method builds the worker based on the player
     * @param row chosen row
     * @param col chosen col
     * @param worker worker doing the action
     * @return boolean if ends correctly
     */
    boolean build(int row, int col, @NotNull Worker worker);



    /**
     * This method builds the worker based on the fact that the player has the building effect
     * @param row chosen row
     * @param col chosen col
     * @param worker worker doing the action
     * @return boolean if ends correctly
     */
    boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect);


    /**
     * This method builds the worker based on the player that uses it
     * @param row1 chosen row for the first action
     * @param col1 chosen col for the first action
     * @param row2 chosen row for the second action
     * @param col2 chosen col for the second action
     * @param worker worker doing the action
     * @return boolean if ends correctly
     */
    boolean build(int row1, int col1, int row2, int col2, @NotNull Worker worker);

    /**
     * This method checks if the player is the winner
     * @param worker number of the worker
     * @return boolean if the player wins
     */
    boolean checkWin(@NotNull Worker worker);

    /**
     * This method moves the worker based on the player that uses it
     * @param rowBuild chosen row for the build
     * @param colBuild chosen col for the build
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @return boolean if ends correctly
     */
    boolean move(int rowBuild, int colBuild, int row, int col, @NotNull Worker worker);

    boolean isEnableSpecialMove();

    void setEnableSpecialMove(boolean enableSpecialMove);

    boolean isEnableSpecialBuild();

    void setEnableSpecialBuild(boolean enableSpecialBuild);

    boolean isHasSpecialMove();

    boolean isHasTwoInputMove();

    void setHasSpecialMove(boolean hasSpecialMove);

    boolean isHasSpecialBuild();

    boolean isHasTwoInputBuild();

    void setHasSpecialBuild(boolean hasSpecialBuild);

}
