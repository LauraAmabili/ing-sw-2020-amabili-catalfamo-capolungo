package it.polimi.ingsw.Model;


import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;

public class Model extends Observable {

  public Model() {
  }
  private Game game = new Game();

  //Set Nickname 1
  public void setNickname(String nickname){
    game.addNickname(nickname);
    //notifyStartMatch();
  }
  //Initialise match + create Turn
  public void initialiseMatch(){
    game.initialiseMatch();

  }
  public void setTurn(){
    Turn turn = new Turn(game.getOnlinePlayers());
    game.setCurrentTurn(turn);
    //game.getCurrentTurn().setCurrentPlayer();
  }

  public Turn getTurn(){
    return game.getCurrentTurn();
  }




  public List<PlayerInterface> getOnlinePlayers(){
    return game.getOnlinePlayers();
  }

  public void setActiveCard(String name){
    God god = new God();
    god.setGodName(name);


  }
}
