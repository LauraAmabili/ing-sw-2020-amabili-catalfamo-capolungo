package it.polimi.ingsw.Model.God;

//false, false, false, false, false, false, false, false, false);

import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;


public class GodFileCreator {

    Gson gson = new Gson();
    String godFile = "./godFile.json";

    public void create() throws IOException {
        ArrayList<God> arrayGods = new ArrayList<>();
        arrayGods.add(new God("Apollo", false, false, false, false, false, false, true, false, false));
        arrayGods.add(new God("Artemis", false, false, false, false, true, false, false, false, false));
        arrayGods.add(new God("Athena", false, false, false, false, false, false, false, true, false));
        arrayGods.add(new God("Atlas", false, false, true, false, false, false, false, false, false));
        arrayGods.add(new God("Demeter", true, false, false, false, false, false, false, false, false));
        arrayGods.add(new God("Hephaestus", false, true, false, false, false, false, false, false, false));
        arrayGods.add(new God("Minotaur", false, false, false, false, false, true, false, false, false));
        arrayGods.add(new God("Pan", false, false, false, false, false, false, false, false, true));
        arrayGods.add(new God("Prometheus", false, false, false, true, false, false, false, false, false));
        write (arrayGods);
    }

    public void write(ArrayList<God> arrayGods) throws IOException {
        FileWriter fileWriter = new FileWriter(godFile);
        gson.toJson(arrayGods, fileWriter);
        fileWriter.close();
    }

}


