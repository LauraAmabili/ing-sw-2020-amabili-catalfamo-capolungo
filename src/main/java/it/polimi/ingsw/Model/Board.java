package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable {


    private static final int SIZE = 5;
    BoardCell[][] grid = new BoardCell[SIZE][SIZE];

    public Board() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new BoardCell(i, j);
            }
        }
    }

    public static int getSIZE() {
        return SIZE;
    }

    /**
     * Delete all Player's workers
     * @param p
     */
    public void deleteWorkers(@NotNull Player p) {
        for (int i = p.getWorkerRef().size() - 1; i > 0; i--) {
            p.getWorkerRef().get(i).getCurCell().setWorker(null);
            p.getWorkerRef().remove(i);
        }
    }


    public BoardCell[][] getGrid() {
        return grid;
    }

    /**
     * Print the grid
     */
    public void printGrid() {
        for (BoardCell[] boardCells : grid) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("+-------------------");
            }
            System.out.print("+");
            System.out.println();
            System.out.print("|");
            for(int l = 0; l < 2; l++) {
                for(int z = 0; z < grid[0].length; z++) {
                    for (int k = 0; k < 19; k++) {
                        System.out.print(" ");
                    }
                    System.out.print("|");
                }
                System.out.println();
                System.out.print("|");
            }
            for (int j = 0; j < grid[0].length; j++) {
                if (boardCells[j].getWorker() == null) {
                    for (int k = 0; k < 9; k++) {
                        System.out.print(" ");
                    }
                    if (boardCells[j].getWorker() == null) {
                        System.out.print(boardCells[j].getLevel());
                        if(boardCells[j].getDome()) {
                            System.out.print("D");
                            for (int k = 0; k < 9; k++) {
                                System.out.print(" ");
                            }
                        } else {
                            for (int k = 0; k < 9; k++) {
                                System.out.print(" ");
                            }
                        }
                    }
                } else {
                    if(!boardCells[j].getDome()) {
                        int spaces = (19 - (boardCells[j].getWorker().getPlayerWorker().getNickname().length() + 4)) / 2;
                        for (int k = 0; k < spaces; k++) {
                            System.out.print(" ");
                        }
                        if (boardCells[j].getWorker() != null) {
                            for (int g = 0; g < boardCells[j].getWorker().getPlayerWorker().getWorkerRef().size(); g++) {
                                if (boardCells[j].getWorker().getPlayerWorker().getWorkerRef().get(g) == boardCells[j].getWorker()) {
                                    System.out.print(boardCells[j].getWorker().getColor() + "W" + (g + 1) + boardCells[j].getWorker().getPlayerWorker().getNickname() + "\u001B[0m" + "-");
                                }
                            }
                        }
                        System.out.print(boardCells[j].getLevel());
                        for (int k = 0; k < (19 - (boardCells[j].getWorker().getPlayerWorker().getNickname().length() + 4)) - spaces; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        int spaces = (19 - (boardCells[j].getWorker().getPlayerWorker().getNickname().length() + 5)) / 2;
                        for (int k = 0; k < spaces; k++) {
                            System.out.print(" ");
                        }
                        if (boardCells[j].getWorker() != null) {
                            for (int g = 0; g < boardCells[j].getWorker().getPlayerWorker().getWorkerRef().size(); g++) {
                                if (boardCells[j].getWorker().getPlayerWorker().getWorkerRef().get(g) == boardCells[j].getWorker()) {
                                    System.out.print("W" + (g + 1) + boardCells[j].getWorker().getPlayerWorker().getNickname() + "-");
                                }
                            }
                        }
                        System.out.print(boardCells[j].getLevel());
                        System.out.print("D");
                        for (int k = 0; k < (19 - (boardCells[j].getWorker().getPlayerWorker().getNickname().length() + 5)) - spaces; k++) {
                            System.out.print(" ");
                        }
                    }
                }
                System.out.print("|");
            }
            System.out.println();
            System.out.print("|");
            for(int l = 0; l < 1; l++) {
                for(int z = 0; z < grid[0].length; z++) {
                    for (int k = 0; k < 19; k++) {
                        System.out.print(" ");
                    }
                    System.out.print("|");
                }
                System.out.println();
                System.out.print("|");
            }
            for(int z = 0; z < grid[0].length; z++) {
                for (int k = 0; k < 19; k++) {
                    System.out.print(" ");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        for (int i = 0; i < grid.length; i++) {
            System.out.print("+-------------------");
        }
        System.out.println("+");

    }


    /**
     * Return a List of the adjacent BoardCells given a BoardCell
     * @param b
     * @return
     */
    public List<BoardCell> adjacentCells(@NotNull BoardCell b) {
        int r_temp, c_temp;
        List<BoardCell> adj = new ArrayList<>();
        r_temp = b.getRow() - 1;
        if (r_temp < 0) {
            r_temp = 0;
        }
        c_temp = b.getCol() - 1;
        if (c_temp < 0) {
            c_temp = 0;
        }
        for (int i = r_temp; i <= b.getRow() + 1 && i < SIZE; i++) {
            for (int j = c_temp; j <= b.getCol() + 1 && j < SIZE; j++) {
                if (!(i == b.getRow() && j == b.getCol())) {
                    adj.add(grid[i][j]);
                }
            }
        }
        return adj;
    }

    /**
     * Creates an gridList of all cells with no workers on
     * @return
     */
    public List<BoardCell> freeCells() {
        List<BoardCell> b = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].getWorker() == null) {
                    b.add(grid[i][j]);
                }
            }
        }
        return b;
    }

    /**
     * Return a List with all the BoardCells
     * @return
     */
    public List<BoardCell> allCells() {
        List<BoardCell> b = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                b.add(grid[i][j]);
            }
        }
        return b;
    }



}
