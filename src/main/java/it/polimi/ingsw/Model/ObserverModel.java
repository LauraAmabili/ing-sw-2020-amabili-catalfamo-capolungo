package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.List;

public interface ObserverModel {

    //public void update(Object obj);
    public void update(Object o, Object obj);
    public void updatePlayerAdded(Object obj);
    public void updateGameisReady();
    public void updateGodSetted(PlayerInterface player, String godName);
    public void updatePlayerDecorated(PlayerInterface playerDecorated);
    public void updateBoard(Board board);
    public void updateTimeToChoose(List gods, String name);
    public void updateGodAdded(List<God> gods, boolean cardChosen);
    public void updateWinners(PlayerInterface player);
    public void updateMoving(int worker);
    public void updateBuilding(Object obj, int worker);
    public void updateSetWorker(int i);
    public void updateCardNotPresent(List chosenGods);
    public void updateGodNotAdded();
    public void updateChoose(boolean chosenGods, List Names, String ChallengerName);
    public void updateNicknameNotValid();
    public void updatePlayerHasLost(String playerName);
    public void updateDecideWorker(Object obj);
}
