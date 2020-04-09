package it.polimi.ingsw.Decorator;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PlayerInterface {

  void decorate();

  boolean move(int row, int col, Worker worker);

  boolean build(int row, int col, @NotNull Worker worker);

  boolean checkWin(Worker worker);

  List<BoardCell> availableCellsToMove(@NotNull Worker worker);

  List<BoardCell> availableCellsToBuild(@NotNull Worker worker);

  boolean buildTwice();

  boolean buildTwice(int row, int col);

}
