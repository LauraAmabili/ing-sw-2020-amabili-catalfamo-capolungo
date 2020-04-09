package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.Worker;

public interface PlayerInterface {

  public boolean move(int row, int col, Worker worker);

  public boolean build(int row, int col, Worker worker);

  public boolean checkWin(Worker worker);

}
