
package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromClient.BeatUpdate;

import java.io.IOException;

public class ClientBeatSender extends Thread {

    final Client client;

    final int cardiacRhythm = 5; //seconds

    public ClientBeatSender(Client client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {

            try {
                client.send(new BeatUpdate());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sleep(cardiacRhythm * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
