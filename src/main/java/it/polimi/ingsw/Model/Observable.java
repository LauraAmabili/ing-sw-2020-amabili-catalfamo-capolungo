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

    void notifyPlayerAdded(String obj){
        for(ObserverModel o : observerModels){
            o.updatePlayerAdded(obj);
        }
    }
    void notifyGameIsRead(){
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }
    void notifyGodSet(String player, String godName){
        for(ObserverModel o : observerModels){
            o.updateGodSet(player, godName);
        }
    }
    void notifyObservers(Object something, Object obj){
        for(ObserverModel o : observerModels){
            o.update(something,obj);
        }
    }
    void notifyGodAdded(List<String> gods, boolean cardChosen){
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
    void notifyCanMove(String nickname){
        for(ObserverModel o : observerModels){
            o.updateDecideWorker(nickname);
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
    void notifyTryNewCoordinatesBuild(int worker){
        for(ObserverModel o : observerModels){
            o.updateBuilding(worker);
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
    void notifyChoose(boolean chosenGods, List names, String ChallengerName){
        for(ObserverModel o : observerModels){
            o.updateChoose(chosenGods, names, ChallengerName);
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
    void notifyTimeToSetCard(String currentPlayerName) {
        for (ObserverModel o : observerModels) {
            o.updateTimeToSetCard(currentPlayerName);
        }
    }
    void notifyTimeToPlaceWorker(String currentPlayerName) {
        for (ObserverModel o : observerModels) {
            o.updateTimeToPlaceWorker(currentPlayerName);
        }
    }
    void notifyCards(List gods, String name){
        for (ObserverModel o : observerModels){
            o.updateTimeToChoose(gods, name);
        }
    }

    void notifyGodAlreadyChosen(String name){
        for (ObserverModel o : observerModels){
            o.updateGodAlreadyChosen(name);
        }
    }
    void notifyWin(PlayerInterface p) {
        for (ObserverModel o : observerModels){
            o.updateWinners(p);
        }
    }

    void notifyWorkerSelected(int worker) {
        for (ObserverModel o : observerModels){
            o.updateWorkerSelected(worker);
        }
    }

    void notifyNoCoordinatesValid(int worker) {
        for (ObserverModel o : observerModels){
            o.updateNoCoordinatesValid(worker);
        }
    }

    void notifyTimeToBuild(int worker) {
        for (ObserverModel o : observerModels){
            o.updateTimeToBuild(worker);
        }
    }

}
