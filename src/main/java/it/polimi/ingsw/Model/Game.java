package it.polimi.ingsw.Model;

import it.polimi.ingsw.Decorator.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class Game {

  //Player
  private int id;
  private List<String> nickNames; //in game players
  private List<PlayerInterface> onlinePlayers;
  private ListIterator<PlayerInterface> iterator;
  //private Player challenger;
  private Turn currentTurn;
  private int counterId = 1;
  private Board board;

  //God
  private List<God> godChallengerList;

  private Map<String, PlayerInterface> Decorations = new HashMap<>();


  public void Decorator () {
    Decorations.put("apollo", new SpecialMove1(new BaseBuild(new BaseWin(new Player()))));
    Decorations.put("atlas", new SpecialBuild_DomeAnyLevel(new BaseMove(new BaseWin(new Player()))));
    System.out.println(Decorations);
  }

  public Game() {
    nickNames = new ArrayList<>();
    onlinePlayers = new ArrayList<>();
  }
  public ListIterator<PlayerInterface> getIterator() {
    return iterator;
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

  public List<String> getNickNames() {
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

    /*public Player getChallenger() {
            return challenger;
        }

     */

  public Turn getCurrentTurn() {
    return currentTurn;
  }

  public void setCurrentTurn(Turn currentTurn) {
    this.currentTurn = currentTurn;
  }

    /*
    public void setChallenger() {
        Random rand = new Random();
        this.challenger = activePlayers.get(rand.nextInt(activePlayers.size()));
    }

    */

  public void addPlayers(PlayerInterface player){
    onlinePlayers.add(player);
  }

  public void delPlayer(@NotNull PlayerInterface player){
    for(int i = 0; i < player.getWorkerRef().length; i++) {
      player.getWorkerRef()[i].getCurCell().setWorker(null);
      player.getWorkerRef()[i] = null;
    }
    onlinePlayers.remove(player);
  }

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

  //Aggiungo alla lista di divinità con cui giocare le divinità scelte dal challenger
  public void addGod(@NotNull ArrayList<God> godChallengerList, God chosenGod){
    godChallengerList.add(chosenGod);
  }

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
      addPlayers(player);
      list.clear();
    }
    iterator = onlinePlayers.listIterator();
  }


  public void DecoratePlayer (Player player, God god) {

    player.setActiveCard(god);
    if(god.getGodName().equals("Apollo")){
      PlayerInterface pApollo = new SpecialMove1(player);
    }


  }

  public void win(@NotNull Worker worker) {
    if(worker.getPlayerWorker().checkWin(worker) || onlinePlayers.size() == 1) {
      System.out.println(worker.getPlayerWorker().getNickname() + "wins");
    }
  }

  public void setGod(String name, PlayerInterface player){
    God ciao = new God();
    ciao.setGodName(name);
    player.setActiveCard(ciao);
  }


  public Map<String, PlayerInterface> getDecorations() {
    return Decorations;
  }
}

