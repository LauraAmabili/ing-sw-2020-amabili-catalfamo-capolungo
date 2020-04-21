package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.*;

public class DecoratoreReflection {

    public DecoratoreReflection() {

    }
    public PlayerInterface Apollo (PlayerInterface player){
        return new SpecialMove_SwapWorkers(player);
    }
    public PlayerInterface Artemis(PlayerInterface player){
        return new SpecialMove_MoveTwice(player);
    }
    public PlayerInterface Athena (PlayerInterface player){
        return new SpecialOpponentTurn_LockMoveUp(player);
    }
    public PlayerInterface Atlas (PlayerInterface player){ return new SpecialBuild_DomeAnyLevel(player); }
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

}
