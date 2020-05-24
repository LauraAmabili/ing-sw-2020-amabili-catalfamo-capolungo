package it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods.SpecialBuild_BuildTwiceDifferent;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpecialBuild_BuildTwiceDowntownTest {


    Game game = new Game();
    ArrayList<BoardCell> adjCells = new ArrayList<>();

    private final List<Worker> mockWorkers1 = new ArrayList<>();


    @Test
    public void SpecialBuild_BuildTwiceDowntownTest(){
        Worker worker2 = new Worker(2);
        Worker worker1 = new Worker(1);
        mockWorkers1.add(worker1);
        Board board = new Board();
        mockWorkers1.add(worker1);
        mockWorkers1.add(worker2);
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(board.getGrid()[2][2]);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        mockPlayer = new Player("mockName", mockWorkers1, board);
        PlayerInterface player = new SpecialBuild_BuildTwiceDowntown(mockPlayer);



        player.setEnableSpecialBuild(true);
        player.build(2, 3, 3, 2, worker1);
        assertEquals(1, board.getGrid()[2][3].getLevel());
        assertEquals(1, board.getGrid()[3][2].getLevel());
        player.setEnableSpecialBuild(false);
        player.build(3, 3, worker1);
        assertEquals(1, board.getGrid()[3][3].getLevel());

    }






}


