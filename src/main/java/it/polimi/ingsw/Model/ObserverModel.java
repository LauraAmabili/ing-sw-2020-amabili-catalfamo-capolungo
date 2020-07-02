package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.List;

public interface ObserverModel {


    /**
     * Sends a Message to the client that the nickname is accepted
     * @param obj nickname chosen by the client
     */
    void updatePlayerAdded(String obj, String color) ;

    /**
     * Starts when there are enough clients for the number of players chosen
     *
     */
    void updateGameisReady() throws InterruptedException;
    /**
     * Sends to all the clients the name of the Player and the God that the player decided to take
     * @param player name of the current player
     * @param godName name of the god chosen by the player
     */
    void updateGodSet(PlayerInterface player, String godName) ;

    /**
     * Sends the message with the Board updated
     * @param board board to be send
     */
    void updateBoard(Board board) ;
    /**
     * Sends to all the clients the Message that it's time for the Challenger to set the cards
     * @param name name of the challenger
     */
    void updateTimeToChoose(String name, String color) ;

    /**
     * sends the name of the chosenGods when the challenger is finished choosing
     * @param gods list of chosenGods
     * @param cardChosen false if there are more cards to be chosen
     * @param challengerName name of the challenger
     *
     */
    void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName) ;

    /**
     * Sends an update to all the clients with the winner
     * @param player name of the winning player
     */
    void updateWinners(PlayerInterface player) ;

    /**
     * Calls the request for the moving
     * @param worker number of the worker
     * @param current name of the current player
     */
    void updateMoving(int worker, String current,  List<BoardCell> available) ;

    /**
     * Sends to the current player the message that the coordinates are not correct
     * @param worker number of the worker
     * @param current name of the current player
     *
     */
    void updateBuilding(int worker, String current,  List<BoardCell> available) ;

    /**
     * This send a message if the coordinates for the Worker are wrong
     * @param i number of worker
     * @param currentPlayer player current playing
     */
    void updateSetWorker(int i, String currentPlayer) ;
    /**
     * Send to the client/current player an update that the name of the God chosen is not correct
     * @param nickname name of the current player
     * @param availableGods name of the chosen God
     *
     */
    void updateCardNotPresent(String nickname, List<String> availableGods , List<God> chosenGods) ;
    /**
     * Sends an update if the god chosen by the challenger is not correct
     * @param challengerName challenger name
     *
     */
    void updateGodNotAdded(String challengerName) ;
    /**
     * sends the client/challenger the positive update that the Card chosen is added correctly sending the names of the chosenGods
     * @param chosenGods name of the chosenGod
     * @param Names names of the gods chosen until now
     * @param ChallengerName name of the challenger
     */
    void updateChoose(boolean chosenGods, List<God> Names, String ChallengerName) ;
    /**
     * Sends the client this message if the nickname is not Valid
     * @param nickname nickname not accepted by the server
     */
    void updateNicknameNotValid(String nickname) ;
    /**
     * Sends a message to all the players if someone win
     * @param playerName name of the player current playing
     */
    void updatePlayerHasLost(String playerName) ;
    /**
     * Sends to all the client that it's time for the current player to choose the Worker because the player can move
     * @param nickname nickname of the current player
     */
    void updateDecideWorker(PlayerInterface nickname) ;
    /**
     * Sends to all clients an update about the Time of the game, now it's time to set the Card for each player
     * @param chosenGods list of gods chosen by the challenger
     * @param currentPlayerName name of the current player
     */
    void updateTimeToSetCard(List<String> chosenGods, PlayerInterface currentPlayerName) ;
    /**
     * Sends to all the client the update about the time of the game, it's time to place the first workers
     * @param currentPlayerName name of the current Player
     *
     */
    void updateTimeToPlaceWorker(String currentPlayerName) ;

    /**
     * Sends a Message if the worker is not correct, we choose automatically the other one
     * @param worker number of the worker
     * @param current name of the current player
     */
    void updateWorkerSelected(boolean hasTwoInput, boolean effect, int worker, String current, List<BoardCell> available) ;

    /**
     * Sends a message if the coordinates are not valid and calls the moving again
     * @param worker number of the worker that cannot move
     * @param current name of the current player
     *
     */
    void updateNoCoordinatesValid(int worker, String current, List<BoardCell> available) ;
    /**
     * Send to all the clients an update that it's time to build
     * @param worker number of the worker
     * @param current name of the current player
     */
    void updateTimeToBuild(int worker, String current, List<BoardCell> av) ;
    /**
     * Sends the board update with the position of the worker just added and calls the request to set the second worker
     * @param board sends a copy of the board to print
     * @param currentPlayer name of the current Player
     *
     */
    void updateBoardAddedWorker(Board board, String currentPlayer);
    /**
     * Sends the client of che currentPlayer the option to set the God for the Game
     * @param chosenGods list of gods chosen by the challenger
     * @param currentPlayer name of the current player
     */
    void updateSetCard(List<String> availableGods, String currentPlayer, List<God> chosenGods) ;
    /**
     * Calling the update about the time of the game, it's time to move
     * @param current current player playing
     *
     */
    void updateStartMoving(String current) ;
    /**
     * called by notifyChooseWorker
     * called when it's time for the player to decide the worker
     * @param current name of the current player
     */
    void updateTimeToChooseWorker(String current) ;
    /**
     * sends a message to the client if the player has a card that needs the effect to be activated
     * @param currentPlayer name of the current player
     */
    void updateAskForEffect(String currentPlayer) ;

    /**
     * sends to the player a message if the player has a card that needs to build differently
     * @param currentPlayer name of the current player
     * @param worker worker that will build
     */
    void updateAskForEffectBuild(String currentPlayer, int worker) ;
    /**
     * Sends a message to the player with the card that needs to build twice
     * @param currentPlayer name of the current player
     * @param worker number of the worker
     */
    void updateBuildTwoInput(String currentPlayer,int worker) ;
    /**
     * Sends a message to the player with the card that needs to move twice
     * @param nickname  name of the current player
     * @param worker number of the worker
     */
    void updateMoveTwoInput(String nickname, int worker) ;
    /**
     * Sends the request for choosing tha name of the first player to choose the cards
     * @param nickname nickname of the current player
     * @param onlinePlayers list of players
     */
    void updateSetFirstPlayer(String nickname, List<PlayerInterface> onlinePlayers) ;
    /**
     * Sends to the client a new request for the coordinates for the build twice
     * @param worker number of the worker
     * @param nickname nickname of the player
     */
    void updateNoCoordinatesValidBuildTwoInput(int worker, String nickname) ;
    /**
     * Sends to the client a new request for the coordinates for the move twice
     * @param worker number of the worker
     * @param nickname nickname of the player
     */
    void updateNoCoordinatesValidMoveTwoInput(int worker, String nickname) ;

    void updateServerRestart() ;


}

