package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Network.Message.MessageFromClient.MessageFromClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageFromServer;


import java.io.*;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    private int port;
    private String nickname;
    private final Gson gson;
    private final String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final VisitorMethodsClient visit;
    private boolean active;
    private final UserInterface userInterface;
    private final UpdatesForMessages updatesForMessages;
    private int numberOfPlayers;
    private ClientBeatSender clientBeatSender;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Client(UserInterface userInterface) {

        updatesForMessages = new UpdatesForMessages(this);
        this.userInterface = userInterface;

        active = true;
        visit = new VisitorMethodsClient(this,userInterface);
        gson = new Gson();
        read();

    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public UpdatesForMessages getUpdatesForMessages() {
        return updatesForMessages;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    /*
    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.startClient();

    }

     */

    public void startClient(String host, String port) {



        try {
            socket = new Socket(host, Integer.parseInt(port));
        } catch (ConnectException e) {
            System.out.println("Server not found");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        clientBeatSender = new ClientBeatSender(this);
        clientBeatSender.start();

        //System.out.println("Press x to start the game");
        //String inout = scanner.nextLine();
        Thread T0 = receive();

    }



    public synchronized void send(MessageFromClient x) throws IOException {

        try {
            out.writeObject(x);
            out.flush();
        } catch (SocketException e) {
            System.out.println("Server Offline");
            active = false;
            killClient();
        }


    }

    public Thread receive() {
        Thread t = new Thread(() -> {
            try {
                while (isActive()) {
                    try {
                        MessageFromServer x = (MessageFromServer) in.readObject();
                        x.accept(visit);
                    }
                    catch (WriteAbortedException e){
                        System.out.println("Non premere nient tanto non puoi ");
                        setActive(false);
                        killClient();
                    }
                    catch (SocketException e) {
                        System.out.println("Server is offline");
                        setActive(false);
                        killClient();
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                setActive(false);

            }
        });
        t.start();
        return t;
    }

    public void read() {
        FileReader fileReader = null;
        try {
            //fileReader = new FileReader("serverConf.json");
            fileReader = new FileReader(new File((Objects.requireNonNull(getClass().getClassLoader().getResource("Configurations/serverConf.json"))).getFile()));
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

    public void killClient() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

}