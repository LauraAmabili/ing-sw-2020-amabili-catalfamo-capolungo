package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.Message;

import java.io.IOException;
import java.util.List;

public abstract class Client {
    private final String username;
    private final String address;
    private final int port;

    final List<Message> messageQueue;


    public Client(String username, String address, int port, List<Message> messageQueue) {
        this.username = username;
        this.address = address;
        this.port = port;
        this.messageQueue = messageQueue;
    }


    String getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }

    public abstract void sendMessage(Message message) throws IOException;

    public abstract void close() throws Exception;


    public String getUsername() {
        return username;
    }



}
