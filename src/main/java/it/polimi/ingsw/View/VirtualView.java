package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.*;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Server.ServerThread;

public class VirtualView extends Observable implements ObserverModel {


    private String MyNickname;
    private PlayerInterface currentPlayer = new Player();
    private final ServerThread thread;
    private final Scanner input = new Scanner(System.in);
    private final Scanner cases = new Scanner(System.in);
    private int numberOfPlayer = 0;

    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";

    public VirtualView(ServerThread thread) {
        this.thread = thread;
    }

    public String getNickname() {
        return MyNickname;
    }
    public void setNickname(String nickname) {
        this.MyNickname = nickname;
    }
    public PlayerInterface getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(PlayerInterface currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ServerThread getThread() {
        return thread;
    }

    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }
    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }


    //Preparing game

    /**
     * Set number of Players of the game. It is called only from the first client
     * @param number number of players chosen
     */
    public synchronized void notifyNumberOfPlayer(int number) throws InterruptedException {

            numberOfPlayer = number;
            notifyInitialiseMatch(number);

    }

    /**
     * Starts when there are enough clients for the number of players chosen
     *
     */
    @Override
    public void updateGameisReady()  {

        System.out.println(ANSI_BLUE);
            insertNickname();
            System.out.println(RESET);

    }



    //Adding player & nicknames


    /**
     * Sends a message to the client asking the nickname
     */
    public void insertNickname()  {


          thread.sendToClient(new NicknameRequest());



    }

    /**
     * Setting the name for the virtual view and for the player in the game
     * @param nickname name chosen by the client
     *
     */
    public synchronized void AddingNickname(String nickname)  {

        setNickname(nickname);
        notifyAddingNickname(nickname);

    }

    /**
     * Sends a Message to the client that the nickname is accepted
     * @param nickname nickname chosen by the client
     */
    @Override
    public void updatePlayerAdded(String nickname, String color) {

        if(nickname.equals(MyNickname)) {
            thread.sendToClient(new NicknameAcceptedUpdate(color, nickname));
        }

    }

    /**
     * Sends the client this message if the nickname is not Valid
     * @param nickname nickname not accepted by the server
     */
    @Override
    public void updateNicknameNotValid(String nickname) {

        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new NicknameNotValidUpdate());
        }
        MyNickname = null;

    }

    //Challenger + Setting chosenCards


    /**
     * Sends to all the clients the Message that it's time for the Challenger to set the cards
     * @param name name of the challenger
     */
    @Override
    public void updateTimeToChoose(String name, String color) {

        thread.sendToClient(new ChooseCardsUpdate(name, color));

    }

    /**
     * sends the client/challenger the positive update that the Card chosen is added correctly sending the names of the chosenGods
     * @param chosenGods name of the chosenGod
     * @param Names names of the gods chosen until now
     * @param ChallengerName name of the challenger
     */
    @Override
    public void updateChoose(boolean chosenGods, List<God> Names, String ChallengerName) {

        if(!chosenGods) {
            if(MyNickname.equals(ChallengerName)) {
                thread.sendToClient(new AvailableGodsUpdate(Names));
                chooseCard(ChallengerName);
            }
        }

    }

    /**
     * Sends the challenger that it's time to choose for the cards
     * @param challengerName name of the challenger
     */
    public void chooseCard(String challengerName) {

        if(MyNickname.equals(challengerName)) {
            thread.sendToClient(new ChallengerCardsRequest());
        }

    }

    /**
     * Sending a notify to the controller checking if the card chosen is correct
     * @param card name of the card chosen by the challenger
     *
     */
    public void tryThisCard(String card)  {

        notifyTryThisCard(card);

    }

    /**
     * sends the name of the chosenGods when the challenger is finished choosing
     * @param gods list of chosenGods
     * @param cardChosen false if there are more cards to be chosen
     * @param challengerName name of the challenger
     *
     */
    @Override
    public void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName)  {

        if (!cardChosen) {
            chooseCard(challengerName);
        } else {
            if (MyNickname.equals(challengerName)) {
                thread.sendToClient(new CardAddedUpdate(gods));
            }
        }
    }

    /**
     * Sends an update if the god chosen by the challenger is not correct
     * @param challengerName challenger name
     *
     */
    @Override
    public void updateGodNotAdded(String challengerName)  {

        if(MyNickname.equals(challengerName)) {
            thread.sendToClient(new CardChallengerNotFoundRequest());
            chooseCard(challengerName);
        }

    }


    //Setting personal God

    /**
     * Sends to all clients an update about the Time of the game, now it's time to set the Card for each player
     * @param chosenGods list of gods chosen by the challenger
     * @param currentPlayerName name of the current player
     */
    @Override
    public  void updateTimeToSetCard(List<String> chosenGods, PlayerInterface currentPlayerName) {

        thread.sendToClient(new SetCardTimeUpdate(currentPlayerName));

    }

    /**
     * Sends the client of che currentPlayer the option to set the God for the Game
     * @param chosenGods list of gods chosen by the challenger
     * @param currentPlayerName name of the current player
     */
    @Override
    public  void updateSetCard(List<String> availableGods, String currentPlayerName, List<God> chosenGods) {

        if(MyNickname.equals(currentPlayerName)) {
            System.out.println(chosenGods);
            thread.sendToClient(new SetYourCardRequest(availableGods, chosenGods));
        }

    }

    /**
     * sends a notify to the controller to check if the Name of the god chosen by the current player is correct
     * @param chosenGod name of the God chosen
     *
     */
    public synchronized void godNameChosen(String chosenGod)  {

        System.out.println(chosenGod);
        notifyGodNameChosen(chosenGod);
    }

    /**
     * Sends to all the clients the name of the Player and the God that the player decided to take
     * @param nickname name of the current player
     * @param godName name of the god chosen by the player
     */
    @Override
    public  void updateGodSet(PlayerInterface nickname, String godName) {

        thread.sendToClient(new CardSetUpdate(nickname, godName));

    }

    /**
     * Send to the client/current player an update that the name of the God chosen is not correct
     * @param nickname name of the current player
     * @param availableGods name of the chosen God
     *
     */
    @Override
    public void updateCardNotPresent(String nickname, List<String> availableGods, List<God> chosenGods)  {

        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new CardNotFoundRequest());
        }

        updateSetCard(availableGods, nickname, chosenGods);
    }



    //Placing worker


    /**
     * Sends to all the client the update about the time of the game, it's time to place the first workers
     * @param currentPlayerName name of the current Player
     *
     */
    @Override
    public void updateTimeToPlaceWorker(String currentPlayerName)  {

        thread.sendToClient(new StartingSetWorkerTimeUpdate(currentPlayerName));
        setWorkers1(currentPlayerName, 0);

    }

    /**
     * Sends to the client of the current player the request to set the first worker
     * @param currentPlayerName name of the current player
     * @param i number of the worker
     */
    public void setWorkers1(String currentPlayerName, int i) {

        if(MyNickname.equals(currentPlayerName)) {
            thread.sendToClient(new StartingSetWorkerRequest(i));
        }

    }

    /**
     * Sends the board update with the position of the worker just added and calls the request to set the second worker
     * @param board sends a copy of the board to print
     * @param currentPlayer name of the current Player
     *
     */
    @Override
    public void updateBoardAddedWorker(Board board, String currentPlayer)  {

        thread.sendToClient(new BoardUpdate(board));
        setWorkers2(currentPlayer, 1);

    }


    /**
     * sends to the client the request to add the second worker
     * @param currentPlayerName name of the current Player
     * @param i number of the worker
     */
    public void setWorkers2(String currentPlayerName, int i) {

        if(MyNickname.equals(currentPlayerName)) {
            thread.sendToClient(new StartingSetWorkerRequest(i));
        }

    }

    /**
     * check if the coordinates are correct, sends a message if they are not
     * @param row row chosen
     * @param col col chosen
     * @param i number of worker to be set
     *
     */
    public void toSetWorker(int row, int col, int i )  {

        if(row > 0 && row <= 5 && col > 0 && col <= 5) {
            notifyAddingWorker(row, col, i);
        } else {
            thread.sendToClient(new WrongCoordinatesUpdate(i));
        }
    }


    /**
     * This send a message if the coordinates for the Worker are wrong
     * @param i number of worker
     * @param currentPlayer player current playing
     */
    @Override
    public void updateSetWorker(int i, String currentPlayer) {

        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new WrongCoordinatesUpdate(i));
        }

    }


    //Input 6 to start your Turn


    /**
     * Calling the update about the time of the game, it's time to move
     * @param currentPlayer current player playing
     *
     */
    @Override
    public void updateStartMoving(String currentPlayer)  {

        if(MyNickname.equals(currentPlayer)) {
            notifyStartMoving();
        }

    }

    /**
     * Sends a message to all the players if someone win
     * @param playerNickname name of the player current playing
     * @param current
     */
    @Override
    public void updatePlayerHasLost(String playerNickname, String current) {

        thread.sendToClient(new PlayerLockedUpdate(playerNickname, current));


    }


    /**
     * Sends to all the client that it's time for the current player to choose the Worker because the player can move
     * @param nickname nickname of the current player
     */
    @Override
    public void updateDecideWorker(PlayerInterface nickname) {

         thread.sendToClient(new PlayerTurnUpdate(nickname));
    }


    /**
     * called by notifyChooseWorker
     * called when it's time for the player to decide the worker
     * @param nickname name of the current player
     */
    @Override
    public void updateTimeToChooseWorker(String nickname, Board board) {

        thread.sendToClient(new BoardUpdate(board));
        chooseWorker(nickname);

    }

    /**
     * sends a message to the client if the player has a card that needs the effect to be activated
     * @param nickname name of the current player
     */
    @Override
    public void updateAskForEffect(String nickname) {
        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new AskEffect());
        }
    }

    /**
     * sends to the player a message if the player has a card that needs to build differently
     * @param currentPlayer name of the current player
     * @param worker worker that will build
     */
    @Override
    public void updateAskForEffectBuild(String currentPlayer, int worker) {
        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new AskEffectBuild(worker));
        }
    }

    /**
     * sends to the controller the notify to check if the player with that worker cam build
     * @param effect to be set true or false
     * @param nickname name of the current player
     * @param worker worker that it's gonna build
     *
     */
    public void updatePlayerBuild(boolean effect, String nickname,int worker)  {
        notifyPlayerBuild(effect, nickname, worker);
    }

    /**
     * Sends the message to the client to choose the worker
     * @param nickname name of the current player
     */
    public void chooseWorker(String nickname) {


        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new ChooseYourWorkerRequest());
        }
    }


    /**
     * After be chosen, here we check if the worker can actually move
     * @param worker number of the worker
     *
     */
    public void tryThisWorker(int worker)  {

        notifyTryThisWorker(worker);

    }

    /**
     * Sends a Message if the worker is not correct, we choose automatically the other one
     * @param worker number of the worker
     * @param current name of the current player
     */
    @Override
    public void updateWorkerSelected(boolean hasTwoInput, boolean effect, int worker, String current,  List<BoardCell> available) {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new WrongWorkerUpdate(worker));
            if(hasTwoInput && effect) {
                updateMoveTwoInput(current, worker);
            } else {
                moving(worker, current, available);
            }
        }

    }

    /**
     * Calls the request for the moving
     * @param worker number of the worker
     * @param current name of the current player
     */
    @Override
    public void updateMoving(int worker, String current,  List<BoardCell> available) {

        moving(worker, current, available);

    }


    /**
     * Sends the message to the current player to ask for coordinates
     * @param worker number of the player
     * @param current name of the current player
     */
    public void moving(int worker, String current, List<BoardCell> available) {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new MoveRequest(worker, available));
        }
    }

    /**
     * This sends a notify to the controller to check if row and col are okay to be moved on
     * @param row chosen row
     * @param col chosen col
     * @param worker chosen worker
     *
     */
    public void tryMoving(int row, int col, int worker)  {

        notifyMoving(row, col, worker);

    }

    /**
     * Sends a message if the coordinates are not valid and calls the moving again
     * @param worker number of the worker that cannot move
     * @param current name of the current player
     *
     */
    @Override
    public void updateNoCoordinatesValid(int worker, String current,  List<BoardCell> available)  {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new TryNewCoordinatesRequest(worker));
        }
        //System.out.println("This coordinates are not valid, insert them again");
        moving(worker, current, available);

    }

    /**
     * Sends an update to all the clients with the winner
     * @param player name of the winning player
     */
    @Override
    public void updateWinners(PlayerInterface player) {

        thread.sendToClient(new WinMessage(player.getNickname()));

    }

    /**
     * Sends a message to the player with the card that needs to build twice
     * @param currentPlayer name of the current player
     * @param worker number of the worker
     */
    @Override
    public void updateBuildTwoInput(String currentPlayer,int worker) {
        thread.sendToClient(new BuildTimeUpdate(currentPlayer));
        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new BuildTwoInputRequest(worker));
        }
    }

    /**
     * Sends a message to the player with the card that needs to move twice
     * @param nickname  name of the current player
     * @param worker number of the worker
     */
    @Override
    public void updateMoveTwoInput(String nickname, int worker) {
        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new MoveTwoInputRequest(worker));
        }
    }


    /**
     * Sends the request for choosing tha name of the first player to choose the cards
     * @param nickname nickname of the current player
     * @param onlinePlayers list of players
     */
    @Override
    public void updateSetFirstPlayer(String nickname, List<PlayerInterface> onlinePlayers) {
        if(MyNickname.equals(nickname)){
            thread.sendToClient(new SetFirstPlayer(onlinePlayers));
        }
    }

    /**
     * Sends to the client a new request for the coordinates for the build twice
     * @param worker number of the worker
     * @param nickname nickname of the player
     */
    @Override
    public void updateNoCoordinatesValidBuildTwoInput(int worker, String nickname) {
        if(MyNickname.equals(nickname)){
            thread.sendToClient(new TryNewCoordinatesRequest(worker));
            thread.sendToClient(new BuildTwoInputRequest(worker));
        }
    }

    /**
     * Sends to the client a new request for the coordinates for the move twice
     * @param worker number of the worker
     * @param nickname nickname of the player
     */
    @Override
    public void updateNoCoordinatesValidMoveTwoInput(int worker, String nickname) {
        if(MyNickname.equals(nickname)){
            thread.sendToClient(new TryNewCoordinatesRequest(worker));
            thread.sendToClient(new MoveTwoInputRequest(worker));
        }
    }


    /**
     * Sends to the model the name of the first player
     * @param player player
     */
    public void setFirstPlayer(int player)  {
        notifyFirstPlayer(player);
    }

    /**
     * This sends a notify to the controller to check the coordinates
     * @param row1 row for the first move
     * @param col1 col for the first move
     * @param row2 row for the second move
     * @param col2 col for the second move
     * @param worker worker to be moved twice
     *
     */
    public void timeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) {

        notifyTimeToMoveTwoInput(row1, col1, row2, col2, worker);

    }

    /**
     * This sends a notify to the controller to check the coordinates
     * @param row1 row for the first build
     * @param col1 col for the first build
     * @param row2 row for the second build
     * @param col2 col for the second build
     * @param worker worker that will build twice
     *
     */
    public void timeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker)  {

        notifyTimeToBuildTwoInput(row1, col1, row2, col2, worker);

    }

    /**
     * Send to all the clients an update that it's time to build
     * @param worker number of the worker
     * @param current name of the current player
     */
    @Override
    public void updateTimeToBuild(int worker, String current, List<BoardCell> av) {

        thread.sendToClient(new BuildTimeUpdate(current));
        //System.out.println("It's now time to  build!");
        building(worker, current, av);

    }


    /**
     * send to the client of the current player the request to build
     * @param worker number of worker just moved
     * @param current name of the current player
     */
    public void building(int worker, String current, List<BoardCell> av) {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new BuildRequest(worker, av));

        }
    }

    /**
     * Sends to the controller the notify with the coordinates to check if they are correct
     * @param row row to be checked
     * @param col col to be checked
     * @param worker number of the worker
     *
     */
    public void tryToBuild(int row, int col, int worker)  {

        notifyBuilding(row, col, worker);

    }

    /**
     * Sends to the current player the message that the coordinates are not correct
     * @param worker number of the worker
     * @param current name of the current player
     *
     */
    @Override
    public void updateBuilding(int worker, String current, List<BoardCell> av)  {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new TryNewCoordinatesRequest(worker));
            //System.out.println("Try new coordinates: ");
            building(worker, current, av);
        }

    }


    /**
     * Sends the message with the Board updated
     * @param board board to be send
     */
    @Override
    public void updateBoard(Board board) {

        thread.sendToClient(new BoardUpdate(board));
    }


    /**
     * sends the message to the client for the request for choosing the worker when the player has the effect
     * @param effect boolean for the effect
     */
    public void updateTimeToChooseWorkerEffect(boolean effect) {
        thread.sendToClient(new ChooseYourWorkerEffectRequest(effect));
    }

    /**
     * sends a notify to the model with the information if the player wants to use the effect
     * @param effect effect
     * @param worker worker to add the effect on
     *
     */
    public void tryThisWorkerEffect(boolean effect, int worker)  {
        notifyTryThisWorkerEffect(effect, worker);
    }


    /**
     * Sends to the model the update that this client dropped the connection
     */
    public void dropConnection()  {
        notifyDropConnection(MyNickname);
    }

    /**
     *Sends the message that the server is restarting
     */
    public void updateServerRestart() {
        thread.sendToClient(new ServerRestart());
    }

}














