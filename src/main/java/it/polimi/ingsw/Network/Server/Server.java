

package it.polimi.ingsw.Network.Server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.Controller.GameController;

import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;


public class Server {

    private int port;
    private final Gson gson = new Gson();
    private final String file = "Configurations/serverConf.json";
    private final ArrayList<ServerThread> serverThreads = new ArrayList<>();
    private boolean keepAlive = true;
    public ServerBeatReceiver serverBeatReceiver;
    private GameController gameController = new GameController();
    public Server() {
        read();
    }
    public List<ServerThread> getServerThreads() {
        return serverThreads;
    }
    public GameController getGameController() {
        return gameController;
    }
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Server server = new Server();
        server.startServer();
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    /**
     * Create the thread that manages the connection
     * Listen for new connections from clients
     *
     * @throws IOException exception
     * @throws ClassNotFoundException exception
     */
    public void startServer() throws IOException, ClassNotFoundException {

        serverBeatReceiver = new ServerBeatReceiver(this);
        new Thread(serverBeatReceiver).start();
        connectClients();

    }

    /**
     * Clients can be connected
     * @throws IOException
     */
    public void connectClients() throws IOException {

        Socket s;
        ServerSocket ss = new ServerSocket(port);
        boolean check = false;
        while (keepAlive) {
            s = ss.accept();
            System.out.println("Connection from " + s + "!");
            ServerThread st;
            if(serverThreads.size() == 0) {
                st = new ServerThread(s, this, 2, false);
            } else {
                for (ServerThread serverThread : serverThreads) {
                    if (serverThread.isMaxPlayerNumberSet()) {
                        check = true;
                        break;
                    }
                }
                if(check) {
                    st = new ServerThread(s, this, serverThreads.get(0).getNumPlayers(), true);
                } else {
                    st = new ServerThread(s, this, serverThreads.get(0).getNumPlayers(), false);
                }
            }
            st.start();
        }
    }


    /**
     * Read the configuration from the server
     */

    public void read() {
        //FileReader fileReader = null;
        BufferedReader fileReader = null;
        //try {
        //fileReader =  new FileReader("serverConf.json");
        fileReader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("Configurations/serverConf.json"))));
           /* //fileReader = new FileReader(new File((Objects.requireNonNull(getClass().getClassLoader().getResource("Configurations/serverConf.json"))).getFile()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            */
        Type userListType = new TypeToken<Integer>() {
        }.getType();
        port = gson.fromJson(fileReader, userListType);
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}