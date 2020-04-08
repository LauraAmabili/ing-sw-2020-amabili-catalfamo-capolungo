package it.polimi.ingsw;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Turn;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {

        Scanner input = new Scanner (System.in);
        Game game = new Game();
        game.addNickname("SuperRexo");
        game.addNickname("NotATeen");
        game.initialiseMatch();
        Turn turn = new Turn();
        game.setCurrentTurn(turn);

        for(int i = 0; i < game.getOnlinePlayers().size(); i++) {
            Worker[] worker = game.getOnlinePlayers().get(i).getWorkerRef();
            game.getBoard().freeCells();
            game.getBoard().printGrid();
            for (int j = 0; j < worker.length; j++) {
                int row = input.nextInt();
                int col = input.nextInt();
                if(!game.addWorker(row -1, col - 1, worker[i])) {
                    j = 0;
                }
                game.getBoard().printGrid();
            }
        }
        game.getBoard().printGrid();
    }
}
