package it.polimi.ingsw;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface {

    private String nickname;

    private Worker[] workerRef; // reference to the workers

    private God activeCard;

    public Player(String nickname, List<Worker> list){
        this.nickname = nickname;
        this.workerRef = new Worker[list.size()];
        for(int i = 0; i < list.size(); i++ ) {
            workerRef[i] = list.get(i);
        }
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

    public ArrayList<BoardCell> checkMovements(Worker worker) {
        ArrayList<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf(BoardCell::getWorker);
        adj.removeIf((n)-> (n.getLevel() > worker.getCurCell().getLevel()+1));
        return adj;
    }



    //returns an Arraylist of the boardcell where the worker can build
    public ArrayList<BoardCell> checkBuilding(Worker worker){

        ArrayList<BoardCell> adj = worker.getBoard().adjacentCells(worker.getCurCell());
        adj.removeIf(BoardCell::getWorker);
        adj.removeIf((n)-> (worker.getCurCell().getDome()));
        return adj;

    }

    public void win(Worker worker) {
        if(checkWin(worker))
            System.out.println(nickname + "wins");
    }


    @Override
    //update the location of the worker in Worker && update the presence of the worker in BoardCell
    public void move(int row, int col, Worker worker) {
        ArrayList<BoardCell> mov = checkMovements(worker);
        worker.getOldCell().setWorker(false);
        worker.setCurCell(worker.getBoard().getGrid()[row][col]);
        worker.getCurCell().setWorker(true);
    }

    @Override
    //update the level of the building in the BoardCell
    public void build(Worker worker, int row, int col) {
        ArrayList<BoardCell> canBuild = checkBuilding(worker);
        BoardCell b = worker.getBoard().getGrid()[row][col];
        if(b.getLevel() == 3) {
            b.setDome(true);
        } else {
            b.setLevel((b.getLevel() + 1));
        }

    }

    @Override
    public boolean checkWin(Worker worker) {

        return (worker.getOldCell().getLevel() < worker.getCurCell().getLevel() && worker.getCurCell().getLevel() == 3);

    }




}
