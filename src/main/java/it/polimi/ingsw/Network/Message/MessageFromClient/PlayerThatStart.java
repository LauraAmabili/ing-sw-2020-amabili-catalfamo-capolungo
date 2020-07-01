package it.polimi.ingsw.Network.Message.MessageFromClient;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Server.VisitorServer;

import java.io.IOException;
import java.util.List;

public class PlayerThatStart extends MessageFromClient{



    String player;
    List<PlayerInterface> onlinePlayers;

    public PlayerThatStart(String player, List<PlayerInterface> onlinePlayers) {
        this.player = player;
        this.onlinePlayers = onlinePlayers;
    }

    public String getPlayer() {
        return player;
    }

    public List<PlayerInterface> getOnlinePlayers() {
        return onlinePlayers;
    }


    /**
     * Calls the correspondent visit method based on the type of Message
     * @param gameMessageVisitorClient gameMessage to be checked
     * @throws IOException Exception
     */
    @Override
    public void accept(VisitorServer gameMessageVisitorClient) throws IOException, InterruptedException {
        gameMessageVisitorClient.visit(this);
    }
}
