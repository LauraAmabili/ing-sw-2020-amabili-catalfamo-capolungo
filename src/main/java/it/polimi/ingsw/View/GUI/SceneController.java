package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;

public abstract class SceneController extends NotifyMessages  {

    Client client;

    public Client getClient() {
        return client;
    }

    public SceneController(Client client) {
        this.client = client;
    }



}
