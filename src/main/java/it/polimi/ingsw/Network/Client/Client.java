package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Network.Message.MessageFromClient.MessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromClient.MockMessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MockMessageToClient;


import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private int port;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;


    public Client() throws IOException {
        read();

    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.startClient();
    }

    public void startClient() throws IOException, ClassNotFoundException {
        socket = new Socket("localhost", port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        send(new MockMessageToServer("fromClient"));
        System.out.println(((MockMessageToClient) receive()).string);


    }

    public void send(MessageToServer x) throws IOException {
        out.writeObject(x);
    }

    public MessageToClient receive() throws IOException, ClassNotFoundException {
        MessageToClient x = (MessageToClient) in.readObject();
        return x;
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