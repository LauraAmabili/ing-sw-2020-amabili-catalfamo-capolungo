package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.PlayerFSA.*;
import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Player implements PlayerInterface {

    private final PlayerFSA initialized = new Initialized(this);
    private final PlayerFSA moving = new Moving(this);
    private final PlayerFSA building = new Building(this);
    private final PlayerFSA idle = new Idle(this);

    private PlayerFSA playerState;

    @Override
    public PlayerFSA getInitialized() {
        return initialized;
    }

    @Override
    public PlayerFSA getMoving() {
        return moving;
    }

    @Override
    public PlayerFSA getBuilding() {
        return building;
    }

    @Override
    public PlayerFSA getIdle() {
        return idle;
    }

    @Override
    public PlayerFSA getPlayerState() {
        return playerState;
    }

    @Override
    public void setPlayerState(PlayerFSA playerState) {
        this.playerState = playerState;
    }

    private String nickname;
    private List<Worker> workerRef; // reference to the workers
    private God activeCard;
    private Board board;
    private boolean moveUp = true;


    public Player(String nickname, @NotNull List<Worker> list, Board board) {
        this.nickname = nickname;
        this.workerRef = list;
        this.board = board;
        PlayerFSA playerState = initialized;
    }

    public Player(String nickname, Board board) {
        this.nickname = nickname;
        this.board = board;
        PlayerFSA playerState = initialized;
    }

    public Player(String nickname) {
        this.nickname = nickname;
        PlayerFSA playerState = initialized;
    }


    public boolean isMoveUp() {
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp=moveUp;
    }

    public void StateMove(int row, int col, Worker worker) {
        playerState.Move(row, col, worker);
        setPlayerState(building);
    }

    public void StateBuild(int row, int col, Worker worker) {
        playerState.Build(row, col, worker);
        setPlayerState(idle);
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setActiveCard(God activeCard) {
        playerState.setCard(activeCard);
        setPlayerState(moving);
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
        this.workerRef = list;
    }

    @Override
    public List<Worker> getWorkerRef() {
        return workerRef;
    }

    //To be Decorated

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

    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect) {
        return null;
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

    public boolean move(int row, int col, @NotNull Worker worker, boolean specialEffect, int rowBuild, int colBuild){
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

    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect) {
        return null;
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

    public boolean build(int row1, int col1, @NotNull Worker worker, int row2, int col2) {
        return false;
    }

    public boolean checkWin(@NotNull Worker worker) {
        return ((worker.getOldCell().getLevel() < worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3);
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
