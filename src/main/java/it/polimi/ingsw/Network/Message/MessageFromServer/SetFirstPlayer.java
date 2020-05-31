package it.polimi.ingsw.Network.Message.MessageFromServer;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Client.VisitorClient;

import java.io.IOException;
import java.util.List;

public class SetFirstPlayer extends MessageFromServer {



    List<PlayerInterface> onlinePlayers;

    public SetFirstPlayer(List<PlayerInterface> onlinePlayers) {
        this.onlinePlayers = onlinePlayers;
    }

    public List<PlayerInterface> getOnlinePlayers() {
        return onlinePlayers;
    }

    @Override
    public void accept(VisitorClient gameMessageVisitorClient) throws IOException {
            gameMessageVisitorClient.visit(this);
    }
}
