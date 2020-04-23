package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public interface PlayerFSA {

    PlayerInterface player = null;

    void addNickname(String name);

    void chosenCards(List<God> godName);

    void setCard(God godName);

    void Move(int row, int col, Worker worker);

    void Build(int row, int col, Worker worker);

}
