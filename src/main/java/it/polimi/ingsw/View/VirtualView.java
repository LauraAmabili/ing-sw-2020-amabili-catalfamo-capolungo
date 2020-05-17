package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Observable;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.*;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Server.ServerThread;

public class VirtualView extends Observable implements ObserverModel, Runnable {


    private String MyNickname;
    private PlayerInterface currentPlayer = new Player();
    private ServerThread thread;
    private Scanner input = new Scanner(System.in);
    private Scanner cases = new Scanner(System.in);
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
    public int getNumberOfPlayer() {
        return numberOfPlayer;
    }
    public void setNumberOfPlayer(int numberOfPlayer) {
        this.numberOfPlayer = numberOfPlayer;
    }

    /*
    @Override
    public void run() {
        start();
        while (true) {
            printComandi();
            String in = cases.nextLine();
            try {
                int integer = Integer.parseInt(in);
                menageInput(integer);
            } catch (NumberFormatException | IOException | InterruptedException exc) {
                try {
                    menageInput(0);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

     */

    //Preparing game

    /**
     * Set number of Players of the game. It is called only from the first client
     * @param number number of players chosen
     * @throws IOException
     * @throws InterruptedException
     */
    public synchronized void notifyNumberOfPlayer(int number) throws IOException, InterruptedException {

            numberOfPlayer = number;
            notifyInitialiseMatch(number);

    }

    /**
     * Starts when there are enough clients for the number of players chosen
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public void updateGameisReady() throws IOException, InterruptedException {

            insertNickname();

    }



    //Adding player & nicknames


    /**
     * Sends a message to the client asking the nickname
     * @throws IOException Exception for the Message
     * @throws InterruptedException Exception for the Message
     */
    public  void insertNickname() throws IOException, InterruptedException {


          thread.sendToClient(new NicknameRequest());



    }

    /**
     * Setting the name for the virtual view and for the player in the game
     * @param nickname name chosen by the client
     * @throws IOException Exception for the Message
     */
    public synchronized void AddingNickname(String nickname) throws IOException {

        MyNickname = nickname;
        notifyAddingNickname(nickname);

    }

    /**
     * Sends a Message to the client that the nickname is accepted
     * @param nickname nickname chosen by the client
     * @throws IOException Exception for the Message
     */
    @Override
    public void updatePlayerAdded(String nickname) throws IOException {

        if(nickname.equals(MyNickname)) {
            thread.sendToClient(new NicknameAcceptedUpdate());
        }

    }

    /**
     * Sends the client this message if the nickname is not Valid
     * @param nickname nickname not accepted by the server
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateNicknameNotValid(String nickname) throws IOException {

        //TODO: serve ancora?

        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new NicknameNotValidUpdate());
        }
        MyNickname = null;

    }

    //Challenger + Setting chosenCards


    /**
     * Sends to all the clients the Message that it's time for the Challenger to set the cards
     * @param name name of the challenger
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateTimeToChoose(String name) throws IOException {

        thread.sendToClient(new ChooseCardsUpdate(name));

    }

    /**
     * sends the client/challenger the positive update that the Card chosen is added correctly sending the names of the chosenGods
     * @param chosenGods name of the chosenGod
     * @param Names names of the gods chosen until now
     * @param ChallengerName name of the challenger
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateChoose(boolean chosenGods, List Names, String ChallengerName) throws IOException {

        if(!chosenGods) {
            if(MyNickname.equals(ChallengerName)) {
                thread.sendToClient(new AvailableGodsUpdate(Names));
                chooseCard(ChallengerName);
            }
        }
            update(null, null);

    }

    /**
     * Sends the challenger that it's time to choose for the cards
     * @param challengerName name of the challenger
     * @throws IOException Exception for the Message
     */
    public void chooseCard(String challengerName) throws IOException {

        if(MyNickname.equals(challengerName)) {
            thread.sendToClient(new ChallengerCardsRequest());
        }


    }

    /**
     * Sending a notify to the controller checking if the card chosen is correct
     * @param card name of the card chosen by the challenger
     * @throws IOException Exception for the Message
     */
    public void tryThisCard(String card) throws IOException {

        notifyTryThisCard(card);

    }

    /**
     * sends the name of the chosenGods when the challenger is finished choosing
     * @param gods list of chosenGods
     * @param cardChosen false if there are more cards to be chosen
     * @param challengerName name of the challenger
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName) throws IOException {

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
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateGodNotAdded(String challengerName) throws IOException {

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
     * @throws IOException Exception for the Message
     */
    @Override
    public  void updateTimeToSetCard(List<String> chosenGods, String currentPlayerName) throws IOException {

        thread.sendToClient(new SetCardTimeUpdate(currentPlayerName));

    }

