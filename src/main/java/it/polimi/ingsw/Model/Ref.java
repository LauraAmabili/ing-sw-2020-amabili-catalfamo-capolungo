package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Ref {


    public Ref() {}

    public Map<String, PlayerInterface> getNome() {
        return nome;
    }

    public void setNome(Map<String, PlayerInterface> nome) {
        this.nome = nome;
    }

    private Map<String, PlayerInterface> nome = new HashMap<>();

    //PlayerInterface playerDecorated = new SpecialMove_SwapWorkers(playerBase);

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

   public void Decorator(String godName, PlayerInterface player){
       player = nome.get(godName);

    }


}
