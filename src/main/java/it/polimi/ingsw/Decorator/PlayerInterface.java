package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PlayerInterface {

  public boolean move(int row, int col, Worker worker);

  public boolean build(int row, int col, Worker worker);

  public boolean checkWin(Worker worker);

  public List<BoardCell> availableCellsToMove(@NotNull Worker worker);

  public List<BoardCell> availableCellsToBuild(@NotNull Worker worker);


  }
