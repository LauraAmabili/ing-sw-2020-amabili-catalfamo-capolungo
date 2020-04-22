package it.polimi.ingsw.Model;


import it.polimi.ingsw.Exeptions.CardsAlreadyChosen;
import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model extends Observable {



  private Game game;


  private int cardsChosen = 0;

  private static Model model = null;

  public static Model instance(Game game) {
    if (model == null) {
      Model model = new Model(game);
      return model;
    }
    return model;
  }

  public Model(Game game) {
    this.game = game;
  }

  public Model() {
  }

  public Game getGame() {
    return game;
  }

  public int getCardsChosen() {
    return cardsChosen;
  }

  public void setCardsChosen(int cardsChosen) {
    this.cardsChosen = cardsChosen;
  }


  /**
   * Add the Players to the Game
   *
   * @param player
   * @throws GameIsAlreadyStarted
   */
  public void addPlayers(PlayerInterface player) throws GameIsAlreadyStarted {
    if (!game.isGameStarted()) {
      game.getOnlinePlayers().add(player);
    } else {
      throw new GameIsAlreadyStarted();
    }
  }

  /**
   * Add nickname to the Player
   *
   * @param nickName
   */
  public void addNickname(String nickName) {
    this.game.addNickname(nickName);
    //this.notifyPlayerAdded(state.ADDNICKNAMES.name());
    this.notifyPlayerAdded(nickName);
  }

  public void initialiseMatch() {
    this.game.initialiseMatch();
  }

  public void decoratePlayer(PlayerInterface player) {
    String methodname = player.getActiveCard().getGodName();
    PlayerInterface playerA = game.decoratePlayer(methodname, player);
    this.notifyPlayerDecorated(playerA);
  }

  public void createTurn() {
    Turn turn = new Turn(game.getOnlinePlayers());
    game.setCurrentTurn(turn);
    game.getCurrentTurn().setCurrentPlayer(game.getOnlinePlayers().get(0));
    //this.notifyObservers(state.INIMATCH.name());
    this.notifyGameIsRead();
  }

  public void setGod(String godName) {
    God god = new God(godName);
    game.getCurrentTurn().getCurrentPlayer().setActiveCard(god);
    Player player = (Player) game.getCurrentTurn().getCurrentPlayer();
    //notifyObservers(player, state.GODSETTED.name());
    this.notifyGodSetted(player, godName);
  }

  public void addWorker(Worker worker, int row, int col) {
    if (game.addWorker(row, col, worker)) {
      Board board = game.getBoard();
      this.notifyWorkerSettled(board);
    }
  }

  public void ChooseChallenger() {
    Random rand = new Random();
    game.getCurrentTurn().setCurrentPlayer(game.getOnlinePlayers().get(rand.nextInt(game.getOnlinePlayers().size())));
    //this.notifyObservers(state.CHALLENGERSETTED.name());
  }

  public void chooseCards() {
    //game.initializeGodList();
    if (cardsChosen == 0) {
      List gods = game.getGodList();
      this.notifyCards(gods);
      cardsChosen = 1;
      this.notifyCardsChosen(gods, cardsChosen);
    } else {
      cardsChosen = 2;
      this.notifyCardsChosen(game.getChosenGods(), cardsChosen);
    }
  }

  public void addChosenGods(String godName) {
    game.addChosenGods(godName);
    notifyGodAdded(game.getChosenGods());
  }

  public void move(int row, int col, Worker worker){
    game.getCurrentTurn().getCurrentPlayer().move(row, col, worker);
    notifyBoardUpdate(game.getBoard());
  }

  public void checkLockPlayer(){
    int ok = 0;
    for(int i = 0; i < 2 ; i++){
      game.getCurrentTurn().checkLockPlayer(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[i]);
      ok++;
    }
    if(ok == 2){
      game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
    }
    if (game.getOnlinePlayers().size() == 1)
      notifyWinner(game.getCurrentTurn().getCurrentPlayer());
  }
  public void build(int row, int col, Worker worker){
    game.getCurrentTurn().getCurrentPlayer().build(row, col, worker);
    notifyBoardUpdate(game.getBoard());
  }
  public void checkBuildPlayer(){
    int ok = 0;
    for(int i = 0; i < 2 ; i++){
      game.getCurrentTurn().checkLockBuild(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[i]);
      ok++;
    }
    if(ok == 2){
      game.delPlayer(game.getCurrentTurn().getCurrentPlayer());
    }
    if (game.getOnlinePlayers().size() == 1)
      notifyWinner(game.getCurrentTurn().getCurrentPlayer());
  }
  public void checkWin(){
    for(int i = 0; i < 2 ; i++) {
      game.getCurrentTurn().getCurrentPlayer().checkWin(game.getCurrentTurn().getCurrentPlayer().getWorkerRef()[i]);
    }
  }

}




