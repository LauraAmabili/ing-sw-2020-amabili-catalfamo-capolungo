package it.polimi.ingsw.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.CLIApp;
import it.polimi.ingsw.Network.Client.CLI;
import it.polimi.ingsw.Network.Client.UserInterface;
import it.polimi.ingsw.Network.Message.MessageFromClient.MessageFromClient;
import it.polimi.ingsw.Network.Message.MessageFromServer.MessageFromServer;
import it.polimi.ingsw.View.GUI.GUI_App;


import java.io.*;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Objects;
import java.util.Scanner;

public class ClientApp {


    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        if (args.length > 0 && args[0].equalsIgnoreCase("-cli")) {
            CLIApp cli = new CLIApp();
            CLIApp.main(args);
        } else {
            GUI_App gui = new GUI_App();
            GUI_App.main(args);
        }


    }

}
