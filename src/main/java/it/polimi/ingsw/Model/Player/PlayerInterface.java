package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PlayerInterface {

  String nickname = null;

  Worker[] workerRef = new Worker[0];

  God activeCard = null;

  void setWorkerRef(Worker[] worker);

  Worker[] getWorkerRef();

  void setActiveCard(God ActiveCard);

  God getActiveCard();

  void setNickname(String nickname);

  String getNickname();



  boolean move(int row, int col, Worker worker);

  boolean build(int row, int col, @NotNull Worker worker);

  boolean checkWin(Worker worker);

  List<BoardCell> availableCellsToMove(@NotNull Worker worker);

  public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect);


  List<BoardCell> availableCellsToBuild(@NotNull Worker worker);

}
