
package it.polimi.ingsw.Network.Server;

import java.time.Instant;
import java.util.ArrayList;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public class ServerBeatReceiver extends Thread implements Runnable{

    Server server;
    ArrayList<Connection> connections = new ArrayList<>();

    int expectedCardiacRhythm = 20; //seconds


    public ServerBeatReceiver(Server server) {
        this.server=server;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(expectedCardiacRhythm*1000/5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkAlives();
        }
    }

    public synchronized void removeBody(Connection connection){

        String toDelete = connection.serverThread.toString();
        System.out.println("deleting " + toDelete + " with last beat at " + connection.inst);
        //connection.serverThread.getView().dropConnection();
        //server.getClients().remove(connection.serverThread);

        //connection.serverThread.setKeepAlive(false);
        //connections.remove(connection);
        System.out.println(toDelete + " deleted");

    }

    public synchronized void checkAlives(){
        long refInst = Instant.now().getLong(INSTANT_SECONDS);
        if (connections.size()>0)
            for (Connection connection: connections
            ) {
                if (refInst-connection.inst>expectedCardiacRhythm)
                    removeBody(connection);
            }
    }

    public synchronized void receiveBeat(ServerThread serverThread){
        System.out.println("Beat from: " + serverThread + "at" + Instant.now().getLong(INSTANT_SECONDS));
        for (Connection connection: connections
        ) {
            if (connection.serverThread==serverThread) {
                connection.inst = Instant.now().getLong(INSTANT_SECONDS);
                return;
            }
        }

        connections.add(new Connection(serverThread, Instant.now().getLong(INSTANT_SECONDS)));
        System.out.println("Connection added!");
    }
}