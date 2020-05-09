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
    int numPlayers = 2;


    private ObjectOutputStream out;
    private ObjectInputStream in;


    private boolean ready = false;

    public VirtualView getView() {
        return view;
    }

    public ServerThread(Socket socket, Server server) throws IOException {
        this.socket = socket;
        this.server = server;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
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
        if (server.getClients().size() == 1) {
            try {
                sendToClient(new NumberOfPlayersRequest());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else  {
            if (server.getClients().size() > numPlayers) {
                server.getClients().remove(server.getClients().size() - 1);
                try {
                    sendToClient(new MaxPlayerReach());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            } else {
                try {
                    sendToClient(new NicknameRequest());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

    }

    public void sendToClient(MessageToClient x) throws IOException {
        out.writeObject(x);
    }

}