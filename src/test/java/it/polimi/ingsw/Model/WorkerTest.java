package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {

    Worker w = new Worker(1);

    PlayerInterface playerWorker=null;
    BoardCell oldCell=null;
    BoardCell curCell=null;
    String color="mockColor";

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getColor() {
        w.getColor();
    }

    @Test
    void setColor() {
    }

    @Test
    void getIdWorker() {
        w.setIdWorker(w.getIdWorker());
    }

    @Test
    void getPlayerWorker() {
    }

    @Test
    void setIdWorker() {
    }

    @Test
    void setPlayerWorker() {
    }

    @Test
    void getOldCell() {
    }

    @Test
    void setOldCell() {
    }

    @Test
    void getCurCell() {
    }

    @Test
    void setCurCell() {
    }
}