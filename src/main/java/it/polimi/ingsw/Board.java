package it.polimi.ingsw;

import jdk.internal.org.jline.terminal.Size;

import java.util.ArrayList;

public class Board {


    private static final int SIZE = 5;
    BoardCell[][] grid = new BoardCell[SIZE][SIZE];

    public void initialiseGrid (){
        for (int i=0; i<grid.length; i++) {
            for (int j=0; i<grid.length; i++) {
                grid[i][j] = new BoardCell(i, j);
            }
        }
    }

    //delete all workers from the game grid and any other references in other classes
    public void deleteWorkers(Player p) {
        for(int i = 0; i < p.getWorkerRef().length; i++) {
            p.getWorkerRef()[i].getCurCell().setWorker(false);
            p.getWorkerRef()[i] = null;
        }
    }


    public BoardCell[][] getGrid() {
        return grid;
    }

    public void printGrid (){
        for (int i=0; i<grid.length; i++) {
            for (int j=0; i<grid.length; i++) {
                System.out.print(grid[i][j].toString());
            }
            System.out.println();
        }
    }

    // given a cell create an ArrayList of all adjacent cells to that.
    public ArrayList<BoardCell> adjacentCells(BoardCell b) {
        int r_temp, c_temp;
        ArrayList<BoardCell> adj = new ArrayList<>();
        r_temp = b.getRow() - 1;
        if(r_temp < 0) { r_temp = 0;}
        c_temp = b.getCol() - 1;
        if(c_temp < 0) { c_temp = 0;}
        for (int i = r_temp; i <= b.getRow() + 1 && i < SIZE; i++) {
            for (int j = c_temp; j <= b.getCol() + 1 && j < SIZE; j++) {
                if (r_temp != b.getRow() && c_temp != b.getCol()) {
                    adj.add(grid[r_temp][c_temp]);
                }
            }
        }
        return adj;
    }

    // create an ArrayList of all cells with no workers on
    public ArrayList<BoardCell> freeCells() {
        ArrayList<BoardCell> b = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(!grid[i][j].getWorker()) {
                    b.add(grid[i][j]);
                }
            }
        }
        return b;
    }

}
