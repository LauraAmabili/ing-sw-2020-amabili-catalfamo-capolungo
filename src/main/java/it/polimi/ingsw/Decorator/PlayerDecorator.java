package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerDecorator implements PlayerInterface {

  // attributes
  protected PlayerInterface player;

  // constructor
  public PlayerDecorator(PlayerInterface p){
    this.player = p;
  }

  @Override
  public boolean move(int row, int col, Worker worker) {
    return false;
  }

  @Override
  public boolean build(int row, int col, Worker worker) {return false;}

  @Override
  public boolean checkWin(Worker worker)
  {
    return false;

  }

  public List<BoardCell> availableCellsToMove(@NotNull Worker worker){};

  public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){};


}
