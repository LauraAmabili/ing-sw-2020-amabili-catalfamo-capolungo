package it.polimi.ingsw.View;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CLIViewTest {


    CLIView virtualView = new CLIView();

    @Test
    void nicknameCurrentPlayerUpdated () {

        virtualView.updatePlayerAdded("Notateen");
        Assert.assertEquals(virtualView.getCurrentPlayer().getNickname(), "Notateen");

    }

    @Test
    void updateGameisReady() {

    }

    @Test
    void updateTimeToSetCard() {
    }

    @Test
    void updateGodSetted() {
    }

    @Test
    void updatePlayerDecorated() {
    }

    @Test
    void updateBoard() {
    }

    @Test
    void updateTimeToChoose() {
    }

    @Test
    void updateGodAdded() {
    }

    @Test
    void updateGodNotAdded() {
    }

    @Test
    void updateTimeToPlaceWorker() {
    }

    @Test
    void update() {
    }

    @Test
    void updateWinners() {
    }

    @Test
    void updateDecideWorker() {
    }

    @Test
    void updateMoving() {
    }

    @Test
    void updateBuilding() {
    }

    @Test
    void updateSetWorker() {
    }

    @Test
    void updateCardNotPresent() {
    }

    @Test
    void updateChoose() {
    }

    @Test
    void updateNicknameNotValid() {
    }

    @Test
    void updatePlayerHasLost() {
    }

    @Test
    void menageInput() {
    }

    @Test
    void printComandi() {
    }

    @Test
    void startingGame() {
    }

    @Test
    void insertNickname() {
    }

    @Test
    void chooseCards() {
    }

    @Test
    void chooseYourGod() {
    }

    @Test
    void start() {
    }

    @Test
    void setWorkers() {
    }

    @Test
    void chooseWorker() {
    }

    @Test
    void startMoving() {
    }

    @Test
    void moving() {
    }

    @Test
    void building() {
    }

    @Test
    void chooseCard() {
    }
}