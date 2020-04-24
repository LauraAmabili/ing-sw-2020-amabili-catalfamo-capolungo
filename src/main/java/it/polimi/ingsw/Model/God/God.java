package it.polimi.ingsw.Model.God;

//To delete

public class God {

  private String godName;

  private boolean specialBuild_BuildTwiceDifferent;
  private boolean specialBuild_BuildTwiceSame;

  //Constructor

  public God(String godName, boolean specialBuild_BuildTwiceDifferent) {
    this.godName = godName;
    this.specialBuild_BuildTwiceDifferent = specialBuild_BuildTwiceDifferent;
  }


  //Getter & Setter

  public String getGodName() {
    return godName;
  }

  public void setGodName(String godName) {
    this.godName = godName;
  }

  public boolean isSpecialBuild_BuildTwiceDifferent() {
    return specialBuild_BuildTwiceDifferent;
  }

  public void setSpecialBuild_BuildTwiceDifferent(boolean specialBuild_BuildTwiceDifferent) {
    this.specialBuild_BuildTwiceDifferent = specialBuild_BuildTwiceDifferent;
  }
}

