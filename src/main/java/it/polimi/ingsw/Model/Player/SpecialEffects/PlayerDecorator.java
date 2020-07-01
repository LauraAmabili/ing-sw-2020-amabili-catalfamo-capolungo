package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.List;

public class PlayerDecorator implements PlayerInterface, Serializable {

    // attributes
    protected final PlayerInterface player;
    private String nickname;
    private List<Worker> workerRef; // reference to the workers
    private List<God> chosenGods;
    private God activeCard;
    private Board board;
    private transient boolean moveUp;

    // constructor
    public PlayerDecorator(PlayerInterface player) {
        this.player = player;
        this.nickname = player.getNickname();
        this.workerRef = player.getWorkerRef();
        this.activeCard = player.getActiveCard();
        this.board = player.getBoard();
        this.moveUp = player.isMoveUp();
        String color = player.getColor();
        for (Worker worker : workerRef) {
            worker.setPlayerWorker(this);
        }
    }


    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setActiveCard(God activeCard) {
        this.activeCard = activeCard;
    }

    @Override
    public God getActiveCard() {
        return activeCard;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public boolean addWorker(int row, int col, Worker worker) {
        return player.addWorker(row, col, worker);
    }

    @Override
    public String getColor() {
        return null;
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setWorkerRef(List<Worker> list) {
        this.workerRef = list;
    }

    @Override
    public List<Worker> getWorkerRef() {
        return workerRef;
    }

    /**
     * Checking the available cells for the moving based on the player
     * @param worker number of the worker
     * @return list of BoardCells available for the move
     */
    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        return player.availableCellsToMove(worker);
    }


    /**
     * Checking the available cells for the moving based on the effect of the player
     * @param worker number of the worker
     * @return list of BoardCells available for the move
     */
    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect) {
        return player.availableCellsToMove(worker, specialEffect);
    }


    /**
     * This method moves the worker based on the player that uses it
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @return boolean if ends correctly
     */
    @Override
    public boolean move(int row, int col, @NotNull Worker worker) {
        return player.move(row, col, worker);
    }


    /**
     * Checking the available cells for the building based on the player
     * @param worker number of the worker
     * @return list of BoardCells available for the build
     */
    @Override
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker) {
        return player.availableCellsToBuild(worker);
    }

    /**
     * Checking the available cells for the building based on the effect of the player
     * @param worker number of the worker
     * @return list of BoardCells available for the build
     */
    @Override
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect) {
        return player.availableCellsToBuild(worker, specialEffect);
    }

    /**
     * This method builds the worker based on the player
     * @param row chosen row
     * @param col chosen col
     * @param worker worker doing the action
     * @return boolean if ends correctly
     */
    @Override
    public boolean build(int row, int col, @NotNull Worker worker) {
        return player.build(row, col, worker);
    }

    /**
     * This method builds the worker based on the fact that the player has the building effect
     * @param row chosen row
     * @param col chosen col
     * @param worker worker doing the action
     * @return boolean if ends correctly
     */
    @Override
    public boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect) {
        return player.build(row, col, worker, specialEffect);
    }

    /**
     * This method builds the worker based on the player that uses it
     * @param row1 chosen row for the first action
     * @param col1 chosen col for the first action
     * @param row2 chosen row for the second action
     * @param col2 chosen col for the second action
     * @param worker worker doing the action
     * @return boolean if ends correctly
     */
    @Override
    public boolean build(int row1, int col1, int row2, int col2, @NotNull Worker worker) {
        return player.build(row1, col1, row2, col2, worker);
    }

    /**
     * This method checks if the player is the winner
     * @param worker number of the worker
     * @return boolean if the player wins
     */
    @Override
    public boolean checkWin(@NotNull Worker worker) {
        return player.checkWin(worker);
    }

    /**
     * This method moves the worker based on the player that uses it
     * @param rowBuild chosen row for the build
     * @param colBuild chosen col for the build
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @return boolean if ends correctly
     */
    @Override
    public boolean move(int rowBuild, int colBuild, int row, int col, @NotNull Worker worker) {
        return player.move(row, col, rowBuild, colBuild, worker);
    }

    @Override
    public boolean isEnableSpecialMove() {
        return false;
    }

    @Override
    public void setEnableSpecialMove(boolean enableSpecialMove) {

    }

    @Override
    public boolean isEnableSpecialBuild() {
        return false;
    }

    @Override
    public void setEnableSpecialBuild(boolean enableSpecialBuild) {

    }

    @Override
    public boolean isHasSpecialMove() {
        return false;
    }

    @Override
    public boolean isHasTwoInputMove() {
        return false;
    }

    @Override
    public void setHasSpecialMove(boolean hasSpecialMove) {
    }

    @Override
    public boolean isHasSpecialBuild() {
        return false;
    }

    @Override
    public boolean isHasTwoInputBuild() {
        return false;
    }

    @Override
    public void setHasSpecialBuild(boolean hasSpecialBuild) {
    }

}
