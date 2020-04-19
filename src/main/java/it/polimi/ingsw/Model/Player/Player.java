package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Player implements PlayerInterface {

    private String nickname;

    private Worker[] workerRef; // reference to the workers

    private God activeCard;

    private boolean moveUp = true;

    public Player(String nickname, @NotNull List<Worker> list) {
        this.nickname = nickname;
        this.workerRef = new Worker[list.size()];
        for (int i = 0; i < list.size(); i++) {
            workerRef[i] = list.get(i);
        }
    }

    public Player(String nickname) {
        this.nickname = nickname;
    }


    public boolean isMoveUp() {
        return moveUp;
    }

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

    }

    @Override
    public void setWorkerRef(Worker[] worker) {

    }

    @Override
    public Worker[] getWorkerRef() {
        return workerRef;
    }

    public void setWorkerRef(List<Worker> list) {
        for (int i = 0; i < list.size(); i++) {
            workerRef[i] = list.get(i);
        }
    }


    /**
     * return a list of the BoardCell where the worker can move
     * @param worker
     * @return
     */
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());

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
        if (availableCellsToMove(worker).contains(worker.getBoard().getGrid()[row][col])) {
            worker.getCurCell().setWorker(null);
            worker.setOldCell(worker.getCurCell());
            worker.setCurCell(worker.getBoard().getGrid()[row][col]);
            worker.getCurCell().setWorker(worker);
            return true;
        }
        return false;
    }

    /**
     * returns an Arraylist of the boardcell where the worker can build
     * @param worker
     * @return
     */
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker) {

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
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
        if (availableCellsToBuild(worker).contains(worker.getBoard().getGrid()[row][col])) {
            BoardCell b = worker.getBoard().getGrid()[row][col];
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

    /**
     * check if the worker went from level 2 to level 3
     * @param worker
     * @return
     */
    public boolean checkWin(@NotNull Worker worker) {
        return ((worker.getOldCell().getLevel() < worker.getCurCell().getLevel()) && worker.getCurCell().getLevel() == 3);
    }

}
