package it.polimi.ingsw.Model;

public class God {

  public enum god {
    APOLLO, ARTEMIS, ATHENA, ATLAS, DEMETER, HEPHAESTUS, MINOTAUR, PAN, PROMETHEUS
  }

  private String godName;

  public String getGodName() {
    return godName;
  }

  public void setGodName(String godName) {
    this.godName = godName;
  }
}

