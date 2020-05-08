package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Network.Server.VisitorServer;

public class NumberOfPlayerResponse extends MessageToServer {


    int numberOfPlayers;

    public NumberOfPlayerResponse(String senderUsername, String token, int numberOfPlayers) {
        super(senderUsername, token);
        this.numberOfPlayers = numberOfPlayers;

    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void accept(VisitorServer gameMessageVisitorServer) {
        gameMessageVisitorServer.visit(this);

    }
}
