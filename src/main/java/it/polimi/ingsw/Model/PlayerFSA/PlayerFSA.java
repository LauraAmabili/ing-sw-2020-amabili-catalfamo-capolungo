package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public interface PlayerFSA {

    PlayerInterface player = null;

    void addNickname(String name);

    void chosenCards(List<God> godName);

    void setCard(God godName);

    void placeWorker(int row, int col, Worker worker);

    void Move(int row, int col, Worker worker);

    void Build(int row, int col, Worker worker);

}
