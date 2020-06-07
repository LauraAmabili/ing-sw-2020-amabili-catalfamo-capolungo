
package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Network.Message.MessageFromServer.DroppedConnection;
import it.polimi.ingsw.Network.Message.MessageFromServer.PlayerNumberRequest;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public class ServerBeatReceiver extends Thread implements Runnable {

    final Server server;
    final ArrayList<Connection> connections = new ArrayList<>();

    final int expectedCardiacRhythm = 10; //seconds

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
                sleep(expectedCardiacRhythm * 1000);
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
        } else {
            server.setGameController(new GameController());
        }

    }


    /**
     * Someone was found dead
     * Delete that connection and all its footprints from the entire structure
     *
     * @param connection of the dead
     */
    public synchronized void removeBody(Connection connection) {
        String nickname = connection.serverThread.getView().getNickname();
        String toDelete = connection.serverThread.toString();
        System.out.println("deleting " + toDelete + " with last beat at " + connection.lastBeatInstant);
        connection.serverThread.getServer().getGameController().getGame().RemoveObserver(connection.serverThread.getView());
        try {
            connection.serverThread.getView().dropConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.getServerThreads().remove(connection.serverThread);
        connection.serverThread.RemoveObserver(connection.serverThread.getView());
        connection.serverThread.setKeepAlive(false);
        connections.remove(connection);
        if (!connection.serverThread.isMaxPlayerNumberSet() && connections.size()>0) {
            try {
                connections.get(0).serverThread.sendToClient(new PlayerNumberRequest());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Connection i : connections){
            try {
                i.serverThread.sendToClient(new DroppedConnection(nickname));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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