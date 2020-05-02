package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.ArrayList;
import java.util.List;

public class Observable {


    private List<ObserverModel> observerModels = new ArrayList<>();


    public void AddObserver(ObserverModel o){
        this.observerModels.add(o);
    }

    void notifyPlayerAdded(Object obj){
        for(ObserverModel o : observerModels){
            o.updatePlayerAdded(obj);
        }
    }
    void notifyGameIsRead(){
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }
    void notifyGodSetted(PlayerInterface player, String godName){
        for(ObserverModel o : observerModels){
            o.updateGodSetted(player, godName);
        }
    }
    void notifyPlayerDecorated(PlayerInterface playerDecorated){
        for(ObserverModel o : observerModels){
            o.updatePlayerDecorated(playerDecorated);
        }
    }
    void notifyWorkerSettled(Board board){
        for(ObserverModel o : observerModels){
            o.updateBoard(board);
        }
    }
    void notifyCards(List gods, String name){
        for(ObserverModel o : observerModels){
            o.updateTimeToChoose(gods, name);
        }
    }
    void notifyObservers(Object something, Object obj){
        for(ObserverModel o : observerModels){
            o.update(something,obj);
        }
    }
    void notifyGodAdded(List<God> gods, boolean cardChosen){
        for(ObserverModel o : observerModels){
            o.updateGodAdded(gods, cardChosen);
        }
    }
    void notifyWinner(PlayerInterface player){
        for(ObserverModel o : observerModels){
            o.updateWinners(player);
        }
    }
    void notifyBoardUpdate(Board board){
        for(ObserverModel o : observerModels){
            o.updateBoard(board);
        }
    }
    void notifyCanMove(Object obj){
        for(ObserverModel o : observerModels){
            o.updateDecideWorker(obj);
        }
    }
    void notifyCanMoveThisWorker(int worker){
        for(ObserverModel o : observerModels){
            o.updateMoving(worker);
        }
    }
    void notifyTryNewCoordinatesMove(int Worker){
        for(ObserverModel o : observerModels){
            o.updateMoving(Worker);
        }
    }
    void notifyCanBuild(Object object, int worker){
        for(ObserverModel o : observerModels){
            o.updateBuilding(object, worker);
        }
    }
    void notifyTryNewCoordinatesBuild(Object obj, int worker){
        for(ObserverModel o : observerModels){
            o.updateBuilding(obj, worker);
        }
    }
    void notifyCellAlreadyOccupied(int i){
        for(ObserverModel o : observerModels){
            o.updateSetWorker(i);
        }

    }
    void notifyGodNotCorrect(List chosenGods){
        for(ObserverModel o : observerModels){
            o.updateCardNotPresent(chosenGods);
        }
    }
    void notifyGodNotAdded(){
        for(ObserverModel o : observerModels){
            o.updateGodNotAdded();
        }

    }
    void notifyChoose(boolean chosenGods, List names){
        for(ObserverModel o : observerModels){
            o.updateChoose(chosenGods, names);
        }

    }
    void notifyNicknameNotValid(){
        for(ObserverModel o : observerModels){
            o.updateNicknameNotValid();
        }

    }
    void notifyPlayerHasLost(String playerName){
        for(ObserverModel o : observerModels){
            o.updatePlayerHasLost(playerName);
        }
    }

}
