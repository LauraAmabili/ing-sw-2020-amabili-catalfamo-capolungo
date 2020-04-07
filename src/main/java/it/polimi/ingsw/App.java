package it.polimi.ingsw;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Turn;

import java.io.IOException;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) throws IOException {

        Game game = new Game();
        game.addNickname("SuperRexo");
        game.addNickname("NotATeen");
        game.initialiseMatch();
        Turn turn = new Turn();
        game.setCurrentTurn(turn);
        turn.setActivePlayers(game.getOnlinePlayers());
        turn.getActivePlayers().get(0).getWorkerRef()[0].setCurCell(turn.getActivePlayers().get(0).getWorkerRef()[0].getBoard().getGrid()[1][1]);
        turn.getActivePlayers().get(0).getWorkerRef()[0].getBoard().getGrid()[1][1].setWorker(turn.getActivePlayers().get(0).getWorkerRef()[0]);
        turn.getActivePlayers().get(0).getWorkerRef()[0].getBoard().getGrid()[1][1].setLevel(3);
        turn.getActivePlayers().get(0).getWorkerRef()[1].setCurCell(turn.getActivePlayers().get(0).getWorkerRef()[1].getBoard().getGrid()[4][4]);
        turn.getActivePlayers().get(0).getWorkerRef()[1].getBoard().getGrid()[4][4].setWorker(turn.getActivePlayers().get(0).getWorkerRef()[1]);
        turn.getActivePlayers().get(0).getWorkerRef()[1].getBoard().getGrid()[4][4].setLevel(3);
        turn.getActivePlayers().get(0).getWorkerRef()[1].getBoard().getGrid()[4][4].setDome(true);
        turn.getActivePlayers().get(1).getWorkerRef()[0].setCurCell(turn.getActivePlayers().get(0).getWorkerRef()[0].getBoard().getGrid()[4][1]);
        turn.getActivePlayers().get(1).getWorkerRef()[0].getBoard().getGrid()[4][1].setWorker(turn.getActivePlayers().get(1).getWorkerRef()[0]);
        turn.getActivePlayers().get(0).getWorkerRef()[1].setCurCell(turn.getActivePlayers().get(0).getWorkerRef()[0].getBoard().getGrid()[1][4]);
        turn.getActivePlayers().get(1).getWorkerRef()[1].getBoard().getGrid()[1][4].setWorker(turn.getActivePlayers().get(1).getWorkerRef()[1]);
        turn.getActivePlayers().get(1).getWorkerRef()[1].getBoard().printGrid();
    }
}
