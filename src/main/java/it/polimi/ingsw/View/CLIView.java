package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

public class CLIView extends View  {


    private String nickname;
    private PlayerInterface player;
    private GameController controller;
    private Scanner input = new Scanner(System.in);
    private Scanner cases = new Scanner(System.in);


    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";

    public CLIView(GameController controller) {
        this.controller = controller;

    }

    //TODO: number of players


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
        System.out.println("Nickname " + obj + " accepted");
    }
    @Override
    public void updateGameisReady(){
        System.out.println("Game is ready!");

    }
    @Override
    public void updateGodSetted(PlayerInterface playerActing, String godName){
        player = playerActing;
        System.out.println(player.getNickname() + " now has " + godName +" as Active Card "+ player.getActiveCard().getGodName());
    }
    @Override
    public void updatePlayerDecorated(PlayerInterface playerDecorated){
        player = playerDecorated;
        System.out.println(player);
        System.out.println(player.getNickname() + " decorated correctly");
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
    public void updateTimeToChoose(List gods){
        //TODO: devi stampare il nome, di chi?? lol
        System.out.println("Choose gods");
        System.out.println(gods.toString());
    }
    @Override
    public void updateGodAdded(List gods){
        System.out.println("God added:");
        System.out.println(gods.toString());
    }
    @Override
    public void updateCardsChosen(List gods, int cardChosen, int numberOfPlayers){

        if(cardChosen == 2){
        System.out.println("Cards Already Chosen " + gods);}
        else {
            for (int i = 0 ; i < numberOfPlayers ; ) {
                System.out.println("Insert god #" + i);
                String name = cases.nextLine();
                notifyAddChosenGods(name);
                //controller.addChosenGods(name);
                i++;
            }
        }
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
    public void updateMoving(Object object){

        if(!(boolean)object) {
            chooseWorker();
        }
        else
        {
            moving((int)object);
        }

    }
    @Override
    public void updateBuilding(Object object){

        if(!(boolean)object){
            System.out.println("Try new coordinates: ");
            building(object);
        }




    }
    @Override
    public void updateSetWorker(int i){

        System.out.println("Posizione sbagliata, riprova");
        //TODO: gestire se il worker è messo in una posizione già occupata
        int row = input.nextInt();
        int col = input.nextInt();
        notifyAddingWorker(row, col, i);

    }
    @Override
    public void updateCardNotPresent(List chosenGods){
        System.out.println("Card not present!");
        chooseYourGod();

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
        System.out.println(PURPLE + "Press 2 to add nickname");
        System.out.println("You are the challenger, press 3 to choose your cards");
        System.out.println("Press 4 to choose God ");
        System.out.println("Press 5 to add Workers ");
        //System.out.println("Press 6 to move");
        System.out.print(RESET);
        System.out.print(ANSI_BLUE);
    }
    public void startingGame(){

        notifyInitialiseMatch();
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
        notifyChoosingCards();
        //controller.chooseCards();
    }
    public void chooseYourGod() {

        System.out.println("Choose your god");
        String godName = cases.nextLine();
        notifyGodNameChosen(godName);
        System.out.println(player + "io");

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
    public void moving(Object obj){

        System.out.println("Choose row & col: ");
        int row = input.nextInt();
        int col = input.nextInt();
        notifyMoving(row, col, (Integer)obj);
        building(obj);

    }
    public void building(Object obj){

       // System.out.println("Vuoi costruire? Insert Yes or no");
        //String input = cases.nextLine();

        System.out.println("Time to build! Insert row e col: " );
        int row = input.nextInt();
        int col = input.nextInt();
        notifyBuilding(row, col, (Integer)obj);


    }




}














