

package it.polimi.ingsw.Network.Server;

public class Connection {
    public Connection(ServerThread serverThread, long inst) {
        this.serverThread = serverThread;
        this.inst = inst;
    }

    public ServerThread serverThread;
    public long inst;
}