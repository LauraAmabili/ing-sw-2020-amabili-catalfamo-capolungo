package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Turn {
  private int TurnId;
  private Player currentPlayer;
  private List<Player> activePlayers;

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

  public List<Player> getActivePlayers() {
    return activePlayers;
  }

  public void setActivePlayers(List<Player> activePlayers) {
    this.activePlayers = activePlayers;
  }

  public void nextTurn() {
    TurnId++;
    currentPlayer = activePlayers.get(activePlayers.indexOf(currentPlayer) + 1);

  }

  public boolean checkLockBuild() {
    Worker[] workerCurPlayer = currentPlayer.getWorkerRef();
    for (int i = 0; i < workerCurPlayer.length; i++) {
      if(currentPlayer.availableCellsToBuild(workerCurPlayer[i]) != null) {
        return false;
      }
    }
    return true;
  }


  // check if the current player can move at least a worker
  public boolean checkLockPlayer() {
    Worker[] workerCurPlayer = currentPlayer.getWorkerRef();
    for (int i = 0; i < workerCurPlayer.length; i++) {
      if(currentPlayer.availableCellsToMove(workerCurPlayer[i]) != null) {
        return false;
      }
    }
    return true;
  }

}
