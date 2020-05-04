package it.polimi.ingsw.Network.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Network.Message.Greetings;
import it.polimi.ingsw.Network.Message.Message;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private static int port;
    private static Gson gson = new Gson();
    private static String file = "./src/main/java/it/polimi/ingsw/resources/serverConf.json";

    public Client() {
        read();
    }

    public static void main(String[] args) throws IOException {
        new Client();


        // need host and port
        Socket socket = new Socket("localhost", port);
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

    public static void read() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Type userListType = new TypeToken<Integer>(){}.getType();
        assert fileReader != null;
        port = gson.fromJson(fileReader, userListType);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
