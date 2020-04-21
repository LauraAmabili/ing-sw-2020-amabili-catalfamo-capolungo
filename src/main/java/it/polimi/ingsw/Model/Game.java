package it.polimi.ingsw.Model;

import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.Player.*;
import org.jetbrains.annotations.NotNull;
import it.polimi.ingsw.Model.*;




import java.lang.reflect.Method;
import java.util.*;


public class Game {

  //Player
  private int id;
  private List<String> nickNames; //in game players
  private List<PlayerInterface> onlinePlayers;
  private Turn currentTurn;
  private int counterId = 1;
  private Board board;
  private boolean gameStarted;


  //God
  private enum gods {
    Apollo, Artemis, Athena, Atlas, Demeter, Hephaestus, Minotaur, Pan, Prometheus
  }

  private List<gods> godList = new ArrayList<>();
  private List<God> chosenGods = new ArrayList<>();

  Map<String, Method> metodi = new HashMap<>();
  Map<String, List<PlayerInterface>> map = new HashMap<>();

  public Game() {
    nickNames = new ArrayList<>();
    onlinePlayers = new ArrayList<>();
  }


  private void chosenGod(God god) {
    chosenGods.add(god);
  }

  public void initializeGodList() {
    godList.addAll(Arrays.asList(gods.values()));
  }

  public Board getBoard() {
    return board;
  }

  public void setBoard(Board board) {
    this.board = board;
  }

  public List<PlayerInterface> getOnlinePlayers() {
    return onlinePlayers;
  }

  public List<String> getNicknames() {
    return nickNames;
  }

  public void addNickname(String nickNames) {
    this.nickNames.add(nickNames);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isGameStarted() {
    return gameStarted;
  }

  public void setGameStarted(boolean gameStarted) {
    this.gameStarted = gameStarted;
  }

  public Turn getCurrentTurn() {
    return currentTurn;
  }

  public void setCurrentTurn(Turn currentTurn) {
    this.currentTurn = currentTurn;
  }

  /**
   * Add player in OnlinePlayer list
   * @param player
   * @throws GameIsAlreadyStarted
   */
  public void addPlayers(PlayerInterface player) throws GameIsAlreadyStarted {
    onlinePlayers.add(player);
  }

  /**
   * Delete the chosen player and all his workers
   * @param player
   */
  public void delPlayer(@NotNull PlayerInterface player){
    for(int i = 0; i < player.getWorkerRef().length; i++) {
      player.getWorkerRef()[i].getCurCell().setWorker(null);
      player.getWorkerRef()[i] = null;
    }
    onlinePlayers.remove(player);
  }

  /**
   * Add a Worker in the board for the first time
   * @param row
   * @param col
   * @param worker
   * @return
   */
  public boolean addWorker(int row, int col, Worker worker) {
    List<BoardCell> list;
    list = worker.getBoard().freeCells();
    if(list.contains(worker.getBoard().getGrid()[row][col])) {
      worker.getBoard().getGrid()[row][col].setWorker(worker);
      worker.setCurCell(worker.getBoard().getGrid()[row][col]);
      return true;
    } else {
      System.out.println("Cell is already occupied");
    }
    return false;
  }

  /**
   *Create a list of Worker to match the Player
   *Create a player
   *Create a Board
   */
  public void initialiseMatch() {
    List<Worker> list = new ArrayList<>();
    Board board = new Board();
    setBoard(board);
    for (String nickName : nickNames) {
      for (int i = 0; i < 2; i++, counterId++) {
        Worker worker = new Worker(counterId, this.board);
        list.add(worker);
      }

      Player player = new Player(nickName, list);

      for(int k = 0; k < player.getWorkerRef().length; k++) {
        player.getWorkerRef()[k].setPlayerWorker(player);
      }
      try {
        addPlayers(player);
      } catch (GameIsAlreadyStarted e) {
        e.printStackTrace();
      }
      list.clear();
    }

  }

  /**
   * Calls the right method using reflection using the name of the ActiveGod
   * @param name
   * @param player
   */
  public void decoratePlayer(String name, PlayerInterface player){

    DecoratoreReflection dec = new DecoratoreReflection();
    Method metodo = null;
    try {
     metodo = dec.getClass().getDeclaredMethod(name, PlayerInterface.class);
    }
    catch (NoSuchMethodException e) {
      //notify();
      // there is no God
      System.out.println("Player not decorated, choose one first");
      notifyExc();
      //TODO: check notify()
    }

    if (metodo != null)
      try {
        metodo.invoke(dec, player);
      }
      catch (Exception ecc) {
        System.out.println("Exception ecc, non invoca il metodo ");
        //TODO: check notify()
        notifyExc();
      }

  }

  public void notifyExc(){
    Model model = new Model();
    model.notifyObservers("Exception");
  }
  /**
   * Check if the Worker won
   * @param worker
   */
  public void win(@NotNull Worker worker) {
    if(worker.getPlayerWorker().checkWin(worker) || onlinePlayers.size() == 1) {
      System.out.println(worker.getPlayerWorker().getNickname() + "wins");
    }
  }

  public String getPlayerNickname(int num){
    return getOnlinePlayers().get(num).getNickname();
  }

}

