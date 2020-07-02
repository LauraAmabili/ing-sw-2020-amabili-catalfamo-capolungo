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

public class Client {

    private int port;
    private String nickname;
    private final Gson gson;
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
    public ClientBeatSender getClientBeatSender() {
        return clientBeatSender;
    }


    /**
     * Client is started on a defined host and port
     * Client Beat Sender is started
     * @param host ip server
     * @param port server port
     */
    public void startClient(String host, String port) {

        try {
            socket = new Socket(host, Integer.parseInt(port));
        } catch (ConnectException | NumberFormatException e) {
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
        Thread T0 = receive();

    }


    /**
     * Sends a message to the server
     * @param x message
     */
    public synchronized void send(MessageFromClient x)  {

        try {
            out.writeObject(x);
            out.flush();
        } catch (IOException e) {
            active = false;
            clientBeatSender.setActive(false);
        }

    }

    /**
     * receive messages and share them with the visitor
     */
    public Thread receive() {
        Thread t = new Thread(() -> {
            try {
                while (isActive()) {
                    try {
                        MessageFromServer x = (MessageFromServer) in.readObject();
                        x.accept(visit);
                    }
                    catch (WriteAbortedException e){
                        setActive(false);
                        clientBeatSender.setActive(false);
                        killClient();
                    }
                    catch (SocketException e) {
                        System.out.println("Server is offline");
                        System.out.println("You have been disconnected");
                        setActive(false);
                        clientBeatSender.setActive(false);
                        killClient();
                        return;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                clientBeatSender.setActive(false);
                setActive(false);

            }
        });
        t.start();
        return t;
    }

    /**
     * read configuration from file
     */
    public void read() {

        BufferedReader fileReader = null;
        fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Configurations/serverConf.json"))));
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

    /**
     * close outgoing channels
     */
    public void killClient() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("You have been disconnected");
        }
    }

}