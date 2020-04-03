package it.polimi.ingsw;

public interface PlayerInterface {

  public void move(int row, int col, Worker worker);

  public void build(int row, int col, Worker worker);

  public boolean checkWin(Worker worker);

}
