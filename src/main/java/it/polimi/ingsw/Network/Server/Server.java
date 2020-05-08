package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Helper.GameConf;
import it.polimi.ingsw.Model.God.God;
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
    private int numPlayers;


    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }



    public Server() {
        read();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server server = new Server();
        server.startServer();
    }

    public void startServer() throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        Socket s = null;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("ServerSocket awaiting connections...");
        while (true) {
            s = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + s + "!");
            ServerThread st = new ServerThread(s);
            st.start();
            clients.add(st);
            System.out.println(clients.size());

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
