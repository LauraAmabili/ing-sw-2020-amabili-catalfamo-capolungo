package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.*;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import it.polimi.ingsw.Network.Server.ServerThread;

public class VirtualView extends View  {


    private String nickname;
    private PlayerInterface currentPlayer = new Player();
    private ServerThread thread;
    private Scanner input = new Scanner(System.in);
    private Scanner cases = new Scanner(System.in);


    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";

    public VirtualView(ServerThread thread) {
        this.thread = thread;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public PlayerInterface getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(PlayerInterface currentPlayer) {
        this.currentPlayer = currentPlayer;

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
            } catch (NumberFormatException | IOException exc) {
                try {
                    menageInput(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Preparing game

    public void startingGame() throws IOException {

        //System.out.println("Welcome! Choose number of players: 2 or 3?");

        //int integer = input.nextInt();
        //notifyInitialiseMatch(integer);

    }
    public void notifyNumberOfPlayer(int number){

        notifyInitialiseMatch(number);

    }
    @Override
    public void updateGameisReady() throws IOException {

        //System.out.println("Game is ready!");
        insertNickname();


    }

    //Adding player & nicknames

    public void insertNickname() throws IOException {

        thread.sendToClient(new NicknameRequest());

    }
    public void AddingNickname(String nickname) {

        notifyAddingNickname(nickname);

    }
    @Override
    public void updatePlayerAdded(String nickname) throws IOException {

        thread.sendToClient(new NicknameAccepted());
        //System.out.println("Nickname " + nickname + " accepted");


    }
    @Override
    public void updateNicknameNotValid() throws IOException {

        thread.sendToClient(new NicknameNotValid());
        //System.out.println("Nickname not valid");
        //insertNickname();
    }

    //Challenger + Setting chosenCards

    @Override
    public void updateTimeToChoose(List gods, String name){

        chooseCards();
    }
    public void chooseCards(){


        notifyChoosingCards();

    }
    @Override
    public void updateChoose(boolean chosenGods, List Names, String ChallengerName) throws IOException {

        ///TODO: thread.sentAll(new TimeToChooseCards(ChallengerName))
        if(!chosenGods) {
            //System.out.println("Challenger was random, "+ ChallengerName + "can now choose the Cards ");
            //System.out.println(Names);
            thread.sendToClient(new CardsName(Names));
            chooseCard();
        }
        else  {
            System.out.println("Cards are already been chosen");
        }
    }
    public void chooseCard() throws IOException {

        thread.sendToClient(new ChooseTheCard());


    }
    public void tryThisCard(String card){

        notifyTryThisCard(card);


    }
    @Override
    public void updateGodAdded(List<String> gods, boolean cardChosen) throws IOException {


        thread.sendToClient(new GodAdded(gods));
        //System.out.println("God added:");
        //for(String g : gods)
           // System.out.println(g);
        if(!cardChosen) {
            chooseCard();
        }


    }
    @Override
    public void updateGodNotAdded() throws IOException {

        thread.sendToClient(new GodNotAdded());
        chooseCard();

    }


    //Setting personal God

    @Override
    public void updateTimeToSetCard(List chosenGods, String currentPlayerName) throws IOException {

        //TODO send everyone "it's currentplayer turn to set up the card"
        // System.out.println(currentPlayerName + ",it's time to choose your card");

        chooseYourGod(chosenGods);

    }
    public void chooseYourGod(List<String> chosenGods) throws IOException {

        thread.sendToClient(new SetYourCard(chosenGods));
        //System.out.println("Choose your god");
        //String godName = cases.nextLine();
        //notifyGodNameChosen(godName);
    }
    public void godNameChosen(String chosenGod){
        notifyGodNameChosen(chosenGod);
    }
    @Override
    public void updateGodSet(String nickname, String godName){

        //se tutto va bene mando a tutti la carta scelta
        //TODO: send to every client new setcardUpdate(nickname, godName)
        //System.out.println(nickname + " now has " + godName + " as Active Card "+ godName);
    }

    /*
    @Override
    public void updateGodAlreadyChosen(List<String> chosenGods, String godName) throws IOException {

        System.out.println(godName + "has been already chosen, try a new one");
        chooseYourGod(chosenGods);

    }

     */

    @Override
    public void updateCardNotPresent(List<String> chosenGods) throws IOException {

        //thread.sendToClient();
        System.out.println("Card not present!");
        chooseYourGod(chosenGods);

    }



    //Placing worker

    @Override
    public void updateTimeToPlaceWorker(String currentPlayerName) {

        System.out.println("Now " + currentPlayerName + "is setting his workers");
        setWorkers();

    }
    public void setWorkers(){

        System.out.println("Time to set your Workers");
        for(int i = 0;  i < 2; i++) {
            System.out.println("Insert your coordinates (x,y) as row and col");
            int row = input.nextInt();
            int col = input.nextInt();
            while(row > 5 || row < 1 || col > 5 || col < 1) {
                System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
                row = input.nextInt();
                col = input.nextInt();
            }
            notifyAddingWorker(row, col, i);
        }

    }
    @Override
    public void updateSetWorker(int i){

        System.out.println("Posizione sbagliata, riprova");
        int row = input.nextInt();
        int col = input.nextInt();
        notifyAddingWorker(row, col, i);

    }

    //Input 6 to start your Turn

    public void startMoving(){

        notifyStartMoving();
    }



    @Override
    public void updatePlayerHasLost(String playerNickname){

        System.out.println(playerNickname + "'s workers are locked. Out!");

    }

    @Override
    public void updateDecideWorker(String nickname) {

        System.out.println("It's "  + nickname + " turn!");
        System.out.println("Choosing his worker");
        chooseWorker();
    }
    public void chooseWorker(){

        System.out.println("Choose your Worker : ");
        int worker = input.nextInt();
        notifyTryThisWorker(worker);

    }


    @Override
    public void updateWorkerSelected(int worker) {

        System.out.println("This worker cannot moves. It's been automatically chosen the other one");
        moving(worker);

    }
    @Override
    public void updateMoving(int worker){

        //System.out.println("Worker " + worker +" can move");
        moving(worker);
    }

    public void moving(int worker){

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
    public void updateNoCoordinatesValid(int worker) {

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
    public void updateTimeToBuild(int worker) {

        System.out.println("It's now time to  build!");
        building(worker);

    }
    public void building(int worker){

        // System.out.println("Vuoi costruire? Insert Yes or no");
        //String input = cases.nextLine();
        //notifyWantToBuild(worker);

        System.out.println("Build around your worker # " + worker + "Insert row e col: " );
        int row = input.nextInt();
        int col = input.nextInt();
        notifyBuilding(row, col, worker);


    }

    @Override
    public void updateBuilding(int worker){

            System.out.println("Try new coordinates: ");
            building(worker);

    }



    @Override
    public void updateBoard(Board board){
        System.out.println(GREEN);
        board.printGrid();
        System.out.println(RESET);
        System.out.println(ANSI_BLUE);
    }



    public void menageInput(Integer in) throws IOException {
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














