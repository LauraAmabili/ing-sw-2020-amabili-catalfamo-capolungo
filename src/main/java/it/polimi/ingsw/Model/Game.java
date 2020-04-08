package it.polimi.ingsw.Model;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class Game {

  //Player
  private int id;
  private List<String> nickNames; //in game players
  private List<Player> onlinePlayers;
  //private Player challenger;
  private Turn currentTurn;
  private int counterId = 1;

  //God
  private List<God> godChallengerList;

  public Game() {
    nickNames = new ArrayList<>();
    onlinePlayers = new ArrayList<>();
  }

  public List<Player> getOnlinePlayers() {
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

  public void addPlayers(Player player){
    onlinePlayers.add(player);
  }

  public void delPlayer(@NotNull Player player){
    for(int i = 0; i < player.getWorkerRef().length; i++) {
      player.getWorkerRef()[i].getCurCell().setWorker(null);
      player.getWorkerRef()[i] = null;
    }
    onlinePlayers.remove(player);
  }

  //Aggiungo alla lista di divinità con cui giocare le divinità scelte dal challenger
  public void addGod(@NotNull ArrayList<God> godChallengerList, God chosenGod){
    godChallengerList.add(chosenGod);
  }

  public void initialiseMatch() {
    ArrayList<Worker> list = new ArrayList<>();
    Board board = new Board();
    for (String nickName : nickNames) {
      for (int i = 0; i < 2; i++, counterId++) {
        Worker worker = new Worker(counterId, board);
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

  public void win(@NotNull Worker worker) {
    if(worker.getPlayerWorker().checkWin(worker) || onlinePlayers.size() == 1) {
        System.out.println(worker.getPlayerWorker().getNickname() + "wins");
    }
  }

}
