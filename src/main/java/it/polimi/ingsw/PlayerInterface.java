package it.polimi.ingsw;

public interface PlayerInterface {

  public void move(int row, int col, Worker worker);

  public void build(Worker worker, int row, int col);

  public boolean checkWin(Worker worker);

}
