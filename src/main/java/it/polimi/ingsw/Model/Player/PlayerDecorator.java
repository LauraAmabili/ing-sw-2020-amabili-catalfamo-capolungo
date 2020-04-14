package it.polimi.ingsw.Model.Player;

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
    this.player = player;
    this.nickname = player.getNickname();
    this.workerRef = player.getWorkerRef();
    this.activeCard = player.getActiveCard();
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
  public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
    return player.availableCellsToMove(worker);
  }

  @Override
  public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect){
    return player.availableCellsToMove(worker, specialEffect);
  }

  @Override
  public boolean move(int row, int col, @NotNull Worker worker){
    return player.move(row, col, worker);
  }

  @Override
  public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){
    return player.availableCellsToBuild(worker);
  }

  @Override
  public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect){
    return player.availableCellsToBuild(worker, specialEffect);
  }

  @Override
  public boolean build(int row, int col, @NotNull Worker worker){
    return player.build(row, col, worker);
  }

  @Override
  public boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect){
    return player.build(row, col, worker, specialEffect);
  }

  @Override
  public boolean build(int row1, int col1, @NotNull Worker worker, int row2, int col2){return player.build(row1, col1, worker, row2, col2);}

  @Override
  public boolean checkWin(@NotNull Worker worker){
    return player.checkWin(worker);
  }

}
