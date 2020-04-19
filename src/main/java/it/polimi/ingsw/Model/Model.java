package it.polimi.ingsw.Model;


import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.ArrayList;
import java.util.List;

public class Model extends Observable {

  private enum state {
    ADDNICKNAMES, INIMATCH,
  }

  List<state> states = new ArrayList<>();

  private Game game;

  public Model(Game game) {
    this.game = game;
  }

  public Game getGame() {
    return game;
  }

  /**
   * Add the Players to the Game
   * @param player
   * @throws GameIsAlreadyStarted
   */
  public void addPlayers(PlayerInterface player) throws GameIsAlreadyStarted {
    if(!game.isGameStarted()) {
      game.getOnlinePlayers().add(player);
      setChanged();
      notifyObservers();
    } else {
      throw new GameIsAlreadyStarted();
    }
  }


  /**
   * Add nickname to the Player
   * @param nickName
   */
  public void addNickname(String nickName) {
    this.game.addNickname(nickName);
    this.setChanged(); //guarda che l'ho modificato
    this.notifyObservers(state.ADDNICKNAMES.name()); //notifica tutti gli observers
  }

  public void initialiseMatch(){
    this.game.initialiseMatch();
    this.notifyObservers(state.INIMATCH.name());
    
  }

  public void decoratePlayer(PlayerInterface player){
    String methodname = player.getActiveCard().getGodName();
    game.decoratePlayer(methodname, player);

  }

}
