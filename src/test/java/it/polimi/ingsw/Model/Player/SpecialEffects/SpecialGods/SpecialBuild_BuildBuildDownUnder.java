package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SpecialBuild_BuildBuildDownUnder {

    Game game = new Game();
    private final List<Worker> mockWorkers1 = new ArrayList<>();


    @Test
    public void testSpecialBuild_BuildBuildDownUnder() {

        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        mockWorkers1.add(worker1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(board.getGrid()[0][0]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialBuild_BuildDownUnder(mockPlayer);
        player.build(1, 0, worker1);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        player.build(0, 1, worker1);
        assertEquals(1, board.getGrid()[0][1].getLevel());
        player.build(1, 0, worker1);
        assertEquals(2, board.getGrid()[1][0].getLevel());
        player.build(0, 0, worker1);
        assertEquals(1, board.getGrid()[0][0].getLevel());





    }

}