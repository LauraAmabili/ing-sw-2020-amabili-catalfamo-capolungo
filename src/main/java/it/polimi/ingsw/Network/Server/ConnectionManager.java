package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromServer.PingRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

public class ConnectionManager implements Runnable {
    private Server server;
    private ArrayList<Connection> connections = new ArrayList<>();
    private final static int timeToSleep = 10; //seconds

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public ConnectionManager(Server server) {
        this.server = server;
        for (ServerThread x : server.getClients()
        ) {
            connections.add(new Connection(x, false));
        }
    }

    @Override
    public void run() {

        while (true) {
            initialise();
            knock();
            try {
                sleep(1000*timeToSleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clean();

        }
    }

    public void clean(){
        for (Connection x : connections) {
            if (!x.isPingReceived())
                removeDead(x);
        }
    }

    public void removeDead(Connection x){
        /*
        @// TODO: 5/10/2020
                //s.getView().del player
                //delete from server
                //delete from this
            */
        x.getServerThread().interrupt();
    }



    public void initialise(){
        for (Connection x : connections) {
            x.setPingReceived(false);
        }
    }

    public void knock(){

        Random rand = new Random();
        for (Connection x : connections) {
            try {
                int r = rand.nextInt(100000000);
                x.getServerThread().sendToClient(new PingRequest(r));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void receivePing(int n) {
        for (Connection x: connections
        ) {
            if (x.getLastPing()==n)
                x.setPingReceived(true);
        }
    }


}
