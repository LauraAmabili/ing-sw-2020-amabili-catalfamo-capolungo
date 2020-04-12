package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerDecorator implements PlayerInterface {

  // attributes
  protected PlayerInterface player;

  private String nickname;

  private Worker[] workerRef; // reference to the workers

  private God activeCard;

  // constructor
  public PlayerDecorator(PlayerInterface player) {
    this.nickname = player.getNickname();
    this.workerRef = player.getWorkerRef();
    this.activeCard = player.getActiveCard();
    this.player = player;
  }

  @Override
  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  @Override
  public String getNickname(){
    return nickname;
  }

  @Override
  public void setActiveCard(God activeCard) {
    this.activeCard = activeCard;
  }

  @Override
  public God getActiveCard() {
    return activeCard;
  }

  @Override
  public void setWorkerRef(Worker[] worker) {
    this.workerRef = worker;
  }

  @Override
  public Worker[] getWorkerRef() {
    return workerRef;
  }

  @Override
  public boolean move(int row, int col, Worker worker){
    return player.move(row, col, worker);
  }

  @Override
  public boolean build(int row, int col, @NotNull Worker worker){
    return player.build(row, col, worker);
  }

  @Override
  public boolean checkWin(Worker worker){
    return player.checkWin(worker);
  }

  @Override
  public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
    return player.availableCellsToMove(worker);
  }

  @Override
  public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){
    return player.availableCellsToBuild(worker);
  }

}
