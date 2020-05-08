package it.polimi.ingsw;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Network.Server.Server;
import it.polimi.ingsw.View.VirtualView;
import it.polimi.ingsw.View.View;

public class ClientApp {

    public static void main(String[] args ) {


        GameController controller = new GameController();
        Server server = new Server();
    }

}
