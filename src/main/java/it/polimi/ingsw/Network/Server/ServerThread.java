package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.MessageToServer;
import it.polimi.ingsw.Network.Message.MessageFromServer.MaxPlayerReach;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;
import it.polimi.ingsw.View.VirtualView;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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


    private boolean ready;

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

    public int getNumOnline() {
        return numOnline;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in  = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.getClients().add(this);
        //numOnline = server.getClients().size();
        if (server.getClients().size() == 1) {
            try {
                sendToClient(new NumberOfPlayersRequest("ALL"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if (isMaxPlrSet() && server.getClients().size() == numPlayers) {
                for(int i = 0; i < server.getClients().size(); i++) {
                    try {
                        server.getClients().get(i).sendToClient(new NicknameRequest("ALL"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (server.getClients().size() > numPlayers) {
                server.getClients().remove(server.getClients().size() - 1);
                try {
                    sendToClient(new MaxPlayerReach("ALL"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        view.AddObserver(server.getGameController());
        server.getGameController().getGame().AddObserver(view);
        while (true) {
            try {
                MessageToServer message = ((MessageToServer) in.readObject());
                System.out.println("Message received");
                message.accept(visitor);
            } catch (IOException e) {
                return;
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void sendToClient(MessageToClient x) throws IOException {
        out.writeObject(x);
    }

}