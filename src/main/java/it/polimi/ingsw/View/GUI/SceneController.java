package it.polimi.ingsw.View.GUI;

import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.GUI;
import it.polimi.ingsw.Network.Client.NotifyMessages;
import it.polimi.ingsw.Network.Client.UpdatesForMessages;

public abstract class SceneController extends NotifyMessages  {



    UpdatesForMessages up;

    public void SceneBuilder(Client client) {
        up = new UpdatesForMessages(client);
        addObserver(up);
    }



}
