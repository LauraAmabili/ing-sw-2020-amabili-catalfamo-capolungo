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
  public void move(int row, int col, Worker worker) {

  }

  @Override
  public void build(int row, int col, Worker worker) {
  }

  @Override
  public boolean checkWin(Worker worker)
  {
    return false;

  }


}
