package it.polimi.ingsw;

import it.polimi.ingsw.Model.Observable;
import it.polimi.ingsw.Network.Client.CLI;
import it.polimi.ingsw.Network.Client.Client;
import it.polimi.ingsw.Network.Client.UserInterface;
import it.polimi.ingsw.View.VirtualView;

import java.io.IOException;

public class CLIApp {


    public static void main(String[] args) throws IOException {

        UserInterface UI = new CLI();
        Client client = new Client(UI);
        client.startClient();


    }



}
