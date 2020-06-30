package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.List;

public interface ObserverModel {

    //public void update(Object obj);
    void update(Object o, Object obj);

    void updatePlayerAdded(String obj, String color) throws IOException;

    void updateGameisReady() throws IOException, InterruptedException;

    void updateGodSet(PlayerInterface player, String godName) throws IOException;

    void updateBoard(Board board) throws IOException;

    void updateTimeToChoose(String name, String color) throws IOException;

    void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName) throws IOException;

    void updateWinners(PlayerInterface player) throws IOException;

    void updateMoving(int worker, String current,  List<BoardCell> available) throws IOException;

    void updateBuilding(int worker, String current,  List<BoardCell> available) throws IOException;

    void updateSetWorker(int i, String currentPlayer) throws IOException;

    void updateCardNotPresent(String nickname, List<String> availableGods , List<God> chosenGods) throws IOException;

    void updateGodNotAdded(String challengerName) throws IOException;

    void updateChoose(boolean chosenGods, List<God> Names, String ChallengerName) throws IOException;

    void updateNicknameNotValid(String nickname) throws IOException;

    void updatePlayerHasLost(String playerName) throws IOException;

    void updateDecideWorker(String nickname) throws IOException;

    void updateTimeToSetCard(List<String> chosenGods, PlayerInterface currentPlayerName) throws IOException;

    void updateTimeToPlaceWorker(String currentPlayerName) throws IOException;

    void updateWorkerSelected(int worker, String current, List<BoardCell> available) throws IOException;

    void updateNoCoordinatesValid(int worker, String current, List<BoardCell> available) throws IOException;

    void updateTimeToBuild(int worker, String current, List<BoardCell> av) throws IOException;

    void updateBoardAddedWorker(Board board, String currentPlayer) throws IOException;

    void updateSetCard(List<String> availableGods, String currentPlayer, List<God> chosenGods) throws IOException;

    void updateSetWorkerOk(String currentWorker, Board board) throws IOException;

    void updateStartMoving(String current) throws IOException;

    void updateTimeToChooseWorker(String current) throws IOException;

    void updateAskForEffect(String currentPlayer) throws IOException;
    
    void updateAskForEffectBuild(String currentPlayer, int worker) throws IOException;

    void updateBuildTwoInput(String currentPlayer,int worker) throws IOException;

    void updateMoveTwoInput(String nickname, int worker) throws IOException;

    void updateDroppedConnection(String nickname) throws IOException;

    void updateSetFirstPlayer(String nickname, List<PlayerInterface> onlinePlayers) throws IOException;

    void updateNoCoordinatesValidBuildTwoInput(int worker, String nickname) throws IOException;

    void updateNoCoordinatesValidMoveTwoInput(int worker, String nickname) throws IOException;

    void updateServerRestart() throws IOException;
}

