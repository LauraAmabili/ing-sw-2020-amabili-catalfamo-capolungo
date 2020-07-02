package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Observable {


    private final List<ObserverModel> observerModels = new ArrayList<>();


    public void AddObserver(ObserverModel o){
        this.observerModels.add(o);
    }

    public void RemoveObserver(ObserverModel o) {
        observerModels.remove(o);
    }

    void notifyPlayerAdded(String obj, String color)  {
        for(ObserverModel o : observerModels){
            o.updatePlayerAdded(obj, color);
        }
    }
    void notifyGameIsRead() throws InterruptedException {
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }
    void notifyGodSet(PlayerInterface player, String godName)  {
        for(ObserverModel o : observerModels){
            o.updateGodSet(player, godName);
        }
    }

    void notifyGodAdded(List<String> gods, boolean cardChosen, String challengerName)  {
        for(ObserverModel o : observerModels){
            o.updateGodAdded(gods, cardChosen, challengerName);
        }
    }
    void notifyBoardUpdate(Board board)  {
        for(ObserverModel o : observerModels){
            o.updateBoard(board);
        }
    }
    void notifyCanMove(PlayerInterface nickname)  {
        for(ObserverModel o : observerModels){
            o.updateDecideWorker(nickname);
        }
    }
    void notifyCanMoveThisWorker(int worker, String current,  List<BoardCell> available)  {
        for(ObserverModel o : observerModels){
            o.updateMoving(worker, current, available);
        }
    }

    void notifyTryNewCoordinatesBuild(int worker, String current,  List<BoardCell> available)  {
        for(ObserverModel o : observerModels){
            o.updateBuilding(worker, current,  available);
        }
    }
    void notifyCellAlreadyOccupied(int i, String currentPlayer)  {
        for(ObserverModel o : observerModels){
            o.updateSetWorker(i, currentPlayer);
        }

    }
    void notifyGodNotCorrect(String nickname, List<String> avilableGods, List<God> chosenGods)  {
        for(ObserverModel o : observerModels){
            o.updateCardNotPresent(nickname, avilableGods, chosenGods);
        }
    }
    void notifyGodNotAdded(String challengerName)  {
        for(ObserverModel o : observerModels){
            o.updateGodNotAdded(challengerName);
        }

    }
    void notifyChoose(boolean chosenGods, List names, String ChallengerName)  {
        for(ObserverModel o : observerModels){
            o.updateChoose(chosenGods, names, ChallengerName);
        }

    }
    void notifyNicknameNotValid(String nickname)  {
        for(ObserverModel o : observerModels){
            o.updateNicknameNotValid(nickname);
        }

    }
    void notifyPlayerHasLost(String playerName)  {
        for(ObserverModel o : observerModels){
            o.updatePlayerHasLost(playerName);
        }
    }
    void notifyTimeToSetCard(List chosenGods, PlayerInterface currentPlayerName)  {
        for (ObserverModel o : observerModels) {
            o.updateTimeToSetCard(chosenGods, currentPlayerName);
        }
    }
    void notifyTimeToPlaceWorker(String currentPlayerName)  {
        for (ObserverModel o : observerModels) {
            o.updateTimeToPlaceWorker(currentPlayerName);
        }
    }
    void notifyCards(String name, String color)  {
        for (ObserverModel o : observerModels){
            o.updateTimeToChoose(name, color);
        }
    }

    void notifyWin(PlayerInterface p)  {
        for (ObserverModel o : observerModels){
            o.updateWinners(p);
        }
    }
    void notifyWorkerSelected(boolean hasTwoInput,boolean effect, int worker, String current,  List<BoardCell> available)  {
        for (ObserverModel o : observerModels){
            o.updateWorkerSelected(hasTwoInput, effect, worker, current, available);
        }
    }
    void notifyNoCoordinatesValid(int worker, String current,  List<BoardCell> available)  {
        for (ObserverModel o : observerModels){
            o.updateNoCoordinatesValid(worker, current, available);
        }
    }
    void notifyTimeToBuild(int worker, String current, List<BoardCell> av)  {
        for (ObserverModel o : observerModels){
            o.updateTimeToBuild(worker, current, av);
        }
    }
    public void notifyStartingGame() throws InterruptedException {
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }

    public void notifyWorkerBoardUpdate(Board board, String current)  {
        for(ObserverModel o : observerModels){
            o.updateBoardAddedWorker(board, current);
        }
    }

    public void notifySetCard(List<String> availableGods, String currentPlayer, List<God> chosenGods) {
        for(ObserverModel o : observerModels){
            o.updateSetCard(availableGods, currentPlayer, chosenGods);
        }
    }

    public void notifyStartMoving(String currentPlayer)  {
        for(ObserverModel o : observerModels){
            o.updateStartMoving(currentPlayer);
        }
    }

    public void notifyChooseWorker(String currentPlayer)  {
        for(ObserverModel o : observerModels){
            o.updateTimeToChooseWorker(currentPlayer);
        }

    }

    public void notifyAskForEffect(String currentPlayer)  {
        for(ObserverModel o : observerModels){
            o.updateAskForEffect(currentPlayer);
        }

    }

    public void notifyTimeToMoveTwoInput(int worker, String nickname)  {
        for(ObserverModel o : observerModels){
            o.updateMoveTwoInput(nickname, worker);
        }
    }

    public void notifyAskForEffectBuild(String currentPlayer, int worker)  {
        for(ObserverModel o : observerModels){
            o.updateAskForEffectBuild(currentPlayer, worker);
        }
    }

    public void notifyTimeToBuildTwoInput(int worker, String currentPlayer)  {
        for(ObserverModel o : observerModels){
            o.updateBuildTwoInput(currentPlayer, worker);
        }
    }


    public void notifySetFirstPlayer(String nickname, List<PlayerInterface> onlinePlayers)  {
        for(ObserverModel o : observerModels){
            o.updateSetFirstPlayer(nickname, onlinePlayers);
        }

    }

    public void notifyNoCoordinatesValidBuildTwoInput(int worker, String nickname)  {
        for(ObserverModel o : observerModels){
            o.updateNoCoordinatesValidBuildTwoInput(worker, nickname);
        }
    }

    public void notifyNoCoordinatesValidMoveTwoInput(int worker, String nickname)  {
        for(ObserverModel o : observerModels){
            o.updateNoCoordinatesValidMoveTwoInput(worker, nickname);
        }
    }

    public void notifyServerRestart()  {
        for(ObserverModel o : observerModels){
            o.updateServerRestart();
        }

    }



}
