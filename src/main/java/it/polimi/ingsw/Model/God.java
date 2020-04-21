package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.HashMap;
import java.util.Map;

public class God {


  private PlayerInterface PlayerToDecorate;

  private String godName;

  public God(String godName) {
    this.godName = godName;
  }

  public String getGodName() {
    return godName;
  }

  public void setGodName(String godName) {
    this.godName = godName;
  }

  public PlayerInterface getPlayerToDecorate() {
    return PlayerToDecorate;
  }

  public void setPlayerToDecorate(PlayerInterface playerToDecorate) {
    PlayerToDecorate = playerToDecorate;
  }




}

