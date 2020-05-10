package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Observable {


    private List<ObserverModel> observerModels = new ArrayList<>();


    public void AddObserver(ObserverModel o){
        this.observerModels.add(o);
    }
    void notifyPlayerAdded(String obj) throws IOException {
        for(ObserverModel o : observerModels){
            o.updatePlayerAdded(obj);
        }
    }
    void notifyGameIsRead() throws IOException, InterruptedException {
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }
    void notifyGodSet(String player, String godName) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateGodSet(player, godName);
        }
    }
    void notifyObservers(Object something, Object obj){
        for(ObserverModel o : observerModels){
            o.update(something,obj);
        }
    }
    void notifyGodAdded(List<String> gods, boolean cardChosen, String challengerName) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateGodAdded(gods, cardChosen, challengerName);
        }
    }
    void notifyWinner(PlayerInterface player){
        for(ObserverModel o : observerModels){
            o.updateWinners(player);
        }
    }
    void notifyBoardUpdate(Board board) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateBoard(board);
        }
    }
    void notifyCanMove(String nickname) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateDecideWorker(nickname);
        }
    }
    void notifyCanMoveThisWorker(int worker) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateMoving(worker);
        }
    }
    void notifyTryNewCoordinatesMove(int Worker) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateMoving(Worker);
        }
    }
    void notifyTryNewCoordinatesBuild(int worker) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateBuilding(worker);
        }
    }
    void notifyCellAlreadyOccupied(int i, String currentPlayer) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateSetWorker(i, currentPlayer);
        }

    }
    void notifyGodNotCorrect(String nickname, List<String> chosenGods) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateCardNotPresent(nickname, chosenGods);
        }
    }
    void notifyGodNotAdded(String challengerName) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateGodNotAdded(challengerName);
        }

    }
    void notifyChoose(boolean chosenGods, List names, String ChallengerName) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateChoose(chosenGods, names, ChallengerName);
        }

    }
    void notifyNicknameNotValid(String nickname) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateNicknameNotValid(nickname);
        }

    }
    void notifyPlayerHasLost(String playerName) throws IOException {
        for(ObserverModel o : observerModels){
            o.updatePlayerHasLost(playerName);
        }
    }
    void notifyTimeToSetCard(List chosenGods, String currentPlayerName) throws IOException {
        for (ObserverModel o : observerModels) {
            o.updateTimeToSetCard(chosenGods, currentPlayerName);
        }
    }
    void notifyTimeToPlaceWorker(String currentPlayerName) throws IOException {
        for (ObserverModel o : observerModels) {
            o.updateTimeToPlaceWorker(currentPlayerName);
        }
    }
    void notifyCards(List gods, String name) throws IOException {
        for (ObserverModel o : observerModels){
            o.updateTimeToChoose(gods, name);
        }
    }
    /*
    void notifyGodAlreadyChosen(List <String> chosenGods, String name){
        for (ObserverModel o : observerModels){
            o.updateGodAlreadyChosen(chosenGods, name);
        }
    }


     */
    void notifyWin(PlayerInterface p) {
        for (ObserverModel o : observerModels){
            o.updateWinners(p);
        }
    }
    void notifyWorkerSelected(int worker) throws IOException {
        for (ObserverModel o : observerModels){
            o.updateWorkerSelected(worker);
        }
    }
    void notifyNoCoordinatesValid(int worker) throws IOException {
        for (ObserverModel o : observerModels){
            o.updateNoCoordinatesValid(worker);
        }
    }
    void notifyTimeToBuild(int worker) throws IOException {
        for (ObserverModel o : observerModels){
            o.updateTimeToBuild(worker);
        }
    }
    public void notifyStartingGame() throws IOException, InterruptedException {
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }

    public void notifyWorkerBoardUpdate(int row, int col, int worker) throws IOException {
        for(ObserverModel o : observerModels){
            o.updateBoardAddedWorker(row, col, worker);
        }
    }

}
