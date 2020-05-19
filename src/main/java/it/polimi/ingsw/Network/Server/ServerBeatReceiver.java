
package it.polimi.ingsw.Network.Server;

import java.time.Instant;
import java.util.ArrayList;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public class ServerBeatReceiver extends Thread implements Runnable {

    Server server;
    ArrayList<Connection> connections = new ArrayList<>();

    int expectedCardiacRhythm = 10; //seconds

    /**
     * Receive the beats from the clients and delete dead clients
     *
     * @param server
     */
    public ServerBeatReceiver(Server server) {
        this.server = server;
    }

    /**
     * loop:
     * sleep
     * check who is still out there
     */
    @Override
    public void run() {
        while (true) {
            try {
                sleep(expectedCardiacRhythm * 1000 / 5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkAlives();
        }
    }


    /**
     * Get the actual instant of time
     * Compare the last message of all clients with the actual instant of time
     * If the last message is too old, the client is dead
     */
    public synchronized void checkAlives() {
        long refLastBeatInstant = Instant.now().getLong(INSTANT_SECONDS);
        if (connections.size() != 0) {
            for (int i = 0; i < connections.size(); i++) {
                if (refLastBeatInstant - connections.get(i).lastBeatInstant > expectedCardiacRhythm)
                    removeBody(connections.get(i));
            }
        }

    }


    /**
     * Someone was found dead
     * Delete that connection and all its footprints from the entire structure
     *
     * @param connection of the dead
     */
    public synchronized void removeBody(Connection connection) {

        String toDelete = connection.serverThread.toString();
        System.out.println("deleting " + toDelete + " with last beat at " + connection.lastBeatInstant);
        /*TODO TODO TODO
        connection.serverThread.getView().dropConnection();
        */
        server.getServerThreads().remove(connection.serverThread);
        connection.serverThread.setKeepAlive(false);
        connections.remove(connection);
        System.out.println(toDelete + " deleted");
    }

    /**
     * Method called from visitor
     * Receive a beat from a client and save it
     * The beat instant is saved with the serverThread of the beat
     *
     * @param serverThread
     */
    public synchronized void receiveBeat(ServerThread serverThread) {
        System.out.println("Beat from: " + serverThread + "at" + Instant.now().getLong(INSTANT_SECONDS));
        for (int i = 0; i < connections.size(); i++) {
            if (connections.get(i).serverThread == serverThread) {
                connections.get(i).lastBeatInstant = Instant.now().getLong(INSTANT_SECONDS);
                return;
            }
        }

        connections.add(new Connection(serverThread, Instant.now().getLong(INSTANT_SECONDS)));
        System.out.println("Connection added!");
    }
}