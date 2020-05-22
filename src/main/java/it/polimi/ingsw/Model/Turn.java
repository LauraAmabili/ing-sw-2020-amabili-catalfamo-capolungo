package it.polimi.ingsw.Model;


import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.*;

public class Turn {
    private int TurnId;
    private PlayerInterface currentPlayer;
    private final List<PlayerInterface> activePlayers;

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


    /**
     * Switch the Player and goes on with the turn
     */
    public synchronized void nextTurn(Game game) {
        int index = activePlayers.size() - 1;
        int i = activePlayers.indexOf(currentPlayer);
        if(index == i) {
            currentPlayer = activePlayers.get(0);
        } else {
            currentPlayer = activePlayers.get(activePlayers.indexOf(currentPlayer) + 1);
        }
        TurnId++;
        i = getActivePlayers().indexOf(currentPlayer);
        game.getStateList().get(i).next();
    }

    /**
     * Check if the current player can move at least a worker
     * @param worker
     * @return
     */
    public boolean checkLockWorker(Worker worker) {
        return getCurrentPlayer().availableCellsToMove(worker).size() == 0;
    }

    public boolean checkLockPlayer() {
        return currentPlayer.availableCellsToMove(currentPlayer.getWorkerRef().get(0)).size() == 0 && currentPlayer.availableCellsToMove(currentPlayer.getWorkerRef().get(1)).size() == 0;
    }

}
