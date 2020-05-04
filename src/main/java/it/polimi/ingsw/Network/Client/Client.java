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
    private static Socket socket;


    // get the input stream from the connected socket
    private InputStream is;

    // create a DataInputStream so we can read data from it.
    private ObjectInputStream ois;

    // get the output stream from the socket.
    private OutputStream os;

    // create an object output stream from the output stream so we can send an object through it
    private ObjectOutputStream oos;

    public Client() {
        read();
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected!");
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

    public static List<Message> receiveListFromServer(){

        // read the list of messages from the socket
        List<Message> listOfMessages = null;
        try {

            listOfMessages = (List<Message>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listOfMessages;
    }

    public Message receiveMessageFromServer(){
        // read the message from the socket
        Message message= null;
        try {

            message = (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }


    public static void sendToServer(List<Message> listOfMessages){
        try {
            oos.writeObject(listOfMessages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendToServer(Message message){
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
        List<Message> greetings = new ArrayList<>();
        greetings.add(new Greetings("Walter", "1", 0));
        greetings.add(new Greetings("Walter", "2", 0));
        greetings.add(new Greetings("Walter", "3", 0));
        greetings.add(new Greetings("Walter", "4", 0));
        sendToServer(greetings);

        List<Message> listOfMessages = receiveListFromServer();




        System.out.println("Closing socket and terminating program.");
        socket.close();
    }


}
