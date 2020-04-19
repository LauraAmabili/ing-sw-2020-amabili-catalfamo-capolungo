package it.polimi.ingsw.Model;

import it.polimi.ingsw.View.View;

import java.util.ArrayList;
import java.util.List;

public class Observable {


    private List<Observer> observers = new ArrayList<>();


    public void AddObserver(Observer o){
        this.observers.add(o);

    }

    void notifyObservers() {
    }

    void notifyObservers(Object obj){

    }

    void setChanged() {
    }
}