    /**
     * Sends the client of che currentPlayer the option to set the God for the Game
     * @param chosenGods list of gods chosen by the challenger
     * @param currentPlayerName name of the current player
     * @throws IOException Exception for the Message
     */
    @Override
    public  void updateSetCard(List<String> chosenGods, String currentPlayerName) throws IOException {

        if(MyNickname.equals(currentPlayerName)) {
            System.out.println(chosenGods);
            thread.sendToClient(new SetYourCardRequest(chosenGods));
        }

    }

    /**
     * sends a notify to the controller to check if the Name of the god chosen by the current player is correct
     * @param chosenGod name of the God chosen
     * @throws IOException Exception for the Message
     */
    public synchronized void godNameChosen(String chosenGod) throws IOException {

        System.out.println(chosenGod);
        notifyGodNameChosen(chosenGod);
    }

    /**
     * Sends to all the clients the name of the Player and the God that the player decided to take
     * @param nickname name of the current player
     * @param godName name of the god chosen by the player
     * @throws IOException Exception for the Message
     */
    @Override
    public  void updateGodSet(String nickname, String godName) throws IOException {

        thread.sendToClient(new CardSetUpdate(nickname, godName));

    }

    /**
     * Send to the client/current player an update that the name of the God chosen is not correct
     * @param nickname name of the current player
     * @param chosenGods name of the chosen God
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateCardNotPresent(String nickname, List<String> chosenGods) throws IOException {

        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new CardNotFoundRequest());
        }

        updateSetCard(chosenGods, nickname);
    }



    //Placing worker


    /**
     * Sends to all the client the update about the time of the game, it's time to place the first workers
     * @param currentPlayerName name of the current Player
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateTimeToPlaceWorker(String currentPlayerName) throws IOException {

        thread.sendToClient(new StartingSetWorkerTimeUpdate(currentPlayerName));
        setWorkers1(currentPlayerName, 0);

    }

    /**
     * Sends to the client of the current player the request to set the first worker
     * @param currentPlayerName name of the current player
     * @param i number of the worker
     * @throws IOException Exception for the Message
     */
    public void setWorkers1(String currentPlayerName, int i) throws IOException {

        if(MyNickname.equals(currentPlayerName)) {
                thread.sendToClient(new StartingSetWorkerRequest(i));
        }

    }

    /**
     * Sends the board update with the position of the worker just added and calls the request to set the second worker
     * @param board sends a copy of the board to print
     * @param currentPlayer name of the current Player
     * @throws IOException Exception for the Message
     */
    @Override
    public void updateBoardAddedWorker(Board board, String currentPlayer) throws IOException {

        Board boardToSend = new Board();
        boardToSend = board;
        thread.sendToClient(new BoardUpdate("boo", boardToSend));
        setWorkers2(currentPlayer, 1);

    }


    /**
     * sends to the client the request to add the second worker
     * @param currentPlayerName name of the current Player
     * @param i number of the worker
     * @throws IOException Exception for the Message
     */
    public void setWorkers2(String currentPlayerName, int i) throws IOException {

        if(MyNickname.equals(currentPlayerName)) {
            thread.sendToClient(new StartingSetWorkerRequest(i));
        }

    }

    public void toSetWorker(int row, int col, int i ) throws IOException {
        if(row > 0 && row <= 5 && col > 0 && col <= 5) {
            notifyAddingWorker(row, col, i);
        } else {
            thread.sendToClient(new WrongCoordinatesUpdate(i));
        }
    }

    @Override
    public void updateSetWorkerOk(String currentPlayer, Board board) throws IOException {

        if(MyNickname.equals(currentPlayer)){
            thread.sendToClient(new BoardUpdate("ciao", board));
        }
    }

