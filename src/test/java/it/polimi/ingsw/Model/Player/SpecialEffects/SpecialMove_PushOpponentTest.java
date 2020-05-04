package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialMove_PushOpponent;
import it.polimi.ingsw.Model.PlayerFSA.AddNickname;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMove_PushOpponentTest {

    Game game = new Game();
    private List<Worker> mockWorkers1 = new ArrayList<Worker>();
    private List<Worker> mockWorkers2 = new ArrayList<Worker>();


    @Test
    public void testSpecialMove_PushOpponent() {
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        mockWorkers1.add(worker1);
        Board board = new Board();
        BoardCell cell = new BoardCell(0,0);
        mockWorkers1.add(worker1);
        mockWorkers2.add(worker2);
        Player mockPlayer1 = new Player("Rexo", mockWorkers1, board);
        mockPlayer1.getBoard().getGrid()[0][0].setWorker(worker1);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker2.setCurCell(board.getGrid()[0][1]);
        worker1.setPlayerWorker(mockPlayer1);
        mockWorkers1.add(worker1);
        mockPlayer1.setPlayerState(new AddNickname(mockPlayer1, game));
        PlayerInterface player1 = new SpecialMove_PushOpponent(mockPlayer1);
        Player mockPlayer2 = new Player("mockName", mockWorkers2, board);
        mockPlayer2.setPlayerState(new AddNickname(mockPlayer2, game));
        mockWorkers1.add(worker2);
        worker2.setPlayerWorker(mockPlayer2);
        mockPlayer2.getBoard().getGrid()[0][1].setWorker(worker2);
        player1.move(0, 1, worker1);
        assertEquals(worker1.getCurCell(), mockPlayer1.getBoard().getGrid()[0][1]);
        assertEquals(worker2.getCurCell(), mockPlayer2.getBoard().getGrid()[0][2]);

    }


}