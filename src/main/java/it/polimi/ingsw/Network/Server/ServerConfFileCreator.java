package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import it.polimi.ingsw.Model.God.God;

import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;


public class ServerConfFileCreator {

    private static Gson gson = new Gson();
    private static String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";

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
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        create();
    }



}

