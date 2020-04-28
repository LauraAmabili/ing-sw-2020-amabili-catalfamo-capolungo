package it.polimi.ingsw.Model.God;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class GodFileCreator {

    Gson gson = new Gson();
    String godFile = "./src/main/java/it/polimi/ingsw/resources/godFile.json";

    public void create() {
        ArrayList<God> arrayGods = new ArrayList<>();
        arrayGods.add(new God("Apollo", Arrays.asList("SpecialMove_SwapWorkers")));
        arrayGods.add(new God("Artemis", Arrays.asList("SpecialMove_MoveTwice")));
        arrayGods.add(new God("Athena", Arrays.asList("SpecialOpponentTurn_LockMoveUp")));
        arrayGods.add(new God("Atlas", Arrays.asList("SpecialBuild_DomeAnyLevel")));
        arrayGods.add(new God("Demeter", Arrays.asList("SpecialMove_SwapWorkers")));
        arrayGods.add(new God("Hephaestus", Arrays.asList("SpecialBuild_BuildTwiceDifferent")));
        arrayGods.add(new God("Minotaur", Arrays.asList("SpecialMove_PushOpponent")));
        arrayGods.add(new God("Pan", Arrays.asList("SpecialWin_MoveDown")));
        arrayGods.add(new God("Prometheus", Arrays.asList("SpecialMove_BMB")));
        write (arrayGods);
    }

    public void write(ArrayList<God> arrayGods)  {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(godFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(arrayGods, fileWriter);
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


