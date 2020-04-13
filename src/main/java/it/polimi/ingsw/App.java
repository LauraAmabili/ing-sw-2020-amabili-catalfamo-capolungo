package it.polimi.ingsw;

import it.polimi.ingsw.Decorator.*;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.God;


import java.util.*;

public class App 
{
    public static void main( String[] args ) {

        Scanner input = new Scanner (System.in);
        boolean a;
        int k;
        int row;
        int col;
        God apollo = new God();
        God atlas = new God();

        apollo.setGodName("apollo");
        atlas.setGodName("atlas");
        Game game = new Game();
        game.addNickname("SuperRexo");
        game.addNickname("NotATeen");
        game.initialiseMatch();


        //Map<String, PlayerInterface> god = new HashMap<>();
        //god.put("apollo", new SpecialMove1(new BaseBuild(new BaseWin(game.getOnlinePlayers().get(0)))));
        //god.put("atlas", new SpecialBuild_DomeAnyLevel(new BaseMove(new BaseWin(game.getOnlinePlayers().get(1)))));


        Turn turn = new Turn(game.getIterator());
        game.setCurrentTurn(turn);
        Random rand = new Random();
        int num = rand.nextInt(game.getOnlinePlayers().size());
        game.getCurrentTurn().setCurrentPlayer(game.getOnlinePlayers().get(num));
        int j = num;

        for(int i = 0; i < game.getOnlinePlayers().size();i++, j++){
            System.out.println("Scegli divinità per : " + game.getCurrentTurn().getCurrentPlayer().getNickname());
            String NomeGod = input.nextLine();
            game.setGod(NomeGod, game.getCurrentTurn().getCurrentPlayer());
            game.Decorator();
            PlayerInterface player = game.getDecorations().get(game.getCurrentTurn().getCurrentPlayer().getActiveCard().getGodName());
            player.setNickname(game.getOnlinePlayers().get(j).getNickname());
            player.setWorkerRef(game.getOnlinePlayers().get(j).getWorkerRef());
            player.setActiveCard(game.getOnlinePlayers().get(j).getActiveCard());
            game.getOnlinePlayers().set(j, player);
            game.getCurrentTurn().nextTurn();
            if(num == game.getOnlinePlayers().size() - 1) {j = -1;}
        }

        //game.getOnlinePlayers().get(0).setActiveCard(apollo);
        //game.getOnlinePlayers().get(1).setActiveCard(atlas);

        //game.getOnlinePlayers().set(0, Decorations.get(game.getOnlinePlayers().get(0).getActiveCard().getGodName()));
        //game.getOnlinePlayers().set(1, Decorations.get(game.getOnlinePlayers().get(1).getActiveCard().getGodName()));
        game.getBoard().printGrid();
        for (int i = 0; i < game.getOnlinePlayers().size(); i++) {
            System.out.println(game.getCurrentTurn().getCurrentPlayer().getNickname()+"'s turn:");
            for (j = 0; j < game.getOnlinePlayers().get(i).getWorkerRef().length; j++) {
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
