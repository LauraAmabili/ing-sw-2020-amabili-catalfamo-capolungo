package it.polimi.ingsw;

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
  public void build(Worker worker, int row, int col) {
  }

  @Override
  public boolean checkWin(Worker worker) {
    return false;

  }


}
