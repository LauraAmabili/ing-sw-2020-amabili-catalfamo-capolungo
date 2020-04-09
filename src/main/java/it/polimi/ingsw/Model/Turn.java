package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Turn {
  private int TurnId;
  private Player currentPlayer;
  private List<Player> activePlayers;

  public Turn(List<Player> list) {
    activePlayers = list;
  }

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
