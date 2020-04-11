package it.polimi.ingsw;

import it.polimi.ingsw.Decorator.PlayerDecorator;
import it.polimi.ingsw.Decorator.PlayerInterface;
import it.polimi.ingsw.Decorator.SpecialMove1;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Turn;
import it.polimi.ingsw.Model.Player;
import it.polimi.ingsw.Model.Worker;

import java.util.*;

public class App 
{




    public static void main(String[] args ) {




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
            System.out.println(game.getCurrentTurn().getCurrentPlayer().getNickname()+"'s turn:");
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
            System.out.println(game.getCurrentTurn().getCurrentPlayer().getNickname()+"'s turn:");
            int i = 0;
            if (game.getCurrentTurn().checkLockPlayer(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[i]) && game.getCurrentTurn().checkLockPlayer(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[i + 1])) {
                game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
                if (game.getOnlinePlayers().size() == 1) {
                    break;
                }
            }
            System.out.println("Choose Worker");
            k = input.nextInt();
            for(i = 0; i < 1; i++) {
                if(game.getCurrentTurn().checkLockPlayer(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1])) {
                    if(k == 2) {
                        k = 1;
                    } else {
                        k++;
                    }
                    System.out.println("This Worker can not move!\nThere are not available cells to move in");
                    System.out.println("Next worker has been chosen");
                }
                System.out.println("Choose moving row");
                row = input.nextInt();
                System.out.println("Choose moving col");
                col = input.nextInt();
                if(!game.getCurrentTurn().getCurrentPlayer().move(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1])) {
                    i = -1;
                    System.out.println("You can not move here!\nInsert new coordinates");
                } else {
                    System.out.println("Moving...");
                }
            }
            if(game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1])) {
                break;
            }
            game.getBoard().printGrid();
            if(game.getCurrentTurn().checkLockBuild(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1])) {
                game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
                if (game.getOnlinePlayers().size() == 1) {
                    break;
                }
            }
            for(i = 0; i < 1; i++) {
                System.out.println("Choose building row");
                row = input.nextInt();
                System.out.println("Choose building col");
                col = input.nextInt();
                if(!game.getCurrentTurn().getCurrentPlayer().build(row - 1, col - 1, game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1])) {
                    i = -1;
                    System.out.println("You can not build here!\nInsert new coordinates");
                } else {
                    System.out.println("Building...");
                }
            }
            game.getBoard().printGrid();
            a = game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[k - 1]);
            game.getCurrentTurn().nextTurn();
        } while(!a);
        if(game.getOnlinePlayers().size() == 1) {
            System.out.println(game.getOnlinePlayers().get(0).getNickname()+" Wins");
        } else {
            System.out.println(game.getCurrentTurn().getCurrentPlayer() + " Wins");
        }
        game.getBoard().printGrid();
        /*
        Il mio lavoro qui è finito!
         */
    }
}
