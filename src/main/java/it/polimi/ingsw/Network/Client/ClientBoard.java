package it.polimi.ingsw.Network.Client;

public class ClientBoard {


    private int[][] board = new int[5][5];

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void print(int nr, int nc)
    {
        int i, j;
        for(i=0; i<nr; i++)
        {
            for (j=0; j<nc; j++)
                System.out.print(board[i][j] + "  ");
            System.out.println();
        }
    }

}
