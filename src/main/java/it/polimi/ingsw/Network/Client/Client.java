package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Network.Message.MessageFromClient.MessageFromClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageFromServer;


import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;

public class Client {

    private int port;
    private String nickname;
    private Gson gson;
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private VisitorMethodsClient visit;
    private boolean active;
    private UserInterface userInterface;
    private UpdatesForMessages updatesForMessages;

    public Client(UserInterface userInterface) throws IOException {
        updatesForMessages = new UpdatesForMessages(this);
        this.userInterface = userInterface;
        gson = new Gson();
        active = true;
        visit = new VisitorMethodsClient(this,userInterface);
        read();
        //startClient();
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

    public void startClient() throws IOException {

        socket = new Socket("localhost", port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        ClientBeatSender clientBeatSender = new ClientBeatSender(this);
        clientBeatSender.start();

        try {

            //System.out.println("Press x to start the game");
            //String inout = scanner.nextLine();
            Thread T0 = receive();
            T0.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            in.close();
            out.close();
            socket.close();
        }

    }



    public synchronized void send(MessageFromClient x) throws IOException {

        out.writeObject(x);
        out.flush();

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