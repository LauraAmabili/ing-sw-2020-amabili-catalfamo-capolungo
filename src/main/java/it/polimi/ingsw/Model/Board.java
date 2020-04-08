package it.polimi.ingsw.Model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Board {


    private static final int SIZE = 5;
    BoardCell[][] grid = new BoardCell[SIZE][SIZE];

    public Board() {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid.length; j++) {
                grid[i][j] = new BoardCell(i, j);
            }
        }
    }

    //delete all workers from the game grid and any other references in other classes
    public void deleteWorkers(@NotNull Player p) {
        for(int i = 0; i < p.getWorkerRef().length; i++) {
            p.getWorkerRef()[i].getCurCell().setWorker(null);
            p.getWorkerRef()[i] = null;
        }
    }


    public BoardCell[][] getGrid() {
        return grid;
    }

    public void printGrid () {
        for (BoardCell[] boardCells : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("+---------------------");
            }
            System.out.print("+");
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < grid[0].length; j++) {
                if (boardCells[j].getWorker() == null) {
                    for (int k = 0; k < 10; k++) {
                        System.out.print(" ");
                    }
                    if (boardCells[j].getLevel() == 3 && boardCells[j].getDome()) {
                        System.out.print("4");
                    } else {
                        System.out.print(boardCells[j].getLevel());
                        if (boardCells[j].getWorker() == null) {
                            for (int k = 0; k < 10; k++) {
                                System.out.print(" ");
                            }
                        }
                    }
                } else {
                    int spaces = (21 - (boardCells[j].getWorker().getPlayerWorker().getNickname().length() + 4)) / 2;
                    for (int k = 0; k < spaces; k++) {
                        System.out.print(" ");
                    }
                    if (boardCells[j].getWorker() != null) {
                        for (int g = 0; g < boardCells[j].getWorker().getPlayerWorker().getWorkerRef().length; g++) {
                            if (boardCells[j].getWorker().getPlayerWorker().getWorkerRef()[g] == boardCells[j].getWorker()) {
                                System.out.print("W" + (g + 1) + boardCells[j].getWorker().getPlayerWorker().getNickname() + "-");
                            }
                        }
                    }
                    if (boardCells[j].getLevel() == 3 && boardCells[j].getDome()) {
                        System.out.print("4");
                    } else {
                        System.out.print(boardCells[j].getLevel());
                    }
                    for (int k = 0; k < (21 - (boardCells[j].getWorker().getPlayerWorker().getNickname().length() + 4)) - spaces; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.print("|");
            }
            System.out.println();
        }
        for (int i = 0; i < grid.length; i++) {
            System.out.print("+---------------------");
        }
        System.out.println("+");

    }


    // given a cell create an gridList of all adjacent cells to that.
    public List<BoardCell> adjacentCells(@NotNull BoardCell b) {
        int r_temp, c_temp;
        List<BoardCell> adj = new ArrayList<>();
        r_temp = b.getRow() - 1;
        if(r_temp < 0) { r_temp = 0;}
        c_temp = b.getCol() - 1;
        if(c_temp < 0) { c_temp = 0;}
        for (int i = r_temp; i <= b.getRow() + 1 && i < SIZE; i++) {
            for (int j = c_temp; j <= b.getCol() + 1 && j < SIZE; j++) {
                if (!(i == b.getRow() && j == b.getCol())) {
                    adj.add(grid[i][j]);
                }
            }
        }
        return adj;
    }

    // create an gridList of all cells with no workers on
    public List<BoardCell> freeCells() {
        List<BoardCell> b = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(grid[i][j].getWorker() == null) {
                    b.add(grid[i][j]);
                }
            }
        }
        return b;
    }

}
