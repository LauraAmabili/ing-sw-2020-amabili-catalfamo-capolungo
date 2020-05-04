package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Helper.GameConf;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Network.Message.Message;

import java.io.*;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;


public class Server {

    private static int port;
    private static Gson gson = new Gson();
    private static String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private Map<String, Connection> clients;
    private boolean gameLoading;

    public Server() {
        read();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new Server();
        // don't need to specify a hostname, it will be the current machine
        Socket s = null;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("ServerSocket awaiting connections...");
        while (true) {
            s = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + s + "!");
            ServerThread st = new ServerThread(s);
            st.start();
        }


    }

    public static void read() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type userListType = new TypeToken<Integer>() {
        }.getType();
        assert fileReader != null;
        port = gson.fromJson(fileReader, userListType);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private boolean checkUsername(String username) {
        for (String forbidden : GameConf.getIllegalUsernames()) {
            if (username.equalsIgnoreCase(forbidden)) {
                return false;
            }
        }
        return true;

    }

    void login(String username, Connection connection) throws IOException {
        if (clients.containsKey(username)) {
            knownPlayerLogin(username, connection);
        } else {
            newPlayerLogin(username, connection);
        }

    }

    private void knownPlayerLogin(String username, Connection connection) throws IOException {
        if (clients.get(username) == null || !clients.get(username).isConnected()) { // Player Reconnection
            clients.replace(username, connection);

            String token = UUID.randomUUID().toString();
            connection.setToken(token);

            if (!gameLoading) {// Game in lobby state for load a game

            } else {

            }

        } else { // Player already connected

        }
    }

    private void newPlayerLogin(String username, Connection connection) throws IOException {
        //check if game has already started
        //check if lobby is full
        if (false){

        }
        else { // New player
            if (checkUsername(username)) { // Username legit
                clients.put(username, connection);

                String token = UUID.randomUUID().toString();
                connection.setToken(token);


            } else { // Username not legit


                connection.disconnect();
            }
        }
    }


}
