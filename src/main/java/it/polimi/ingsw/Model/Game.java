package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;


public class Game {

  //Player
  private int id;
  private ArrayList<String> nickNames = new ArrayList<>(); //in game players
  private ArrayList<Player> onlinePlayers = new ArrayList<>();
  //private Player challenger;
  private Turn currentTurn;
  private int counterId = 1;

  //God
  private ArrayList<God> godChallengerList;

  public Game() {}

  public ArrayList<Player> getOnlinePlayers() {
    return onlinePlayers;
  }

  public List<String> getNickNames() {
    return nickNames;
  }

  public void addNickNames(String nickNames) {
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

  public void addPlayers(Player player){
    onlinePlayers.add(player);
  }

  public void delPlayer(ArrayList <Player> onlinePlayers, Player player){
    for(int i = 0; i < player.getWorkerRef().length; i++) {
      player.getWorkerRef()[i].getCurCell().setWorker(null);
      player.getWorkerRef()[i] = null;
    }
    onlinePlayers.remove(player);
  }

  //Aggiungo alla lista di divinità con cui giocare le divinità scelte dal challenger
  public void addGod(ArrayList<God> godChallengerList, God chosenGod){
    godChallengerList.add(chosenGod);
  }

  public void initialiseMatch() {
    ArrayList<Worker> list = new ArrayList<>();
    Board board = new Board();
    board.initialiseGrid();
    for (String nickName : nickNames) {
      for (int i = 0; i < 2; i++, counterId++) {
        Worker worker = new Worker(counterId, board);
        list.add(worker);
      }
      Player player = new Player(nickName, list);
      for(int k = 0; k < player.getWorkerRef().length; k++) {
        player.getWorkerRef()[k].setPlayerWorker(player);
      }
      onlinePlayers.add(player);
      list.clear();
    }
  }

  public void win(Worker worker) {
    if(worker.getPlayerWorker().checkWin(worker) || onlinePlayers.size() == 1) {
        System.out.println(worker.getPlayerWorker().getNickname() + "wins");
    }
  }

}

