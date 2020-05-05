package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.Ping;

import java.io.*;
import java.net.Socket;

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

        send(new Ping("1", "1", "Message from server"));

        socket.close();
    }

    public void send(Ping x) throws IOException {
        out.writeObject(x);
    }

    public Ping receive() throws IOException, ClassNotFoundException {
        Ping x = (Ping) in.readObject();
        return x;
    }

}