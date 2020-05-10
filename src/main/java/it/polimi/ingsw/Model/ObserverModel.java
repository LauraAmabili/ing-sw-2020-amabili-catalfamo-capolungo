package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.List;

public interface ObserverModel {

    //public void update(Object obj);
    void update(Object o, Object obj);
    void updatePlayerAdded(String obj) throws IOException;
    void updateGameisReady() throws IOException, InterruptedException;
    void updateGodSet(String player, String godName) throws IOException;
    void updateBoard(Board board) throws IOException;
    void updateTimeToChoose(List gods, String name) throws IOException;
    void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName) throws IOException;
    void updateWinners(PlayerInterface player);
    void updateMoving(int worker) throws IOException;
    void updateBuilding(int worker) throws IOException;
    void updateSetWorker(int i, String currentPlayer) throws IOException;
    void updateCardNotPresent(String nickname, List<String> chosenGods) throws IOException;
    void updateGodNotAdded(String challengerName) throws IOException;
    void updateChoose(boolean chosenGods, List Names, String ChallengerName) throws IOException;
    void updateNicknameNotValid(String nickname) throws IOException;
    void updatePlayerHasLost(String playerName) throws IOException;
    void updateDecideWorker(String nickname) throws IOException;
    void updateTimeToSetCard(List<String> chosenGods, String currentPlayerName) throws IOException;
    void updateTimeToPlaceWorker(String currentPlayerName) throws IOException;
    //void updateGodAlreadyChosen(List <String> chosenGods, String godName) throws IOException;
    void updateWorkerSelected(int worker) throws IOException;
    void updateNoCoordinatesValid(int worker) throws IOException;
    void updateTimeToBuild(int worker) throws IOException;
    void updateBoardAddedWorker(int row, int col, int worker) throws IOException;
}
