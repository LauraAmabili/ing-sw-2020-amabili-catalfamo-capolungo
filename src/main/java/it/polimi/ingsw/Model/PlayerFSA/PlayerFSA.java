package it.polimi.ingsw.Model.PlayerFSA;


import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Worker;

import java.util.List;
import java.util.Objects;

public abstract class PlayerFSA {

    PlayerInterface player = null;


    //TODO: se modifichi a boolean mi raccomando ricordati di rimodificare qui il tipo di ritorno

    public void addNickname(String name) {

    }

    public void chosenCards(List<God> godName) {

    }

    public void setCard(God godName) {

    }

    public void placeWorker(int row, int col, Worker worker) {

    }

    public void Move(int row, int col, Worker worker) {

    }

    public void Build(int row, int col, Worker worker) {

    }

    public void Next() {

    }


    @Override
    public boolean equals(Object o){
        // self check
        if(this == o){
            return true;
        } else if(o == null){
            // null check
            return false;
        } else if(getClass() != o.getClass()) {
            // type check and cast
            return false;
        } else {
            final PlayerFSA a = (PlayerFSA) o;
            // field comparison
            return Objects.equals(a, a);
        }
    }

}
