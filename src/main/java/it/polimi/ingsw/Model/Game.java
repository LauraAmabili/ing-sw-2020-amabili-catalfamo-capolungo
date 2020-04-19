package it.polimi.ingsw.Model;

import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.Player.*;
import org.jetbrains.annotations.NotNull;

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

  Map<String, List<PlayerInterface>> map = new HashMap<>();

  public Game() {
    nickNames = new ArrayList<>();
    onlinePlayers = new ArrayList<>();
  }

  public void setMap() {
    List <PlayerInterface> ApolloList = new ArrayList<>();
    List <PlayerInterface> ArtemisList = new ArrayList<>();
    List <PlayerInterface> AthenaList = new ArrayList<>();
    List <PlayerInterface> AtlasList = new ArrayList<>();
    List <PlayerInterface> DemeterList = new ArrayList<>();
    List <PlayerInterface> HephaestusList = new ArrayList<>();
    List <PlayerInterface> MinotaurList = new ArrayList<>();
    List <PlayerInterface> PanList = new ArrayList<>();
    List <PlayerInterface> PrometheusList = new ArrayList<>();
    for (PlayerInterface onlinePlayer : onlinePlayers) {
      map.put("Apollo", ApolloList);
      map.get("Apollo").add(new SpecialMove_SwapWorkers(onlinePlayer));
      map.put("Artemis", ArtemisList);
      map.get("Artemis").add(new SpecialMove_MoveTwice(onlinePlayer));
      map.put("Athena", AthenaList);
      map.get("Athena").add(new SpecialOpponentTurn_LockMoveUp(onlinePlayer));
      map.put("Atlas", AtlasList);
      map.get("Atlas").add(new SpecialBuild_DomeAnyLevel(onlinePlayer));
      map.put("Demeter", DemeterList);
      map.get("Demeter").add(new SpecialBuild_BuildTwiceDifferent(onlinePlayer));
      map.put("Hephaestus", HephaestusList);
      map.get("Hephaestus").add(new SpecialBuild_BuildTwiceSame(onlinePlayer));
      map.put("Minotaur", MinotaurList);
      map.get("Minotaur").add(new SpecialMove_PushOpponent(onlinePlayer));
      map.put("Pan", PanList);
      map.get("Pan").add(new SpecialWin_MoveDown(onlinePlayer));
      map.put("Prometheus", PrometheusList);
      map.get("Prometheus").add(new SpecialWin_MoveDown(onlinePlayer)); //TODO: Creare classe per movimento e costruzione di prometeo
    }

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
   *
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


  public void DecoratePlayer (Player player, God god) {

    player.setActiveCard(god);

    if(god.getGodName().equals("Apollo")){
      PlayerInterface pApollo = new SpecialMove_SwapWorkers(player);
    }


  }

  public void win(@NotNull Worker worker) {
    if(worker.getPlayerWorker().checkWin(worker) || onlinePlayers.size() == 1) {
      System.out.println(worker.getPlayerWorker().getNickname() + "wins");
    }
  }

  public String getPlayerNickname(int num){
    return getOnlinePlayers().get(num).getNickname();
  }

}

