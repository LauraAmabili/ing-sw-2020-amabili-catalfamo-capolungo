package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.GameController;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.lang.reflect.Method;
import java.util.*;

public class CLIView extends View {


    String nickname;
    PlayerInterface player;
    GameController controller;
    Scanner input = new Scanner(System.in);
    Scanner cases = new Scanner(System.in);

    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public CLIView(GameController controller) {
        this.controller = controller;
    }
    Map<Integer, Method> run = new HashMap<>();
    //TODO: eccezione input case
    //TODO: number of players


    @Override
    public void run() {
        //chiamata a metodo costruzione mappa
        createMap();
        System.out.println("Press 1 to start the game");
        while(true) {
            String in = cases.nextLine();
            int integer = Integer.parseInt(in);
            switch (integer) {
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
                    break;
                case 6:
                    //turn();
                default:
                    break;
                    }
            }
        }

    public void Comandi (){

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
        //TODO: devi stampare il nome
        System.out.println("Choose gods");
        System.out.println(gods.toString());
    }
    @Override
    public void updateGodAdded(List gods){
        System.out.println("God added:");
        System.out.println(gods);
    }
    @Override
    public void updateCardsChosen(List gods, int cardChosen){
        if(cardChosen == 2){
        System.out.println("Cards Already Chosen "+ gods);}
        else {
            //TODO: number of players
            for (int i = 0; i < 2; i++) {
                System.out.println("Insert god #" + i);
                String name = cases.nextLine();
                controller.addChosenGods(name);
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
    //@Override
    /*
    public void updateTimeToChooseWorker(){
        System.out.println("Choose worker");
        int worker = input.nextInt();
        controller.thisWorker(worker);
    }

*/
    public void chooseYourGod() {
        System.out.println("Choose your god");
        String godName = cases.nextLine();
        //TODO: controller.GodTaken(godName);
        controller.setGodName(godName);
        //decorate player
        //controller.decoratePlayer(player);
    }

    public void printComandi(){
        System.out.println(PURPLE + "Initialize match: 1");
        System.out.println("Add nickname: 2");
        System.out.println("Choose your cards 3");
        System.out.println("Choose God: 4");
        System.out.println("Press 5 to add Workers");
        //System.out.println("Press 6 to move");
        System.out.print(RESET);
        System.out.print(ANSI_BLUE);
    }
    public void startingGame(){
        //System.out.print("Game is starting...");
        controller.initialiseMatch();
        controller.createTurn();
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
    }
    public void checkGods(){

    }


    public void createMap(){
        Map<Integer, Method> comandi = new HashMap<>();
        Integer i = 0;
       // List<String> names = new ArrayList<String>(Arrays.asList('inserNickname', )); //TODO: inserisci elementi
        for(Method m : this.getClass().getMethods()) {
            //if(names.contains(m.getName()))
            comandi.put(i, m);
            //System.out.println(m.getName() + i.toString());
            i++;
        }
    }


}














