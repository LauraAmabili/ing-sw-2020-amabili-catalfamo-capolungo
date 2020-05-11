package it.polimi.ingsw.Network.Server;

import java.net.InetAddress;

public class Connection {
    private ServerThread serverThread;
    int LastPing;
    private boolean pingReceived;

    public int getLastPing() {
        return LastPing;
    }

    public void setLastPing(int lastPing) {
        LastPing = lastPing;
    }

    public ServerThread getServerThread() {
        return serverThread;
    }

    public void setServerThread(ServerThread serverThread) {
        this.serverThread = serverThread;
    }

    public boolean isPingReceived() {
        return pingReceived;
    }

    public void setPingReceived(boolean pingReceived) {
        this.pingReceived = pingReceived;
    }



    public Connection(ServerThread serverThread, boolean pingReceived) {
        this.serverThread = serverThread;
        this.pingReceived = pingReceived;
    }
}
