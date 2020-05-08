package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Helper.GameConf;
import it.polimi.ingsw.Network.Message.MessageToClient;
import it.polimi.ingsw.View.VirtualView;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {

    private int port;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private boolean gameLoading;
    private static int maxClient = 3;
    private ArrayList<Connection> connections;
    private List<VirtualView> viewList = new ArrayList<>();
    private List<ServerThread> threadList = new ArrayList<>();

    public Server() {
        read();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Server server = new Server();
        server.startServer();
    }

    public void send(VirtualView view, MessageToClient message) throws IOException {
        int i = viewList.indexOf(view);
        ServerThread thread = threadList.get(i);
        thread.send(message);
    }


    public void startServer() throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        Socket s = null;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("ServerSocket awaiting connections...");
        while (true) {
            //if (clients.size() <= maxClient) {
            s = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + s + "!");
            connections.add(new Connection("a", s.getPort(), s.getInetAddress()));
            ServerThread st = new ServerThread(s);
            threadList.add(st);
            st.start();
            VirtualView view = new VirtualView(this);
            viewList.add(view);
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
