package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {

    private int port;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    private int numPlayers;
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


        /*
        System.out.println(clients.get(2).receiveFromClient());
        clients.get(2).sendToClient(new Ping("UE", "a", "Server"));
        */
    }


    public void connectClients() throws IOException, ClassNotFoundException {
        Socket s = null;
        ServerSocket ss = new ServerSocket(port);

        while (true) {

            s = ss.accept();
            System.out.println("Connection from " + s + "!");
            ServerThread st = new ServerThread(s, this);
            st.start();
            st.startServer();
            clients.add(st);
            if (clients.size() == 1)
                requestPlayers(clients.get(0));
            if (clients.size() == numPlayers + 1) {
                st.interrupt();
                clients.remove(clients.size() - 1);
            }

        }

    }

    public void requestPlayers(ServerThread st) {
        st.sendToClient(new NumberOfPlayersRequest(""));


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
