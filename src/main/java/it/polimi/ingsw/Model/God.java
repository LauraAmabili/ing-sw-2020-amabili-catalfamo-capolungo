package it.polimi.ingsw.Model;

import it.polimi.ingsw.Decorator.*;

import java.util.HashMap;
import java.util.Map;

public class God {


  private PlayerInterface PlayerToDecorate;


  public enum gods {
    APOLLO, ARTEMIS, ATHENA, ATLAS, DEMETER, HEPHAESTUS, MINOTAUR, PAN, PROMETHEUS
  }

  private String godName;



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

