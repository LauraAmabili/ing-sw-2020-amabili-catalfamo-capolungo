package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Controller.GameController;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {

    private int port;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private List<ServerThread> clients = new ArrayList<>();

    GameController gameController = new GameController();

    public Server() {
        read();
    }

    public List<ServerThread> getClients() {
        return clients;
    }

    public GameController getGameController() {
        return gameController;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException, ClassNotFoundException, InterruptedException {
        connectClients();
    }


    public void connectClients() throws IOException, ClassNotFoundException {
        Socket s = null;
        ServerSocket ss = new ServerSocket(port);

        while (true) {
            s = ss.accept();
            System.out.println("Connection from " + s + "!");
            ServerThread st = new ServerThread(s, this);
            st.start();
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


}
