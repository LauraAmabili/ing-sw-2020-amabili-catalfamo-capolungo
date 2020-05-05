package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Message.Greetings;
import it.polimi.ingsw.Network2.Message;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

class ServerThread extends Thread implements Runnable {

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

        System.out.println(receive());

        send("MessageFromServer");


        socket.close();
    }

    public void send(String x) throws IOException {
        out.writeObject(x);
    }

    public String receive() throws IOException, ClassNotFoundException {
        String x = (String) in.readObject();
        return x;
    }

}