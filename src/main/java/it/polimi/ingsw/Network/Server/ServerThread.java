package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.MessageFromClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MaxPlayerReachedUpdate;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageFromServer;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;
import it.polimi.ingsw.Network.Message.MessageFromServer.PlayerNumberRequest;
import it.polimi.ingsw.View.VirtualView;


import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread implements Runnable {

    private VirtualView view = new VirtualView(this);
    Socket socket;
    Server server;
    private VisitorServer visitor = new VisitorMethodsServer(view, this);
    boolean maxPlrSet = false;
    int numPlayers = 2;
    int numOnline = 0;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private volatile boolean keepAlive = true;
    private boolean ready = false;


    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public VirtualView getView() {
        return view;
    }

    public ServerThread(Socket socket, Server server, int numPlayers, boolean maxPlrSet) throws IOException {
        this.socket = socket;
        this.server = server;
        this.numPlayers = numPlayers;
        this.maxPlrSet = maxPlrSet;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public boolean isMaxPlrSet() {
        return maxPlrSet;
    }

    public void setMaxPlrMsg(boolean maxPlrSet) {
        this.maxPlrSet = maxPlrSet;
    }

    public Server getServer() {
        return server;
    }

    public int getNumOnline() {
        return numOnline;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.getServerThreads().add(this);
        //numOnline = server.getClients().size();
        if (server.getServerThreads().size() == 1) {
            try {
                sendToClient(new PlayerNumberRequest());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (isMaxPlrSet() && server.getServerThreads().size() == numPlayers) {
                for (int i = 0; i < server.getServerThreads().size(); i++) {
                    try {
                        server.getServerThreads().get(i).sendToClient(new NicknameRequest());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (server.getServerThreads().size() > numPlayers) {
                server.getServerThreads().remove(server.getServerThreads().size() - 1);
                try {
                    sendToClient(new MaxPlayerReachedUpdate());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        view.AddObserver(server.getGameController());
        server.getGameController().getGame().AddObserver(view);
        while (keepAlive) {
            try {
                MessageFromClient message = ((MessageFromClient) in.readObject());
                System.out.println("Message received");
                message.accept(visitor);
            } catch (IOException e) {
                return;
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void sendToClient(MessageFromServer x) throws IOException {
        out.reset();
        out.writeObject(x);
        out.flush();
    }

}