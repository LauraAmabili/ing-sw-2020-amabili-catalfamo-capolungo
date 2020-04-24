package it.polimi.ingsw.Model.God;

//write conf on file

import com.google.gson.*;

import java.io.*;


public class GodFileCreator {

    Gson gson = new Gson();
    String godFile = "D:/OneDrive/ing-sw-2020-amabili-catalfamo-capolungo/godFile.json";

    public void create() throws IOException {
        God Athena = new God("Athena", true);
        write(Athena);
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

}


