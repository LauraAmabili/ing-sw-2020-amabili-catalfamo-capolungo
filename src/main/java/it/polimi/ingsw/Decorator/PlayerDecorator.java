package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.Worker;

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
  public boolean build(int row, int col, Worker worker) {
    return false;
  }

  @Override
  public boolean checkWin(Worker worker)
  {
    return false;

  }


}
