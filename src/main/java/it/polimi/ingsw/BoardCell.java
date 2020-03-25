package it.polimi.ingsw;

public class BoardCell {

    private int row;
    private int col;
    private int level;
    private Worker worker;
    private boolean dome;

    public BoardCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setWorker(Worker worker){
        this.worker = worker;
    }

    public void setDome(boolean options){
        this.dome = options;
    }

    public int getLevel(){
        return level;
    }

    public Worker getWorker(){
        return worker;
    }

    public boolean getDome(){
        return dome;
    }

    public int getRow(){
        return row;
    }
    public int getCol(){
        return  col;
    }
}
