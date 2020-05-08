package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.Message;
import it.polimi.ingsw.Network.Message.Ping;

import java.io.*;
import java.net.Socket;

class ServerThread extends Thread implements Runnable {

    Socket socket;
    Server server;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Message messageIn;

    private boolean ready = false;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;


    }

    public void run() {
    }

    public void startServer() throws IOException, ClassNotFoundException {

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());


        //messageIn = receiveFromClient();
        ready=true;
    }

    public void sendToClient(Message x) throws IOException {
        out.writeObject(x);
    }

    public Message receiveFromClient() throws IOException, ClassNotFoundException {

        return ((Message) in.readObject());
    }


    public boolean isReady() {
        return ready;
    }
}