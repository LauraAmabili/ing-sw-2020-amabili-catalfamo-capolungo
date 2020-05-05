package it.polimi.ingsw.Network.Server;

import java.net.InetAddress;

public class Connection {
    private String username;
    private int port;
    private InetAddress address;

    public Connection(String username, int port, InetAddress address) {
        this.username = username;
        this.port = port;
        this.address = address;
    }
}
