
package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromClient.BeatUpdate;


public class ClientBeatSender extends Thread {

    final Client client;

    final int cardiacRhythm = 5; //seconds

    public volatile boolean active = true;

    public ClientBeatSender(Client client) {
        this.client = client;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Send a message to the server each cardiacRhythm seconds to notify that this client is alive
     */
    @Override
    public void run() {
        while (active) {
            client.send(new BeatUpdate());
            try {
                sleep(cardiacRhythm * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
