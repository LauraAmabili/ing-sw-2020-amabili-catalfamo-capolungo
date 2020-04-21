package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;

public interface Observer {

    //public void update(Object obj);
    public void update(Object o, Object obj);
    public void updatePlayerAdded(Object obj);
    public void updateGameisReady();
    public void updateGodSetted(PlayerInterface player, String godName);
    public void updatePlayerDecorated(PlayerInterface playerDecorated);
    public void updateBoard(Board board);
    public void updateTimeToChoose(List gods);
}
