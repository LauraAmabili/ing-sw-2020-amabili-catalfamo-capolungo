



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
        System.out.println(connection.serverThread + "is dead!!");
    }

    public synchronized void checkAlives(){
        long refInst = Instant.now().getLong(INSTANT_SECONDS);
        for (Connection connection: connections
        ) {
            if (refInst-connection.inst>expectedCardiacRhythm)
                removeBody(connection);
        }
    }

    public synchronized void receiveBeat(ServerThread serverThread){
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