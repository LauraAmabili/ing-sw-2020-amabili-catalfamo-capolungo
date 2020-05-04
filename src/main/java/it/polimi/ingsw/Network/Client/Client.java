package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.Greetings;
import it.polimi.ingsw.Network.Message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream os = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream oos = new ObjectOutputStream(os);

        // make a bunch of messages to send.
        List<Message> greetings = new ArrayList<>();

        greetings.add(new Greetings("Walter", "1", 0));
        greetings.add(new Greetings("Walter", "2", 0));
        greetings.add(new Greetings("Walter", "3", 0));
        greetings.add(new Greetings("Walter", "4", 0));


        System.out.println("Sending messages to the ServerSocket");

        oos.writeObject(greetings);

        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}
