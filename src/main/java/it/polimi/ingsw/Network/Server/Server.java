package it.polimi.ingsw.Network.Server;

import com.google.gson.JsonObject;
import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Network.Message.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;


public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        Socket s = null;
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        while (true){
            s = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
            System.out.println("Connection from " + s + "!");
            ServerThread st=new ServerThread(s);
            st.start();
        }


    }
}
