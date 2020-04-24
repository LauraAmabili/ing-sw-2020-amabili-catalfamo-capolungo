package it.polimi.ingsw.Model.Player;

//read conf from file

import com.google.gson.Gson;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.Player.SpecialEffects.SpecialBuild_BuildTwiceDifferent;
import it.polimi.ingsw.Model.God.God;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PlayerCreator {

    Gson gson = new Gson();
    String godFile = "D:/OneDrive/ing-sw-2020-amabili-catalfamo-capolungo/godFile.json";

    public PlayerInterface createPlayer(String godName, String nickname) throws IOException {
        PlayerInterface p = new Player();
        p.setNickname(nickname);
        God god = find(godName);
        if (god.isSpecialBuild_BuildTwiceDifferent())
            p = new SpecialBuild_BuildTwiceDifferent(p);

        return p;

    }

    public void write(God god) throws IOException {
        FileWriter fileWriter = new FileWriter(godFile);
        gson.toJson(god, fileWriter);
        fileWriter.close();
    }

    public God read() throws IOException {
        FileReader fileReader = new FileReader(godFile);
        God god = gson.fromJson(fileReader, God.class);
        fileReader.close();
        return god;
    }

    public God find(String g) throws IOException {
        FileReader fileReader = new FileReader(godFile);
        God god;
        do{
            god = gson.fromJson(fileReader, God.class);

        }
        while (god.getGodName()==g);
        fileReader.close();
        return god;
    }
}
