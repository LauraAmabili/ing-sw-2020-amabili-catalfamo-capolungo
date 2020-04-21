package it.polimi.ingsw.Model;


import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Exeptions.NoGod;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model extends Observable {

  private enum state {
    ADDNICKNAMES, INIMATCH, CHALLENGERSETTED, GODSETTED, ADDGODTOPLAYER, PLAYERDECORATED, MOVE, BUILD, WIN
  }

  List<state> states = new ArrayList<>();

  private Game game;
  public Model(Game game) {
    this.game = game;
  }
  public Model (){ }
  public Game getGame() {
    return game;
  }
  /**
   * Add the Players to the Game
   * @param player
   */
  public void addPlayers(PlayerInterface player) {
      game.getOnlinePlayers().add(player);
  }
  /**
   * Add nickname to the Player
   * @param nickName
   */
  public void addNickname(String nickName) {
    this.game.addNickname(nickName);
    //this.notifyPlayerAdded(state.ADDNICKNAMES.name());
    this.notifyPlayerAdded(nickName);
  }

  public void initialiseMatch(){
    this.game.initialiseMatch();
  }

  public void decoratePlayer(PlayerInterface player){
    String methodname = player.getActiveCard().getGodName();
    PlayerInterface playerA = game.decoratePlayer(methodname,player);
    this.notifyPlayerDecorated(playerA);
  }

  public void createTurn(){
    Turn turn = new Turn(game.getOnlinePlayers());
    game.setCurrentTurn(turn);
    game.getCurrentTurn().setCurrentPlayer(game.getOnlinePlayers().get(0));
    //this.notifyObservers(state.INIMATCH.name());
    this.notifyGameIsRead();
  }

  public void setGod(String godName){
    try {
      game.setGod(godName);
    } catch (NoGod e) {
      game.notifyExc();
    }
    Player player = (Player)game.getCurrentTurn().getCurrentPlayer();
    this.notifyGodSetted(player, godName);
  }

  public void addWorker(Worker worker, int row, int col){
    if(game.addWorker(row, col, worker)) {
      Board board = game.getBoard();
      this.notifyWorkerSetted(board);
    }
  }
  public void ChooseChallenger() {
    Random rand = new Random();
    game.getCurrentTurn().setCurrentPlayer(game.getOnlinePlayers().get(rand.nextInt(game.getOnlinePlayers().size())));
    //this.notifyObservers(state.CHALLENGERSETTED.name());
  }


}
