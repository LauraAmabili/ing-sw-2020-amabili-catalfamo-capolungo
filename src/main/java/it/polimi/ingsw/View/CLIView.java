package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

public class CLIView extends View  {




    private String nickname;
    private PlayerInterface currentPlayer = new Player();
    private Scanner input = new Scanner(System.in);
    private Scanner cases = new Scanner(System.in);


    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";



    public CLIView() {


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
            } catch (NumberFormatException exc) {
                menageInput(0);
            }
        }
    }

    @Override
    public void updatePlayerAdded(Object obj){

        currentPlayer.setNickname((String)obj);
        System.out.println("Nickname " + obj + " accepted");
    }
    @Override
    public void updateGameisReady(){

        System.out.println("Game is ready!");
        insertNickname();


    }
    @Override
    public void updateGodSetted(PlayerInterface playerActing, String godName){

        currentPlayer = playerActing;
        System.out.println(currentPlayer.getNickname() + " now has " + godName + " as Active Card "+ currentPlayer.getActiveCard().getGodName());
    }
    @Override
    public void updatePlayerDecorated(PlayerInterface playerDecorated){
        currentPlayer = playerDecorated;
        System.out.println(currentPlayer);
        System.out.println(currentPlayer.getNickname() + " decorated correctly");
        //System.out.println(player);
    }
    @Override
    public void updateBoard(Board board){
        System.out.println(GREEN);
        board.printGrid();
        System.out.println(RESET);
        System.out.println(ANSI_BLUE);
    }
    @Override
    public void updateGodAdded(List<God> gods, boolean cardChosen){


        System.out.println("God added:");
        for(God g : gods)
            System.out.println(g.getGodName());
        if(!cardChosen) {
            chooseCard();
        }


    }
    @Override
    public void updateGodNotAdded(){

        System.out.println("Try Again");
        chooseCard();

    }
    @Override
    public void update(Object obh, Object obj){

        System.out.println("Exception occured");
    }
    @Override
    public void updateWinners(PlayerInterface player){
        System.out.println(player+ "wins!");
    }
    @Override
    public void updateDecideWorker(Object bol){


        chooseWorker();
    }
    @Override
    public void updateMoving(int worker){

        moving(worker);

    }
    @Override
    public void updateBuilding(Object object, int worker){

        if(!(boolean)object){
            System.out.println("Try new coordinates: ");
            building(worker);
        }




    }
    @Override
    public void updateSetWorker(int i){

        System.out.println("Posizione sbagliata, riprova");
        int row = input.nextInt();
        int col = input.nextInt();
        notifyAddingWorker(row, col, i);

    }
    @Override
    public void updateCardNotPresent(List chosenGods){

        System.out.println("Card not present!");
        chooseYourGod();

    }
    @Override
    public void updateChoose(boolean chosenGods, List Names, String ChallengerName){

        if(!chosenGods) {
            System.out.println("Challenger was random, "+ ChallengerName + "can now choose the Cards ");
            System.out.println(Names);
            chooseCard();
        }
        else  {
            System.out.println("cards are already been chosen");
        }
    }
    @Override
    public void updateNicknameNotValid(){
        System.out.println("Nickname not valid");
        insertNickname();
    }
    @Override
    public void updatePlayerHasLost(String playerNickname){

        System.out.println(playerNickname + " out!");
    }



    public void menageInput(Integer in) {
        switch (in) {
            case 1:
                startingGame();
                break;
            case 2:
                insertNickname();
                break;
            case 3:
                chooseCards();
                break;
            case 4:
                chooseYourGod();
                break;
            case 5:
                setWorkers();
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
        System.out.println(PURPLE + "Press 2 to add a player Nickname");
        System.out.println("Write '3' if all the players are in");
        System.out.println("Press 4 if you are ready to choose your God ");
        System.out.println("Press 5 to add Workers ");
        System.out.println("Press 6 to start your turn");
        System.out.print(RESET);
        System.out.print(ANSI_BLUE);
    }
    public void startingGame(){

        System.out.println("Benvenuto! Choose number of players: 2 or 3?");

        int number = input.nextInt();

        notifyInitialiseMatch(number);
        //controller.initialiseMatch();
        //controller.createTurn();
    }
    public void insertNickname(){

        System.out.print("Insert Nickname: ");
        String in = cases.nextLine();
        this.nickname = in;
        notifyAddingNickname(in);
        //controller.addNickname(in);
    }
    public void chooseCards(){

        System.out.println("Time to choose your powers");
        System.out.println("A challenger will be assigned randomly");

        notifyChoosingCards();


    }
    public void chooseYourGod() {

        System.out.println("Choose your god");
        String godName = cases.nextLine();
        notifyGodNameChosen(godName);
        System.out.println(currentPlayer + "io");

    }
    public void start(){

        System.out.println("WELCOME TO SANTORINI");
        System.out.println("Press 1 to start the game");
    }
    public void setWorkers(){

        System.out.println("Time to set your Workers");
        for(int i = 0;  i < 2; i++){
            int row = input.nextInt();
            int col = input.nextInt();
            notifyAddingWorker(row, col, i);
        }

    }
    public void chooseWorker(){

        System.out.println("Choose your Worker : ");
        int worker = input.nextInt();
        notifyTryThisWorker(worker);

    }
    public void startMoving(){

        notifyStartMoving();

    }
    public void moving(int worker){

        System.out.println("Choose row & col: ");
        int row = input.nextInt();
        int col = input.nextInt();
        notifyMoving(row, col, worker);
        //se mi sono mossa costruisco
        building(worker);

    }
    public void building(int worker){

       // System.out.println("Vuoi costruire? Insert Yes or no");
        //String input = cases.nextLine();
        //notifyWantToBuild(worker);

        System.out.println("Time to build around your worker #" + worker + "Insert row e col: " );
        int row = input.nextInt();
        int col = input.nextInt();
        notifyBuilding(row, col, worker);


    }
    public void chooseCard(){

        System.out.println("Choose card: ");
        String in = cases.nextLine();
        notifyTryThisCard(in);

    }




}














