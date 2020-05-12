package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialBuild_BuildTwiceDifferent;
import it.polimi.ingsw.Model.PlayerFSA.AddNickname;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SpecialBuild_BuildTwiceDifferentTest {


    Game game = new Game();
    ArrayList<BoardCell> adjCells = new ArrayList<>();

    private List<Worker> mockWorkers1 = new ArrayList<Worker>();



    @Before
    public void setUp() {

    }


    @Test
    public void SpecialBuild_BuildTwiceDifferentTest(){
        Worker worker1 = new Worker(1);
        mockWorkers1.add(worker1);
        BoardCell cell = new BoardCell(0,0);
        Board board = new Board();
        Player mockPlayer = new Player("Rexo", mockWorkers1, board);
        worker1.setCurCell(cell);
        worker1.setOldCell(null);
        worker1.setPlayerWorker(mockPlayer);
        mockWorkers1.add(worker1);
        PlayerInterface player = new SpecialBuild_BuildTwiceDifferent(mockPlayer);
        player.setEnableSpecialBuild(true);
        player.build(1, 0, 0, 1, worker1);
        assertEquals(1, board.getGrid()[1][0].getLevel());
        assertEquals(1, board.getGrid()[0][1].getLevel());
        player.setEnableSpecialBuild(false);
        player.build(1, 0, worker1);
        assertEquals(2, board.getGrid()[1][0].getLevel());

    }






}


