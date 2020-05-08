package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.List;

public interface ObserverModel {

    //public void update(Object obj);
    void update(Object o, Object obj);
    void updatePlayerAdded(String obj);
    void updateGameisReady() throws IOException;
    void updateGodSet(String player, String godName);
    void updateBoard(Board board);
    void updateTimeToChoose(List gods, String name);
    void updateGodAdded(List<String> gods, boolean cardChosen);
    void updateWinners(PlayerInterface player);
    void updateMoving(int worker);
    void updateBuilding(int worker);
    void updateSetWorker(int i);
    void updateCardNotPresent(List chosenGods);
    void updateGodNotAdded();
    void updateChoose(boolean chosenGods, List Names, String ChallengerName);
    void updateNicknameNotValid() throws IOException;
    void updatePlayerHasLost(String playerName);
    void updateDecideWorker(String nickname);
    void updateTimeToSetCard(String currentPlayerName);
    void updateTimeToPlaceWorker(String currentPlayerName);
    void updateGodAlreadyChosen(String godName);
    void updateWorkerSelected(int worker);
    void updateNoCoordinatesValid(int worker);
    void updateTimeToBuild(int worker);

}
