package it.polimi.ingsw.Model.Player;

//read conf from file

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Helper.GameConf;
import it.polimi.ingsw.Model.Player.SpecialEffects.*;
import it.polimi.ingsw.Model.God.God;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlayerCreator {

    Gson gson = new Gson();
    String godConf = GameConf.getGodConf();
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

    public PlayerInterface createPlayer(String godName, PlayerInterface p) {
        God god = find(godName);
        p = addEffects(p, god.getEffects());
        return p;
    }


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
        return p;
    }

    public void read() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File((Objects.requireNonNull(getClass().getClassLoader().getResource("Configurations/godConf.json"))).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type userListType = new TypeToken<ArrayList<God>>(){}.getType();
        assert fileReader != null;
        arrayGods = gson.fromJson(fileReader, userListType);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public God find(String g) {
        for (God x : arrayGods){
            if (x.getGodName().equals(g)){
                return x;
            }
        }
        return null;
    }
}
