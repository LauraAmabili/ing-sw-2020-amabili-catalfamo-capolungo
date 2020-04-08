package it.polimi.ingsw.Model;

import it.polimi.ingsw.Decorator.PlayerInterface;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Player implements PlayerInterface {

    private String nickname;

    private Worker[] workerRef; // reference to the workers

    private God activeCard;

    public Player(String nickname, @NotNull List<Worker> list){
        this.nickname = nickname;
        this.workerRef = new Worker[list.size()];
        for(int i = 0; i < list.size(); i++ ) {
            workerRef[i] = list.get(i);
        }
    }

    public void decorate() {

    }

    public String getNickname(){
        return nickname;
    }

    public void setActiveCard(God activeCard) {
        this.activeCard = activeCard;
    }

    public God getActiveCard() {
        return activeCard;
    }

    public Worker[] getWorkerRef() {
        return workerRef;
    }

    public void setWorkerRef(List<Worker> list) {
        for(int i = 0; i < list.size(); i++ ) {
            workerRef[i] = list.get(i);
        }
    }

    // returns an Arraylist of the boardcell where the worker can move

    public List<BoardCell> availableCellsToMove(@NotNull Worker worker) {
        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n)-> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        adj.removeIf((n)-> (n.getLevel() > worker.getCurCell().getLevel()+1));
        return adj;
    }



    //returns an Arraylist of the boardcell where the worker can build
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){

        List<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf((n)-> n.getWorker() != null);
        adj.removeIf(BoardCell::getDome);
        return adj;

    }

    @Override
    //update the location of the worker in Worker && update the presence of the worker in BoardCell
    public void move(int row, int col, @NotNull Worker worker) {
        worker.getCurCell().setWorker(null);
        worker.setOldCell(worker.getCurCell());
        worker.setCurCell(worker.getBoard().getGrid()[row][col]);
        worker.getCurCell().setWorker(worker);
    }

    @Override
    //update the level of the building in the BoardCell
    public void build(int row, int col, @NotNull Worker worker) {
        BoardCell b = worker.getBoard().getGrid()[row][col];
        if(b.getLevel() == 3) {
            b.setDome(true);
        } else {
            b.setLevel((b.getLevel() + 1));
        }
    }

    @Override
    public boolean checkWin(@NotNull Worker worker) {

        return (worker.getOldCell().getLevel() < worker.getCurCell().getLevel() && worker.getCurCell().getLevel() == 3);

    }




}