    /**
     * This send a message if the coordinates for the Worker are wrong
     * @param i
     * @param currentPlayer
     * @throws IOException
     */
    @Override
    public void updateSetWorker(int i, String currentPlayer) throws IOException {

        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new WrongCoordinatesUpdate(i));
        }

    }


    //Input 6 to start your Turn


    @Override
    public void updateStartMoving(String currentPlayer) throws IOException {

        if(MyNickname.equals(currentPlayer)) {
            notifyStartMoving();
        }

    }
    @Override
    public void updatePlayerHasLost(String playerNickname) throws IOException {

        thread.sendToClient(new PlayerLockedUpdate(playerNickname));


    }


    @Override
    public void updateDecideWorker(String nickname) throws IOException {

         thread.sendToClient(new PlayerTurnUpdate(nickname));
    }


    @Override
    public void updateTimeToChooseWorker(String nickname) throws IOException {

        chooseWorker(nickname);

    }

    @Override
    public void updateAskForEffect(String nickname) throws IOException {
        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new AskEffect());
        }
    }

    @Override
    public void updateAskForEffectBuild(String currentPlayer, int worker) throws IOException {
        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new AskEffectBuild(worker));
        }
    }

    public void updatePlayerBuild(boolean effect, String nickname,int worker) throws IOException {
        notifyPlayerBuild(effect, nickname, worker);
    }

    public void chooseWorker(String nickname) throws IOException {


        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new ChooseYourWorkerRequest());
        }
    }


    public void tryThisWorker(int worker) throws IOException {

        notifyTryThisWorker(worker);

    }


    @Override
    public void updateWorkerSelected(int worker, String current) throws IOException {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new WrongWorkerUpdate(worker));
            moving(worker, current);
        }

    }
    @Override
    public void updateMoving(int worker, String current) throws IOException {

        moving(worker, current);

    }

    public void moving(int worker, String current) throws IOException {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new MoveRequest(worker));
        }
        //se mi sono mossa costruisco
    }

    public void tryMoving(int row, int col, int worker) throws IOException {

        notifyMoving(row, col, worker);

    }


    @Override
    public void updateNoCoordinatesValid(int worker, String current) throws IOException {

        thread.sendToClient(new TryNewCoordinatesRequest(worker));
        //System.out.println("This coordinates are not valid, insert them again");
        moving(worker, current);

    }
    @Override
    public void update(Object obh, Object obj){

        System.out.println("Exception occured");
    }


    @Override
    public void updateWinners(PlayerInterface player) throws IOException {

        thread.sendToClient(new WinMessage(player.getNickname()));

        //System.out.println(player + "You win!");
    }

    @Override
    public void updateBuildTwoInput(String currentPlayer,int worker) throws IOException {
        thread.sendToClient(new BuildTimeUpdate(currentPlayer));
        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new BuildTwoInputRequest(worker));
        }
    }

    @Override
    public void updateMoveTwoInput(String nickname, int worker) throws IOException {
        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new MoveTwoInputRequest(worker));
        }
    }

    public void timeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {

        notifyTimeToMoveTwoInput(row1, col1, row2, col2, worker);

    }

    public void timeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {

        notifyTimeToBuildTwoInput(row1, col1, row2, col2, worker);

    }

    @Override
    public void updateTimeToBuild(int worker, String current) throws IOException {

        thread.sendToClient(new BuildTimeUpdate(current));
        //System.out.println("It's now time to  build!");
        building(worker, current);

    }




    public void building(int worker, String current) throws IOException {


        // System.out.println("Vuoi costruire? Insert Yes or no");
        //String input = cases.nextLine();
        //notifyWantToBuild(worker);
        if(MyNickname.equals(current)) {
            thread.sendToClient(new BuildRequest(worker));
            //System.out.println("Build around your worker # " + worker + "Insert row e col: ");
            //int row = input.nextInt();
            //int col = input.nextInt();
            //notifyBuilding(row, col, worker);
        }


    }

    public void tryToBuild(int row, int col, int worker) throws IOException {

        notifyBuilding(row, col, worker);

    }

    @Override
    public void updateBuilding(int worker, String current) throws IOException {

        if(MyNickname.equals(current)) {
            thread.sendToClient(new TryNewCoordinatesRequest(worker));
            //System.out.println("Try new coordinates: ");
            building(worker, current);
        }

    }



    @Override
    public void updateBoard(Board board) throws IOException {

        thread.sendToClient(new BoardUpdate("board updated", board));
    }

    @Override
    public void run() {

    }

    public void updateTimeToChooseWorkerEffect(boolean effect) throws IOException {
        thread.sendToClient(new ChooseYourWorkerEffectRequest(effect));
    }

    public void tryThisWorkerEffect(boolean effect, int worker) throws IOException {
        notifyTryThisWorkerEffect(effect, worker);
    }

    public void dropConnection() {
        notifyDropConnection(MyNickname);
    }


    /*
    public void menageInput(Integer in) throws IOException, InterruptedException {
        switch (in) {
            case 1:
                //startingGame();
                break;
            case 2:
                insertNickname();
                break;
            case 6:
                //startMoving();
                break;
            case 0:
                System.out.println("Input sbagliato");
                break;
            default:
                System.out.println("Input sbagliato");
                break;
        }
    }



    public void printComandi(){
        System.out.println(PURPLE + "Press 2 to add nickname");
        System.out.println("Press 6 to start your turn");
        System.out.print(RESET);
        System.out.print(ANSI_BLUE);
    }




    public void start(){

        System.out.println("WELCOME TO SANTORINI");
        System.out.println("Press 1 to start the game");
    }

     */






}














