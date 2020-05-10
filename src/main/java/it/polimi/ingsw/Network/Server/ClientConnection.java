package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Controller.Observer;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageToClient;

public interface ClientConnection {

    void closeConnection();
    void addObserver(Observer observer);
    void asyncSend(MessageToClient message);
}
