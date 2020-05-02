package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientSocket extends Client implements Runnable {

    private transient Socket socket;

    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;

    public ClientSocket(String username, String address, int port, List<Message> messageQueue) {
        super(username, address, port, messageQueue);
    }


    public void startConnection() throws IOException {
        socket = new Socket(getAddress(), getPort());
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        Thread messageReceiver = new Thread(this);
        messageReceiver.start();
    }


    @Override
    public void sendMessage(Message message) throws IOException {
        if (out != null) {
            out.writeObject(message);
            out.reset();
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void run() {

    }
}