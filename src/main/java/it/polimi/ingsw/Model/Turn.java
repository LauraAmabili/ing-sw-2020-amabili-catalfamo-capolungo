package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;

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

  public void nextTurn() {
    int index = activePlayers.size() - 1;
    int i = activePlayers.indexOf(currentPlayer);
    if(index == i) {
      currentPlayer = activePlayers.get(0);
    } else {
      currentPlayer = activePlayers.get(activePlayers.indexOf(currentPlayer) + 1);
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
