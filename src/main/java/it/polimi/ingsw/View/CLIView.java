package it.polimi.ingsw.View;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.*;

public class CLIView extends View {


    String nickname;
    PlayerInterface player;
    GameEngine controller;
    List<String> chosenGods = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    Scanner cases = new Scanner(System.in);
    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public CLIView(GameEngine controller) {
        this.controller = controller;
    }

    /*
    Map<String, String> map = new HashMap<>();
    @Override
    public void run(){
        while(true) {
            String in = input.nextLine();
            String nomemetodo= map.get(in);
            //array di input
            nomemetodo.invoke(ClasseMetodi, array);
        }
    }
     */
    //TODO: eccezione input case
    //TODO: number of players

    @Override
    public void run() {
        while(true) {
            //TODO: mapping<comando,nomemetodo> + reflection
            printCommand();
            String in = cases.nextLine();
            int integer = Integer.parseInt(in);
            switch (integer) {
                case 1:
                   insertNickname();
                    break;
                case 2:
                   startingGame();
                    break;
                case 3:
                    chooseCards();
                    break;
                case 4:
                    chooseYourGod();
                   break;
                case 5:
                    setFirstWorkers();
                    break;
                default:
                    break;
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
        System.out.println(player);
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
        System.out.println("Choose gods");
        System.out.println(gods);
    }

    public void chooseYourGod(){
        System.out.println("Choose your god");
        String godName = cases.nextLine();
        controller.setGod(godName);
        //decorate player
        controller.decoratePlayer(player);
    }

    public void printCommand(){
        System.out.println(PURPLE + "Add nickname: 1");
        System.out.println("Initialize match: 2");
        System.out.println("Choose God: 3");
        System.out.println("Choose your cards 4");
        System.out.println("Press 5 to add Workers");
        System.out.println("Press 6 to move");
        System.out.print(RESET);
        System.out.print(ANSI_BLUE);
    }

    public void setFirstWorkers(){
        System.out.println("Set your first worker");
        for ( int i = 0; i < player.getWorkerRef().length; i++) {
            Worker worker = player.getWorkerRef()[i];
            System.out.println("Insert coordinates for worker #" + i);
            int raw = input.nextInt();
            int col = input.nextInt();
            controller.addWorker(worker, raw, col);
        }
    }
    public void startingGame(){
        System.out.print("Game is starting...");
        controller.initialiseMatch();
    }
    public void insertNickname(){
        System.out.print("Insert Nickname: ");
        String in = cases.nextLine();
        this.nickname = in;
        controller.addNickname(in);
    }
    public void chooseCards(){

        System.out.println("Time to choose your powers");
        controller.chooseCards();
        for (int i = 0; i < 2; i++){
            System.out.println("Insert god #"+i);
            String name = cases.nextLine();
            chosenGods.add(name);
        }
        System.out.println("Well done! You chosen cards are "+chosenGods);
    }

    @Override
    public void update(Object obh, Object obj){
        System.out.println("Exception occurred");
    }


}