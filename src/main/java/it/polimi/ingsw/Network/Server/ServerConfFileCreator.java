package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Helper.GameConf;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Helper Class used to save the configurations of the server on a file
 */
public class ServerConfFileCreator {

    private static final Gson gson = new Gson();
    private static final String file = GameConf.getServerConf();


    public static void create() {
        int port = 7777;
        write (port);
    }

    public static void write(int x)  {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(x, fileWriter);
        try {
            assert fileWriter != null;
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        create();
    }



}


