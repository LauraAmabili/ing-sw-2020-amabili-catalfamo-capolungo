package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;
import java.util.ListIterator;

public class Turn {
  private int TurnId;
  private PlayerInterface currentPlayer;
  private ListIterator<PlayerInterface> activePlayers;

  public Turn(ListIterator<PlayerInterface> list) {
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

  public ListIterator<PlayerInterface> getActivePlayers() {
    return activePlayers;
  }

  public void setActivePlayers(ListIterator<PlayerInterface> activePlayers) {
    this.activePlayers = activePlayers;
  }

  public void nextTurn() {
    if(activePlayers.hasNext()) {
      currentPlayer = activePlayers.next();
    } else {
      while(activePlayers.hasPrevious()){
        currentPlayer = activePlayers.previous();
      }
    }
    TurnId++;
  }

  public boolean checkLockBuild(Worker worker) {
    return getCurrentPlayer().availableCellsToBuild(worker).size() == 0;
  }


  // check if the current player can move at least a worker
  public boolean checkLockPlayer(Worker worker) {
    return getCurrentPlayer().availableCellsToMove(worker).size() == 0;
  }

}
