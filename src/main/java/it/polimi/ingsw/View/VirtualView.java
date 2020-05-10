package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.*;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Server.ServerThread;

public class VirtualView extends View  {


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

    //Preparing game



    public void startingGame() throws IOException, InterruptedException {

       //notifyStartingGame();


        //notifyInitialiseMatch(integer);

    }

    public synchronized void notifyNumberOfPlayer(int number) throws IOException, InterruptedException {

            numberOfPlayer = number;
            notifyInitialiseMatch(number);


    }
    @Override
    public void updateGameisReady() throws IOException, InterruptedException {

            insertNickname();

    }



    //Adding player & nicknames

    public  void insertNickname() throws IOException, InterruptedException {

     // if(thread.getNumOnline() == numberOfPlayer) {
          thread.sendToClient(new NicknameRequest());
      //}


    }
    public synchronized void AddingNickname(String nickname) throws IOException, InterruptedException {

        MyNickname = nickname;
        notifyAddingNickname(nickname);

    }
    @Override
    public void updatePlayerAdded(String nickname) throws IOException {

        if(nickname.equals(MyNickname)) {
            thread.sendToClient(new NicknameAccepted());
        }

    }
    @Override
    public void updateNicknameNotValid(String nickname) throws IOException {

        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new NicknameNotValid());
        }
        MyNickname = null;

    }

    //Challenger + Setting chosenCards


    @Override
    public void updateTimeToChoose(List gods, String name) throws IOException {

        thread.sendToClient(new TimeToChooseCards(name));
        //chooseCards();
    }
    public void chooseCards() throws IOException {

        notifyChoosingCards();

    }
    @Override
    public void updateChoose(boolean chosenGods, List Names, String ChallengerName) throws IOException {


        if(!chosenGods) {
            if(MyNickname.equals(ChallengerName)) {
                thread.sendToClient(new CardsName(Names));
                chooseCard(ChallengerName);
            }
        }
        else  {
            System.out.println("Cards are already been chosen");
        }
    }
    public void chooseCard(String challengerName) throws IOException {

        if(MyNickname.equals(challengerName)) {
            thread.sendToClient(new ChooseTheCard());
        }


    }
    public void tryThisCard(String card) throws IOException {

        notifyTryThisCard(card);

    }
    @Override
    public void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName) throws IOException {

        if (!cardChosen) {
            chooseCard(challengerName);
        } else {
            if (MyNickname.equals(challengerName)) {
                thread.sendToClient(new GodAdded(gods));
            }
        }

    }

    @Override
    public void updateGodNotAdded(String challengerName) throws IOException {

        if(MyNickname.equals(challengerName)) {
            thread.sendToClient(new GodNotAdded());
            chooseCard(challengerName);
        }

    }


    //Setting personal God

    @Override
    public  void updateTimeToSetCard(List<String> chosenGods, String currentPlayerName) throws IOException {

        thread.sendToClient(new TimeToSetCard(currentPlayerName));
        //chooseYourGod(chosenGods, currentPlayerName);

    }

    @Override
    public  void updateSetCard(List<String> chosenGods, String currentPlayerName) throws IOException {

        //chooseYourGod(chosenGods, currentPlayerName);

        if(MyNickname.equals(currentPlayerName)) {
            System.out.println(chosenGods);
            thread.sendToClient(new SetYourCard(chosenGods));
        }

    }


    public  void chooseYourGod(List<String> chosenGods, String currentPlayerName) throws IOException {

    }
    //mando il messaggio sopra e mi fermo! aspetto che mi mandi la carta, sono in ascolto della carta


    public synchronized void godNameChosen(String chosenGod) throws IOException {

        System.out.println(chosenGod);
        notifyGodNameChosen(chosenGod);
    }

    @Override
    public  void updateGodSet(String nickname, String godName) throws IOException {

        thread.sendToClient(new SetCardUpdate(nickname, godName));

    }
    @Override
    public void updateCardNotPresent(String nickname, List<String> chosenGods) throws IOException {

        if(MyNickname.equals(nickname)) {
            thread.sendToClient(new CardNotPresent());
        }
        //chooseYourGod(chosenGods, nickname);
        updateSetCard(chosenGods, nickname);
    }


    /*
    @Override
    public void updateGodAlreadyChosen(List<String> chosenGods, String godName) throws IOException {

        System.out.println(godName + "has been already chosen, try a new one");
        chooseYourGod(chosenGods);

    }

     */

    //Placing worker

    @Override
    public void updateTimeToPlaceWorker(String currentPlayerName) throws IOException {

        thread.sendToClient(new TimeToPlaceWorkers(currentPlayerName));
        setWorkers(currentPlayerName);

    }

    public void setWorkers(String currentPlayerName) throws IOException {

        if(MyNickname.equals(currentPlayerName)) {
            for (int i = 0; i < 2; i++) {
                thread.sendToClient(new SetWorkerRequest(i));
            }
        }

        /*
        System.out.println("Time to set your Workers");
        System.out.println("Insert your coordinates (x,y) as row and col");
        for(int i = 0;  i < 2; i++) {

            int row = input.nextInt();
            int col = input.nextInt();
            while(row > 5 || row < 1 || col > 5 || col < 1) {
                System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
                row = input.nextInt();
                col = input.nextInt();
            }
            notifyAddingWorker(row, col, i);
        }
         */
    }
    public void toSetWorker(int row, int col, int i ) throws IOException {

        notifyAddingWorker(row, col, i);

    }

    @Override
    public void updateSetWorkerOk(String currentPlayer, Board board) throws IOException {

        if(MyNickname.equals(currentPlayer)){
            thread.sendToClient(new BoardUpdateWorker("ALL", 1, 1, 1));
        }
    }
    @Override
    public void updateSetWorker(int i, String currentPlayer) throws IOException {

        if(MyNickname.equals(currentPlayer)) {
            thread.sendToClient(new WrongPositionForWorker(i));
        }
        //System.out.println("Posizione sbagliata, riprova");
        // int row = input.nextInt();
        //int col = input.nextInt();
        //notifyAddingWorker(row, col, i);

    }


    //Input 6 to start your Turn

    public void startMoving() throws IOException {

        notifyStartMoving();

    }
    @Override
    public void updatePlayerHasLost(String playerNickname) throws IOException {

        thread.sendToClient(new PlayerOut(playerNickname));
        //System.out.println(playerNickname + "'s workers are locked. Out!");

    }
    @Override
    public void updateDecideWorker(String nickname) throws IOException {

        thread.sendToClient(new TurnToMove(nickname));
        //System.out.println("It's "  + nickname + " turn!");
        //System.out.println("Choosing his worker");
        chooseWorker();
    }
    public void chooseWorker() throws IOException {


        System.out.println("Choose your Worker : ");
        int worker = input.nextInt();
        notifyTryThisWorker(worker);

    }


    @Override
    public void updateWorkerSelected(int worker) throws IOException {

        System.out.println("This worker cannot moves. It's been automatically chosen the other one");
        moving(worker);

    }
    @Override
    public void updateMoving(int worker) throws IOException {

        //System.out.println("Worker " + worker +" can move");
        moving(worker);
    }

    public void moving(int worker) throws IOException {

        System.out.println("Choose row & col: ");
        int row = input.nextInt();
        int col = input.nextInt();
        while(row > 5 || row < 1 || col > 5 || col < 1) {
            System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
            row = input.nextInt();
            col = input.nextInt();
        }
        notifyMoving(row, col, worker);
        //se mi sono mossa costruisco

    }
    @Override
    public void updateNoCoordinatesValid(int worker) throws IOException {

        System.out.println("This coordinates are not valid, insert them again");
        moving(worker);

    }
    @Override
    public void update(Object obh, Object obj){

        System.out.println("Exception occured");
    }
    @Override
    public void updateWinners(PlayerInterface player){
        System.out.println(player + "You win!");
    }

    @Override
    public void updateTimeToBuild(int worker) throws IOException {

        System.out.println("It's now time to  build!");
        building(worker);

    }

    @Override
    public void updateBoardAddedWorker(int row, int col, int worker) throws IOException {
        thread.sendToClient(new BoardUpdateWorker("ALL", row, col, worker));
    }

    public void building(int worker) throws IOException {

        // System.out.println("Vuoi costruire? Insert Yes or no");
        //String input = cases.nextLine();
        //notifyWantToBuild(worker);

        System.out.println("Build around your worker # " + worker + "Insert row e col: " );
        int row = input.nextInt();
        int col = input.nextInt();
        notifyBuilding(row, col, worker);


    }

    @Override
    public void updateBuilding(int worker) throws IOException {

            System.out.println("Try new coordinates: ");
            building(worker);

    }



    @Override
    public void updateBoard(Board board) throws IOException {

        System.out.println("Sto provando a mandare la board ");
        thread.sendToClient(new BoardUpdate("ho aggiornato la board", board));

        System.out.println("la board l'ho mandata");
        /*
        System.out.println(GREEN);
        board.printGrid();
        System.out.println(RESET);
        System.out.println(ANSI_BLUE);

         */
    }



    public void menageInput(Integer in) throws IOException, InterruptedException {
        switch (in) {
            case 1:
                startingGame();
                break;
            case 2:
                insertNickname();
                break;
            case 6:
                startMoving();
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






}














