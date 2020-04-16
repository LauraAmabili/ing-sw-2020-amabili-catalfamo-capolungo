package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialMove_SwapWorkers;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class Game {

  //Player
  private int id;
  private List<String> nickNames; //in game players
  private List<PlayerInterface> onlinePlayers;
  //private Player challenger;
  private Turn currentTurn;
  private int counterId = 1;
  private Board board;

  //God
  private List<God> godChallengerList;

  public Game() {
    nickNames = new ArrayList<>();
    onlinePlayers = new ArrayList<>();
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

