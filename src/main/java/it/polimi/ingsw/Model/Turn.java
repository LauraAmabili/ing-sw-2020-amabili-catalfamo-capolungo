package it.polimi.ingsw.Model;

import java.util.ArrayList;

public class Turn {
  private int TurnId;
  private Player currentPlayer;
  private ArrayList<Player> activePlayers;

  public int getTurnId() {
    return TurnId;
  }

  public void setTurnId(int turnId) {
    TurnId = turnId;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  public void setCurrentPlayer(Player currentPlayer) {
    this.currentPlayer = currentPlayer;
  }

  public ArrayList<Player> getActivePlayers() {
    return activePlayers;
  }

  public void setActivePlayers(ArrayList<Player> activePlayers) {
    this.activePlayers = activePlayers;
  }

  public void nextTurn() {
    TurnId++;
    currentPlayer = activePlayers.get(activePlayers.indexOf(currentPlayer) + 1);

  }

  // check if the current player can move at least a worker
  public boolean checkLockPlayer() {
    Worker[] workerCurPlayer = currentPlayer.getWorkerRef();
    for (int i = 0, j = 0; i < workerCurPlayer.length; i++) {
      if(currentPlayer.availableCellsToMove(workerCurPlayer[i]) == null) {
        j++;
      }
      if(j == workerCurPlayer.length) {
        return true;
      }
    }
    return false;
  }

}
