package it.polimi.ingsw.Model.Player;

//read conf from file

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.Player.SpecialEffects.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.NormalGods.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialGods.*;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerCreator {

    Gson gson = new Gson();

    ArrayList<God> arrayGods;

    public PlayerCreator() {
        read();
    }

    public ArrayList<God> getArrayGods() {
        return arrayGods;
    }

    public void setArrayGods(ArrayList<God> arrayGods) {
        this.arrayGods = arrayGods;
    }

    /**
     * create player with the effects of the god passed as input
     * @param godName
     * @param p
     * @return
     */
    public PlayerInterface createPlayer(String godName, PlayerInterface p) {
        God god = find(godName);
        p = addEffects(p, god.getEffects());
        return p;
    }

    /**
     *
     * @param p
     * @param effects
     * @return
     */
    public PlayerInterface addEffects(PlayerInterface p, List<String> effects) {
        if (effects.contains("SpecialBuild_BuildTwiceDifferent"))
            p = new SpecialBuild_BuildTwiceDifferent(p);
        if (effects.contains("SpecialBuild_BuildTwiceSame"))
            p = new SpecialBuild_BuildTwiceSame(p);
        if (effects.contains("SpecialBuild_DomeAnyLevel"))
            p = new SpecialBuild_DomeAnyLevel(p);
        if (effects.contains("SpecialMove_BMB"))
            p = new SpecialMove_BMB(p);
        if (effects.contains("SpecialMove_MoveTwice"))
            p = new SpecialMove_MoveTwice(p);
        if (effects.contains("SpecialMove_PushOpponent"))
            p = new SpecialMove_PushOpponent(p);
        if (effects.contains("SpecialMove_SwapWorkers"))
            p = new SpecialMove_SwapWorkers(p);
        if (effects.contains("SpecialOpponentTurn_LockMoveUp"))
            p = new SpecialOpponentTurn_LockMoveUp(p);
        if (effects.contains("SpecialWin_MoveDown")) {
            p = new SpecialWin_MoveDown(p);
        }
        if (effects.contains("SpecialBuild_BuildDownUnder")) {
            p = new SpecialBuild_BuildDownUnder(p);
        }
        if (effects.contains("SpecialBuild_BuildTwiceDowntown")) {
            p = new SpecialBuild_BuildTwiceDowntown(p);
        }
        if (effects.contains("SpecialBuild_RemoveBlock")) {
            p = new SpecialBuild_RemoveBlock(p);
        }
        if (effects.contains("SpecialMove_SwapOtherSide")) {
            p = new SpecialMove_SwapOtherSide(p);
        }
        if (effects.contains("SpecialWin_BunchOfTowers")) {
            p = new SpecialWin_BunchOfTowers(p);
        }


        return p;
    }

    public void read() {

        BufferedReader fileReader = null;
        fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Configurations/godConf.json"))));


        Type userListType = new TypeToken<ArrayList<God>>() {
        }.getType();
        assert fileReader != null;
        arrayGods = gson.fromJson(fileReader, userListType);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public God find(String g) {
        for (God x : arrayGods) {
            if (x.getGodName().equals(g)) {
                return x;
            }
        }
        return null;
    }
}
