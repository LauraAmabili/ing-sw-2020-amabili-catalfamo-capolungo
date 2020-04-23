package it.polimi.ingsw.Model.Player;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.PlayerFSA.*;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface PlayerInterface {

  PlayerFSA addNickname = null;
  PlayerFSA initialized = null;
  PlayerFSA setCard = null;
  PlayerFSA moving = null;
  PlayerFSA building = null;
  PlayerFSA idle = null;

  PlayerFSA OldPlayerState = null;
  PlayerFSA playerState = null;

  String nickname = null;
  List<Worker> workerRef = null;
  List<God> chosenGods = null;
  God activeCard = null;
  Board board = null;

  void setWorkerRef(List<Worker> list);

  List<Worker> getWorkerRef();

  void setActiveCard(God ActiveCard);

  God getActiveCard();

  Board getBoard();

  void setBoard(Board board);

  void setNickname(String nickname);

  String getNickname();

  void setChosenGods(List<God> gods);

  List<God> getChosenGods();

  boolean isMoveUp();

  void setMoveUp(boolean moveUp);

  void setOldPlayerState(PlayerFSA playerState);

  PlayerFSA getOldPlayerState();

  List<BoardCell> availableCellsToMove(@NotNull Worker worker);

  List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect);

  boolean move(int row, int col, @NotNull Worker worker);

  List<BoardCell> availableCellsToBuild(@NotNull Worker worker);

  List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect);

  boolean build(int row, int col, @NotNull Worker worker);

  boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect);

  boolean build(int row1, int col1, @NotNull Worker worker, int row2, int col2);

  boolean checkWin(@NotNull Worker worker);

  boolean move(int row, int col, @NotNull Worker worker, boolean specialEffect, int rowBuild, int colBuild);

  PlayerFSA getAddNickname();

  PlayerFSA getInitialized();

  PlayerFSA getSetCard();

  PlayerFSA getMoving();

  PlayerFSA getBuilding();

  PlayerFSA getIdle();

  PlayerFSA getPlayerState();

  void setPlayerState(PlayerFSA playerState);

  void StateMove(int row, int col, Worker worker);

  void StateBuild(int row, int col, Worker worker);

}
