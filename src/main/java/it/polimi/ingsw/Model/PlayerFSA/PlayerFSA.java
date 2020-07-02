package it.polimi.ingsw.Model.PlayerFSA;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.Objects;

public abstract class PlayerFSA {

    PlayerInterface player = null;


    public PlayerInterface getPlayer() {
        return player;
    }

    public void setPlayer(PlayerInterface player) {
        this.player = player;
    }

    public void addNickname(String name)  {

    }

    public void chosenCard(String godName)  {

    }

    public void setCard(String godName)  {

    }

    public void placeWorker(int row, int col, int worker)  {

    }

    public void canIMove()  {

    }

    public boolean getEffect() {
        return false;
    }

    public void checkWorker(int worker, boolean effect)  {

    }

    public void move(int row, int col, int worker)  {

    }

    public void move(int row1, int col1, int row2, int col2, int worker)  {

    }

    public void checkBuild(int worker, boolean effect)  {

    }

    public void build(int row1, int col1, int row2, int col2, int worker)  {

    }

    public void build(int row, int col, int worker)  {

    }

    public void next() {

    }

    @Override
    public boolean equals(Object o) {
        // self check
        if(this == o) {
            return true;
        } else if(o == null) {
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
