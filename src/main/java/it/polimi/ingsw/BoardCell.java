package it.polimi.ingsw;

public class BoardCell {

    private int row;
    private int col;
    private int level;
    private boolean worker;
    private boolean dome;

    public BoardCell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setWorker(){
        this.worker = false;
    }

    public void setWorker(boolean statement) {
        this.worker = statement;
    }

    public void setDome(boolean options){
        this.dome = options;
    }

    public int getLevel(){
        return level;
    }

    public boolean getWorker(){
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
