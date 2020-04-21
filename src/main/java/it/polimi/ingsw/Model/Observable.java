package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.View.View;

import java.util.ArrayList;
import java.util.List;

public class Observable {


    private List<Observer> observers = new ArrayList<>();


    public void AddObserver(Observer o){
        this.observers.add(o);
    }

    void notifyPlayerAdded(Object obj){
        for(Observer o : observers){
            o.updatePlayerAdded(obj);
        }
    }
    void notifyGameIsRead(){
        for(Observer o : observers){
            o.updateGameisReady();
        }
    }
    void notifyGodSetted(PlayerInterface player, String godName){
        for(Observer o : observers){
            o.updateGodSetted(player, godName);
        }
    }

    void notifyPlayerDecorated(PlayerInterface playerDecorated){
        for(Observer o : observers){
            o.updatePlayerDecorated(playerDecorated);
        }
    }

    void notifyWorkerSetted(Board board){
        for(Observer o : observers){
            o.updateBoard(board);
        }
    }
    void notifychooseCards(List gods){
        for(Observer o : observers){
            o.updateTimeToChoose(gods);
        }
    }
    /*
    void notifyObservers(Object obj) {
        for(Observer o : observers){
            o.update(obj);
        }
    }
*/
    void notifyObservers(Object something, Object obj){
        for(Observer o : observers){
            o.update(something,obj);
        }
    }



}
