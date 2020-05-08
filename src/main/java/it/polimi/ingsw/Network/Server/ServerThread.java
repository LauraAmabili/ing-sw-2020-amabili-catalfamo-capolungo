package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.MessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromClient.MockMessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MockMessageToClient;
import it.polimi.ingsw.Network.Message.Ping;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread implements Runnable {

    Socket socket;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ServerThread(Socket socket) {
        this.socket = socket;

    }

    public void run() {
        ServerThread serverThread = new ServerThread(socket);
        try {
            serverThread.startServer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void startServer() throws IOException, ClassNotFoundException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println(((MockMessageToServer) receive()).string);
        send(new MockMessageToClient("fromServer"));

        socket.close();
    }

    public void send(MessageToClient x) throws IOException {
        out.writeObject(x);
    }

    public MessageToServer receive() throws IOException, ClassNotFoundException {
        return ((MessageToServer) in.readObject());
    }



}