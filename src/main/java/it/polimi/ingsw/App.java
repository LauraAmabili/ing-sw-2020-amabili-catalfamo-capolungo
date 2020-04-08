package it.polimi.ingsw;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Turn;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.Worker;

import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {

        Scanner input = new Scanner (System.in);
        boolean a;
        int k;
        int row;
        int col;
        Game game = new Game();
        game.addNickname("SuperRexo");
        game.addNickname("NotATeen");
        game.initialiseMatch();
        Turn turn = new Turn(game.getOnlinePlayers());
        game.setCurrentTurn(turn);
        Random rand = new Random();
        game.getCurrentTurn().setCurrentPlayer(game.getOnlinePlayers().get(rand.nextInt(game.getOnlinePlayers().size())));
        game.getBoard().printGrid();
        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            game.getBoard().freeCells();
            for (int j = 0; j < game.getOnlinePlayers().get(i).getWorkerRef().length; j++) {
                row = input.nextInt();
                col = input.nextInt();
                if (!game.addWorker(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[j])) {
                    if(j == 0) {
                        j = -1;
                    } else {
                        j = 0;
                    }
                }
                game.getBoard().printGrid();
            }
            game.getCurrentTurn().nextTurn();
        }
        do {
            System.out.println(game.getCurrentTurn().getCurrentPlayer().getNickname()+"'s turn");
            System.out.println("Choose Worker");
            k = input.nextInt();
            System.out.println("Choose moving row");
            row = input.nextInt();
            System.out.println("Choose moving col");
            col = input.nextInt();
            System.out.println("Moving...");
            game.getCurrentTurn().getCurrentPlayer().move(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1]);
            if(!game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1])) {
                break;
            }
            game.getBoard().printGrid();
            System.out.println("Choose building row");
            row = input.nextInt();
            System.out.println("Choose building col");
            col = input.nextInt();
            System.out.println("Building...");
            game.getCurrentTurn().getCurrentPlayer().build(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1]);
            game.getBoard().printGrid();
            a = game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1]);
            game.getCurrentTurn().nextTurn();
        } while(!a);
        System.out.println(game.getCurrentTurn().getCurrentPlayer()+" Wins");
        game.getBoard().printGrid();
    }
}
