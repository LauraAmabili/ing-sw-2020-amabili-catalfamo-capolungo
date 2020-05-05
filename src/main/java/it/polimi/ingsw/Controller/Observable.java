package it.polimi.ingsw.Controller;

import java.util.ArrayList;
import java.util.List;

public class Observable {



    private List<Observer> observerController = new ArrayList<>();


    public void AddObserver(Observer o){
        this.observerController.add(o);
    }


    public void notifyInitialiseMatch(int number){
        for(Observer o : observerController){
            o.updateInitialiseMatch(number);

        }
    }
    public void notifyAddingNickname(String in ){
        for(Observer o : observerController){
            o.updateNickname(in);
        }
    }
    public void notifyChoosingCards(){
        for(Observer o : observerController){
            o.updateChoosingCards();
        }

    }

    public void notifyGodNameChosen(String godName){
        for(Observer o : observerController){
            o.updateSetGodName(godName);
        }
    }

    public void notifyAddingWorker(int row, int col, int i ){
        for(Observer o : observerController){
            o.updateAddingWorker(row, col, i );
        }
    }


    public void notifyStartMoving(){
        for(Observer o : observerController){
            o.updateStartMoving();
        }

    }

    public void notifyTryThisWorker(int worker){
        for(Observer o : observerController){
            o.updateTryThisWorker(worker);
        }
    }

    public void notifyMoving(int row, int col, int worker){
        for(Observer o : observerController){
            o.updateMoving(row, col, worker);
        }


    }

    public void notifyTryThisCard(String in){
        for(Observer o : observerController){
            o.updateTryThisCard(in);
        }
    }


    public void notifyBuilding(int row, int col, int i ){
        for(Observer o : observerController){
            o.updateBuilding(row, col, i );
        }

    }


}
