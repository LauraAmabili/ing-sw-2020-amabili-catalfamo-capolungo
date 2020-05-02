package it.polimi.ingsw.Controller;

public interface Observer {


    public void updateInitialiseMatch(int numberOfPlayers);
    public void updateNickname(String in);
    public void updateChoosingCards();
    public void updateSetGodName(String name);
    public void updateAddingWorker(int row, int col, int i );
    public void updateStartMoving();
    public void updateBuilding(int row, int col, int i);
    public void updateTryThisWorker(int worker);
    public void updateMoving(int row, int col, int worker);
    public void updateTryThisCard(String in);

}