package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.HashMap;
import java.util.Map;

public class Ref {


    public Ref() {}
    private Map<String, PlayerInterface> nome = new HashMap<>();

    /*
    public void put() {

        nome.put("Apollo", new SpecialMove_SwapWorkers(new Player()));
        nome.put("Artemis", new SpecialMove_MoveTwice(new Player()));
        nome.put("Athena", new SpecialOpponentTurn_LockMoveUp(new Player()));
        nome.put("Atlas", new SpecialBuild_DomeAnyLevel(new Player()));
        nome.put("Demeter", new SpecialBuild_BuildTwiceDifferent(new Player()));
        nome.put("Hephaestus", new SpecialBuild_BuildTwiceSame(new Player()));
        nome.put("Minotaur", new SpecialMove_PushOpponent(new Player()));
        nome.put("Pan", new SpecialWin_MoveDown(new Player()));
    }
    public PlayerInterface Decorator(String godName){
        return nome.get(godName);
    }
    */
/*
    public PlayerInterface Apollo (PlayerInterface player) {
        PlayerInterface playerDecorated = new SpecialMove_SwapWorkers(player);
        return playerDecorated;
    }

    public PlayerInterface Artemis (PlayerInterface player){
        return new SpecialMove_MoveTwice(player);
    }
    public PlayerInterface Athena (PlayerInterface player){
        return new SpecialOpponentTurn_LockMoveUp(player);
    }

    public PlayerInterface Atlas (PlayerInterface player){
        return new SpecialBuild_DomeAnyLevel(player);
    }

    public PlayerInterface Demeter (PlayerInterface player){
        return new SpecialBuild_BuildTwiceDifferent(player);
    }

    public PlayerInterface Hephaestus(PlayerInterface player){
        return new SpecialBuild_BuildTwiceSame(player);
    }

    public PlayerInterface Minotaur(PlayerInterface player){
        return new SpecialMove_PushOpponent(player);
    }

    public PlayerInterface Pan(PlayerInterface player){
        return new SpecialWin_MoveDown(player);
    }

 */

}
