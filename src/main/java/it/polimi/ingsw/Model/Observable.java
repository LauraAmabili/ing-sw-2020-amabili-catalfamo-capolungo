package it.polimi.ingsw.Model;

import java.util.ArrayList;
import java.util.List;

public class Observable {

  private String notify;
  private List<Observer> observers = new ArrayList<>();

  public void addObserver(Observer name) {
    //observer.update(this.matchScore);
    //this.observers.add(channel);
    this.observers.add(name);
  }

  public void notifyNickname() {
    for (Observer observer : this.observers) {
      observer.updateNickname();
    }
    notifyStartMatch();
  }

  public void notifyStartMatch(){
    for (Observer observer : this.observers) {
      observer.updateGame();
    } //Game creato
    notifyTurnCreated();
  }

  public void notifyTurnCreated(){
    for(Observer observer : this.observers){
      observer.updateTurnCreated();
    }
  }
  public void notifyChooseGod(){
    for (Observer observer : this.observers) {
      observer.updateChooseGod();
    }
    notifyGodOk();
  }



  public void notifyGodOk(){
    for (Observer observer : this.observers) {
      observer.updateToDecide();
    }
  }
  public void deleteObserver(Observer name) {
  }

  public void setNotify(String notify) {
    this.notify = notify;
  }


}
