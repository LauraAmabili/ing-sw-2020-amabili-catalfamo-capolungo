package it.polimi.ingsw.Model;


import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

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

  public void addPlayers(PlayerInterface player) throws GameIsAlreadyStarted {
    if(!game.isGameStarted()) {
      game.getOnlinePlayers().add(player);
      setChanged();
      notifyObservers();
    } else {
      throw new GameIsAlreadyStarted();
    }
  }

  public void addNickname(String nickName) {
    this.game.addNickname(nickName);
    this.setChanged(); //guarda che l'ho modificato
    this.notifyObservers(state.ADDNICKNAMES.name()); //notifica tutti gli observers
  }


}
