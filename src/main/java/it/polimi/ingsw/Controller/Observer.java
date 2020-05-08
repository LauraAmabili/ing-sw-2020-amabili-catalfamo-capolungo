package it.polimi.ingsw.Controller;

import java.io.IOException;

public interface Observer {


    public void updateInitialiseMatch(int numberOfPlayers) throws IOException;
    public void updateNickname(String in) throws IOException;
    public void updateChoosingCards() throws IOException;
    public void updateSetGodName(String name) throws IOException;
    public void updateAddingWorker(int row, int col, int i );
    public void updateStartMoving();
    public void updateBuilding(int row, int col, int i);
    public void updateTryThisWorker(int worker);
    public void updateMoving(int row, int col, int worker);
    public void updateTryThisCard(String in) throws IOException;
}
