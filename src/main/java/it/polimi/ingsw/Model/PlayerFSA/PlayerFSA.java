package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Worker;

public interface PlayerFSA {

    Player player = null;

    void setCard(God godName);

    void Move(int row, int col, Worker worker);

    void Build(int row, int col, Worker worker);

}
