

package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Controller.GameController;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import static java.lang.Thread.sleep;


public class Server {

    private int port;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private ArrayList<ServerThread> serverThreads = new ArrayList<>();
    public ServerBeatReceiver serverBeatReceiver;

    GameController gameController = new GameController();

    public Server() {
        read();
    }


    public List<ServerThread> getServerThreads() {
        return serverThreads;
    }

    public GameController getGameController() {
        return gameController;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server server = new Server();
        server.startServer();

    }

    public void startServer() throws IOException, ClassNotFoundException, InterruptedException {

        serverBeatReceiver = new ServerBeatReceiver(this);
        new Thread(serverBeatReceiver).start();
        connectClients();
    }


    public void connectClients() throws IOException, ClassNotFoundException {

        Socket s = null;
        ServerSocket ss = new ServerSocket(port);

        while (true) {
            s = ss.accept();
            System.out.println("Connection from " + s + "!");
            ServerThread st;
            if(serverThreads.size() == 0) {
                st = new ServerThread(s, this, 2, false);
            } else {
                st = new ServerThread(s, this, serverThreads.get(0).getNumPlayers(), serverThreads.get(0).isMaxPlrSet());
            }
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