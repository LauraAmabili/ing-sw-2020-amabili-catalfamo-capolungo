package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Turn {
  private int TurnId;
  private PlayerInterface currentPlayer;
  private List<PlayerInterface> activePlayers;

  public Turn(List<PlayerInterface> list) {
    Random random = new Random();
    activePlayers = list;
    setCurrentPlayer(activePlayers.get(random.nextInt(activePlayers.size())));
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
