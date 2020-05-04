package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.Message;

import java.io.IOException;

public abstract class Connection {
    private boolean connected = true;
    private String token;


    public boolean isConnected() {
        return connected;
    }


    public abstract void sendMessage(Message message) throws IOException;


    public abstract void disconnect();


    public abstract void ping();

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
