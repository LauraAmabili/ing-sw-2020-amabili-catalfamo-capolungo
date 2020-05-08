package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Helper.GameConf;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Network.Message.Message;
import it.polimi.ingsw.Network.Message.Ping;

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

    private int port;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private static int maxClient = 3;
    private ArrayList<ServerThread> clients = new ArrayList<>();


    public Server() {
        read();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException, ClassNotFoundException, InterruptedException {




        connectClients();
        while(true);
        /*System.out.println(clients.get(0).receiveFromClient());
        clients.get(0).sendToClient(new Ping("UE", "a", "Server"));
        */
    }

    public void connectClients() throws IOException, ClassNotFoundException {
        Socket s = null;
        ServerSocket ss = new ServerSocket(port);

        while (clients.size()<maxClient){
            s = ss.accept();
            System.out.println("Connection from " + s + "!");
            ServerThread st = new ServerThread(s, this);
            st.start();
            st.startServer();
            clients.add(st);


        }


    }


    public void read() {
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
        /*for (String forbidden : usedUsernames) {
            if (username.equalsIgnoreCase(forbidden)) {
                return false;
            }


        }

         */
        return true;

    }

    /*
    void login(String username, Connection connection) throws IOException {
        if (clients.contains(username)) {
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
        if (false) {

        } else { // New player
            if (checkUsername(username)) { // Username legit
                clients.put(username, connection);

                String token = UUID.randomUUID().toString();
                connection.setToken(token);


            } else { // Username not legit


                connection.disconnect();
            }
        }
    }*/


}
