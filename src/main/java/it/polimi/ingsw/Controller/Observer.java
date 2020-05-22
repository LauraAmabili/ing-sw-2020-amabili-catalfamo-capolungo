package it.polimi.ingsw.Controller;

import java.io.IOException;

public interface Observer {


    void updateInitialiseMatch(int numberOfPlayers) throws IOException, InterruptedException;
    void updateNickname(String in) throws IOException;
    void updateChoosingCards() throws IOException;
    void updateSetGodName(String name) throws IOException;
    void updateAddingWorker(int row, int col, int i) throws IOException;
    void updateStartMoving() throws IOException;
    void updateBuilding(int row, int col, int i) throws IOException;
    void updateTryThisWorker(int worker) throws IOException;
    void updateMoving(int row, int col, int worker) throws IOException;
    void updateTryThisCard(String in) throws IOException;
    void updateStartingGame() throws IOException, InterruptedException;
    void updateTryThisWorkerEffect(boolean effect, int worker) throws IOException;
    void updatePlayerBuild(boolean effect, String nickname, int worker) throws IOException;
    void updateTimeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException;
    void updateTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException;
    void updateDropConnection(String nickname);
}
