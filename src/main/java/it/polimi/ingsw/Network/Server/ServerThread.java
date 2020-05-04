package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.Message;

import java.io.*;
import java.net.Socket;
import java.util.List;

class ServerThread extends Thread implements Runnable{

    String line=null;
    BufferedReader is = null;
    PrintWriter os=null;
    Socket s=null;

    public ServerThread(Socket s){
        this.s=s;
    }

    public void run() {
        // get the input stream from the connected socket
        InputStream is = null;
        try {
            is = s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // create a DataInputStream so we can read data from it.
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read the list of messages from the socket
        List<Message> listOfMessages = null;
        try {
            listOfMessages = (List<Message>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println("Received [" + listOfMessages.size() + "] messages from: " + s);
        // print out the text of every message
        System.out.println("All messages:");
        listOfMessages.forEach((msg) -> System.out.println(msg.toString()));

        System.out.println("Closing sockets.");
    }
}
