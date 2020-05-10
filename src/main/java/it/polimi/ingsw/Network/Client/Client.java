package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Network.Message.MessageFromClient.FirstInput;
import it.polimi.ingsw.Network.Message.MessageFromClient.MessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;


import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private int port;
    private String nickname;
    private Gson gson = new Gson();
    private String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private VisitorMethodsClient visit = new VisitorMethodsClient(this);
    Scanner scanner = new Scanner(System.in);
    private boolean active = true;

    public Client() throws IOException {
        read();

    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.startClient();
    }

    public void startClient() throws IOException, ClassNotFoundException {

        socket = new Socket("localhost", port);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        //send(new MockMessageToServer("fromClient"));
        //System.out.println(((MockMessageToClient) receive()).string);

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



    public void send(MessageToServer x) throws IOException {

        out.writeObject(x);
        out.flush();

    }

    public Thread receive() throws IOException, ClassNotFoundException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        try {
                            MessageToClient x = (MessageToClient) in.readObject();
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