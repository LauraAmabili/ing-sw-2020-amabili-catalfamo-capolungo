package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.MessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;
import it.polimi.ingsw.View.VirtualView;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread implements Runnable {

    private VirtualView view = new VirtualView(this);
    private VisitorServer visitor = new VisitorMethodsServer(view);
    Socket socket;
    Server server;

    private ObjectOutputStream out;
    private ObjectInputStream in;


    private boolean ready = false;

    public VirtualView getView() {
        return view;
    }

    public ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;


    }

    public void run() {
        while (true) {
            try {
                MessageToServer message = receiveFromClient();
                message.accept(visitor);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public void startServer() throws IOException, ClassNotFoundException {

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        //messageIn = receiveFromClient();
        ready=true;
    }

    public void sendToClient(MessageToClient x) throws IOException {
        out.writeObject(x);
    }

    public MessageToServer receiveFromClient() throws IOException, ClassNotFoundException {

        return ((MessageToServer) in.readObject());
    }


    public boolean isReady() {
        return ready;
    }
}