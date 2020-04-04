package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.Worker;

public interface PlayerInterface {

  public void move(int row, int col, Worker worker);

  public void build(int row, int col, Worker worker);

  public boolean checkWin(Worker worker);

}
