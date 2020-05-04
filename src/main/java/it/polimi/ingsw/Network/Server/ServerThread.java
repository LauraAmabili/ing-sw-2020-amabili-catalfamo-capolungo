package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.Greetings;
import it.polimi.ingsw.Network.Message.Message;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ServerThread extends Thread implements Runnable{

    Socket s=null;

    // get the input stream from the connected socket
    InputStream is;

    // create a DataInputStream so we can read data from it.
    ObjectInputStream ois;

    // get the output stream from the socket.
    OutputStream os;

    // create an object output stream from the output stream so we can send an object through it
    ObjectOutputStream oos;



    public ServerThread(Socket s){
        this.s=s;

        try {
            is = s.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ois = new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os = s.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public List<Message> receiveListFromClient(){
        // read the list of messages from the socket
        List<Message> listOfMessages = null;
        try {

            listOfMessages = (List<Message>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return listOfMessages;
    }

    public Message receiveMessageFromClient(){
        // read the message from the socket
        Message message= null;
        try {

            message = (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }



    public void sendToClient(List<Message> listOfMessages){
        try {
            oos.writeObject(listOfMessages);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendToClient(Message message){
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void run() {

        List<Message> listOfMessages = receiveListFromClient();
        System.out.println(listOfMessages);

        List<Message> greetings = new ArrayList<>();
        greetings.add(new Greetings("Walter", "1", 0));
        greetings.add(new Greetings("Walter", "2", 0));
        greetings.add(new Greetings("Walter", "3", 0));
        greetings.add(new Greetings("Walter", "4", 0));
        sendToClient(greetings);

        System.out.println("Closing sockets.");
    }


}
