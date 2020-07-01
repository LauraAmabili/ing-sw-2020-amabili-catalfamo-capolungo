package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface, Serializable {


    //Player Attributes
    private String nickname;
    private List<Worker> workerRef = new ArrayList<>(); // reference to the workers
    private God activeCard;
    private transient Board board;
    private final List<God> chosenGods = new ArrayList<>();
    private boolean moveUp = true;
    private boolean enableSpecialMove;
    private boolean enableSpecialBuild;
    private boolean hasSpecialMove = false;
    private boolean hasSpecialBuild = false;
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isHasSpecialMove() {
        return hasSpecialMove;
    }

    @Override
    public boolean isHasTwoInputMove() {
        return false;
    }

    public void setHasSpecialMove(boolean hasSpecialMove) {
        this.hasSpecialMove = hasSpecialMove;
    }

    public boolean isHasSpecialBuild() {
        return hasSpecialBuild;
    }

    public boolean isHasTwoInputBuild() {
        boolean hasTwoInputBuild = false;
        return false;
    }

    public void setHasSpecialBuild(boolean hasSpecialBuild) {
        this.hasSpecialBuild = hasSpecialBuild;
    }

    public boolean isEnableSpecialBuild() {
        return enableSpecialBuild;
    }

    public void setEnableSpecialBuild(boolean enableSpecialBuild) {
        this.enableSpecialBuild = enableSpecialBuild;
    }

    public boolean isEnableSpecialMove() {
        return enableSpecialMove;
    }

    public void setEnableSpecialMove(boolean enableSpecialMove) {
        this.enableSpecialMove = enableSpecialMove;
    }

    public Player() {
    }

    public Player(String nickname, List<Worker> workerRef, Board board) {
        this.nickname = nickname;
        this.workerRef = workerRef;
        this.board = board;
    }

    @Override
    public boolean isMoveUp() {
        return moveUp;
    }

    @Override
    public void setMoveUp(boolean moveUp) {
        this.moveUp=moveUp;
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
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Override
    public void setWorkerRef(List<Worker> list) {
        workerRef.addAll(list);
    }

    @Override
    public List<Worker> getWorkerRef() {
        return workerRef;
    }


    /**
     * Add a Worker in the board for the first time
     * @param row
     * @param col
     * @param worker
     * @return
     */
    @Override
    public boolean addWorker(int row, int col, Worker worker) {
        List<BoardCell> list;
        list = board.freeCells();
        if(list.contains(getBoard().getGrid()[row][col])) {
            getBoard().getGrid()[row][col].setWorker(worker);
            worker.setCurCell(getBoard().getGrid()[row][col]);
            return true;
        } else {
            return false;
            //System.out.println("Cell is already occupied");
        }

    }

    /**
     * return an Arraylist of the boardcell where the worker can move
     * @param worker
     * @return
     */
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {

        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());

        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        if (moveUp){
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        }
        else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }
        return adj;


    }

    /**
     * return an Arraylist of the boardcell where the worker can move
     * @param worker
     * @return
     */
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect) {

        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());

        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        if (moveUp) {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel() + 1));
        } else {
            adj.removeIf((n) -> (n.getLevel() > worker.getCurCell().getLevel()));
        }
        return adj;

    }

    /**
     * update the location of the worker in Worker && update the presence of the worker in BoardCell
     * @param row
     * @param col
     * @param worker
     * @return
     */
    public boolean move(int row, int col, @NotNull Worker worker) {
        if (availableCellsToMove(worker).contains(this.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(this.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }

    public boolean move(int row, int col, @NotNull Worker worker, int rowBuild, int colBuild){
        return false;
    }

    /**
     * returns an Arraylist of the boardcell where the worker can build
     * @param worker
     * @return
     */
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker) {

        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        return adj;

    }


    /**
     * return an Arraylist of the boardcell where the worker can build
     * @param worker
     * @param specialEffect
     * @return
     */
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect) {

        List<BoardCell> adj = this.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n) -> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        return adj;

    }

    /**
     * update the level of the building in the BoardCell
     * @param row
     * @param col
     * @param worker
     * @return
     */
    public boolean build(int row, int col, @NotNull Worker worker) {
        if (availableCellsToBuild(worker).contains(this.getBoard().getGrid()[row][col])) {
            BoardCell b = this.getBoard().getGrid()[row][col];
            if (b.getLevel() == 3) {
                b.setDome(true);
            } else {
                b.setLevel((b.getLevel() + 1));
            }
            return true;
        }
        return false;
    }

    public boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect) {
        return false;
    }

    public boolean build(int row1, int col1, int row2, int col2, @NotNull Worker worker) {
        return false;
    }

    /**
     * check if the win move was performed by a worker
     * @param worker nuber of the worker
     * @return
     */
    public boolean checkWin(@NotNull Worker worker) {
        if (worker.getOldCell()==worker.getCurCell())
            return false;
        return ((worker.getOldCell().getLevel() < worker.getCurCell().getLevel())
                && worker.getCurCell().getLevel() == 3);
    }

    @Override
    public boolean move(int rowBuild, int colBuild, int row, int col, @NotNull Worker worker) {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }
}
