package it.polimi.ingsw.Model.Player;

//read conf from file

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialBuild_BuildTwiceDifferent;
import it.polimi.ingsw.Model.God.God;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class PlayerCreator {

    Gson gson = new Gson();
    String godFile = "./godFile.json";

    public PlayerInterface createPlayer(String godName, PlayerInterface p) throws IOException {


        God god = find(godName);
        if (god.isSpecialBuild_BuildTwiceDifferent())
            p = new SpecialBuild_BuildTwiceDifferent(p);

        return p;

    }


    public God find(String g) throws IOException {
        FileReader fileReader = new FileReader(godFile);

        Type userListType = new TypeToken<ArrayList<God>>(){}.getType();
        ArrayList<God> arrayGods = gson.fromJson(fileReader, userListType);

        for (God x : arrayGods){
            if (x.getGodName().equals(g)){
                fileReader.close();
                return x;
            }
        }


        fileReader.close();
        return null;



    }
}
