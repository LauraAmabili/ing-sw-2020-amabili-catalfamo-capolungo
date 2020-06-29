package it.polimi.ingsw.Controller;

import java.io.IOException;

public interface Observer {


    /**
     * calls the method on game to create the match with the number of players chosen and create the turn
     *
     * @param numberOfPlayers number of player decided by the first client
     */
    void updateInitialiseMatch(int numberOfPlayers) throws IOException, InterruptedException;
    /**
     * checks the player with the correct state and set the nickname chosen by the client
     *
     * @param in chosen nickname
     * @throws IOException Exception
     */
    void updateNickname(String in) throws IOException;
    /**
     * Calls the method to choose the cards by the challenger
     *
     * @throws IOException Exception
     */
    void updateChoosingCards() throws IOException;
    /**
     * Calls the method if the player has the correct state to set the card chosen
     *
     * @param name name of the god chosen by the current player
     * @throws IOException Exception
     */
    void updateSetGodName(String name) throws IOException;
    /**
     * calls the method with the coordinates for the worker to set
     *
     * @param row    chosen row
     * @param col    chosen col
     * @param i worker to be set
     * @throws IOException Exception
     */
    void updateAddingWorker(int row, int col, int i) throws IOException;
    /**
     * After checking if the player is in the correct state, this check if the current player can move
     *
     * @throws IOException Exception
     */
    void updateStartMoving() throws IOException;
    /**
     * After checking if the player is in the correct state, calling the build with the coordinates chosen
     *
     * @param row    chosen row
     * @param col    chosen col
     * @param i worker just moved
     * @throws IOException Exception
     */
    void updateBuilding(int row, int col, int i) throws IOException;
    /**
     * check the current player that is in the correct state and checks if the chosen worker can move
     *
     * @param worker number of the chosen worker
     * @throws IOException exception for the message
     */
    void updateTryThisWorker(int worker) throws IOException;
    /**
     * checks if the current player is in the correct state and calls the move on the coordinates given
     *
     * @param row    chosen row
     * @param col    chosen col
     * @param worker numbero of the worker to move
     * @throws IOException exception for the message
     */
    void updateMoving(int row, int col, int worker) throws IOException;
    /**
     * If the current player is choosing the cards calls the method to add the chosencard to the list
     *
     * @param in name of the chosen card
     * @throws IOException Exception
     */
    void updateTryThisCard(String in) throws IOException;
    /**
     * calls the starting game in the model
     *
     * @throws IOException          exception for the message
     * @throws InterruptedException exception
     */
    void updateStartingGame() throws IOException, InterruptedException;
    /**
     * This method check if the player of the worker involved has the effect true
     *
     * @param effect effect boolean to set true if the client wants to use the effect
     * @param worker worker to apply the effect to
     * @throws IOException Exception
     */
    void updateTryThisWorkerEffect(boolean effect, int worker) throws IOException;
    /**
     * check if the current player in the correct state has the effect flag true
     *
     * @param effect   effect to check
     * @param nickname name of the current player
     * @param worker   worker to build around
     * @throws IOException Exception
     */
    void updatePlayerBuild(boolean effect, String nickname, int worker) throws IOException;
    /**
     * check if the current player is the correct state and then move with the coordinates
     *
     * @param row1   first chosen row
     * @param col1   first chosen col
     * @param row2   second chosen row
     * @param col2   second chosen col
     * @param worker worker to move twice
     * @throws IOException exception for the message
     */
    void updateTimeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException;
    /**
     * check if the current player is in the correct state and then build in the coordinates given
     *
     * @param row1   first chosen row
     * @param col1   first chosen col
     * @param row2   second chosen row
     * @param col2   second chosen col
     * @param worker worker that should build, but, you know, is just a game
     * @throws IOException exception for the message
     */
    void updateTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException;
    /**
     * When the player lose his connection this delete his information in the game and sends a message to all the observers
     *
     * @param nickname name of the player that drop his connection
     */
    void updateDropConnection(String nickname) throws IOException;
    /**
     * Sends to the model the name of the first player chosen to start the game
     * @param player name of the player
     * @throws IOException Exception
     */
    void updateFirstPlayer(int player) throws IOException;
}
