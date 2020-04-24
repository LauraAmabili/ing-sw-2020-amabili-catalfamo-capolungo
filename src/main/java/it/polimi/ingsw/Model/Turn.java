package it.polimi.ingsw.Model;


import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Turn {
    private int TurnId;
    private PlayerInterface currentPlayer;
    private List<PlayerInterface> activePlayers;

    public Turn(List<PlayerInterface> list) {
        activePlayers = list;
    }

    public int getTurnId() {
        return TurnId;
    }

    public void setTurnId(int turnId) {
        TurnId = turnId;
    }

    public PlayerInterface getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerInterface currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public List<PlayerInterface> getActivePlayers() {
        return activePlayers;
    }

    public void setActivePlayers(List<PlayerInterface> activePlayers) {
        this.activePlayers = activePlayers;
    }

    public void createChallenger() {
        Random random = new Random();
        currentPlayer = activePlayers.get(random.nextInt(activePlayers.size()));
        currentPlayer.setPlayerState(currentPlayer.getInitialized());
    }

    /**
     * Switch the Player and goes on with the turn
     */
    public void nextTurn() {
        int index = activePlayers.size() - 1;
        int i = activePlayers.indexOf(currentPlayer);
        if(index == i) {
            currentPlayer = activePlayers.get(0);
        } else {
            currentPlayer = activePlayers.get(activePlayers.indexOf(currentPlayer) + 1);
        }
        TurnId++;
        if(currentPlayer.getOldPlayerState() == currentPlayer.getAddNickname() || currentPlayer.getOldPlayerState() == currentPlayer.getInitialized()) {
            currentPlayer.setPlayerState(currentPlayer.getSetCard());
        } else if(currentPlayer.getOldPlayerState() == currentPlayer.getSetCard()) {
            currentPlayer.setPlayerState(currentPlayer.getPlaceWorker());
        } else if(currentPlayer.getOldPlayerState() == currentPlayer.getPlaceWorker()) {
            currentPlayer.setPlayerState(currentPlayer.getMoving());
        } else if(currentPlayer.getOldPlayerState() == currentPlayer.getMoving()) {
            currentPlayer.setPlayerState(currentPlayer.getBuilding());
        } else if(currentPlayer.getOldPlayerState() == currentPlayer.getBuilding()) {
            currentPlayer.setPlayerState(currentPlayer.getMoving());
        }
    }

    /**
     * Check if the current player can build at least somewhere
     * @param worker
     * @return
     */
    public boolean checkLockBuild(Worker worker) {
        return getCurrentPlayer().availableCellsToBuild(worker).size() == 0;
    }

    /**
     * Check if the current player can move at least a worker
     * @param worker
     * @return
     */

    public boolean checkLockPlayer(Worker worker) {
        return getCurrentPlayer().availableCellsToMove(worker).size() == 0;
    }

}
