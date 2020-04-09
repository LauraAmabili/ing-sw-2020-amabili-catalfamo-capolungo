package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerDecorator implements PlayerInterface {

  public void decorate() {
    this.player.decorate();
  }

  // attributes
  protected PlayerInterface player;

  // constructor
  public PlayerDecorator(PlayerInterface p){
    this.player = p;
  }


  public boolean move(int row, int col, Worker worker){
    return false;
  };

  public boolean build(int row, int col, @NotNull Worker worker){
    return false;
  };

  public boolean checkWin(Worker worker){
    return false;
  };

  public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
    return null;
  };

  public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){
    return null;
  }

  public boolean buildTwice(){return false;};

  public boolean buildTwice(int row, int col){return false;};




  ;
}
