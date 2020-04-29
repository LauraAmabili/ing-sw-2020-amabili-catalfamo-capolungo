package it.polimi.ingsw.Model.Player;

//read conf from file

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.Player.SpecialEffects.*;
import it.polimi.ingsw.Model.God.God;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayerCreator {

    Gson gson = new Gson();
    String godFile = "./godFile.json";
    ArrayList<God> arrayGods;

    public PlayerInterface createPlayer(String godName, PlayerInterface p) throws IOException {
        if (arrayGods==null)
            read();
        God god = find(godName);
        if (god.getEffects().contains("SpecialBuild_BuildTwiceDifferent"))
            p = new SpecialBuild_BuildTwiceDifferent(p);
        if (god.getEffects().contains("SpecialBuild_BuildTwiceSame"))
            p = new SpecialBuild_BuildTwiceSame(p);
        if (god.getEffects().contains("SpecialBuild_DomeAnyLevel"))
            p = new SpecialBuild_DomeAnyLevel(p);
        if (god.getEffects().contains("SpecialMove_BMB"))
            p = new SpecialMove_BMB(p);
        if (god.getEffects().contains("SpecialMove_MoveTwice"))
            p = new SpecialMove_MoveTwice(p);
        if (god.getEffects().contains("SpecialMove_PushOpponent"))
            p = new SpecialMove_PushOpponent(p);
        if (god.getEffects().contains("SpecialMove_SwapWorkers"))
            p = new SpecialMove_SwapWorkers(p);
        if (god.getEffects().contains("SpecialOpponentTurn_LockMoveUp"))
            p = new SpecialOpponentTurn_LockMoveUp(p);
        if (god.getEffects().contains("SpecialWin_MoveDown"))
            p = new SpecialWin_MoveDown(p);
        return p;

    }

    public void read() throws IOException {
        FileReader fileReader = new FileReader(godFile);
        Type userListType = new TypeToken<ArrayList<God>>(){}.getType();
        arrayGods = gson.fromJson(fileReader, userListType);
        fileReader.close();
    }
    public God find(String g) throws IOException {
        for (God x : arrayGods){
            if (x.getGodName().equals(g)){
                return x;
            }
        }
        return null;
    }
}
