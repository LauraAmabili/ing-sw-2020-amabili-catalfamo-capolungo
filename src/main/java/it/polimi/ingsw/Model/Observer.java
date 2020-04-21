package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;

public interface Observer {

    //public void update(Object obj);
    void update(Object o, Object obj);
    void updatePlayerAdded(Object obj);
    void updateGameisReady();
    void updateGodSetted(PlayerInterface player, String godName);
    void updatePlayerDecorated(PlayerInterface playerDecorated);
    void updateBoard(Board board);
    void updateTimeToChoose(List gods);
    void updateStateError();
}
