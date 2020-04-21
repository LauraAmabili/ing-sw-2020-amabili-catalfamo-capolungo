package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Model;
import it.polimi.ingsw.Model.Observable;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;
import java.util.Scanner;

public class CLIView extends View {


    String nickname;
    PlayerInterface player;
    Controller controller;
    Scanner input = new Scanner(System.in);

    public CLIView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true) {
            //TODO: Scrivere lista comandi
            System.out.println("Add nickname: 1");
            System.out.println("Initialize match: 2");
            System.out.println("Choose God: 3");
            //System.out.println("You want to decorate? press 4 bitch!");
            String in = input.nextLine();
            Integer integer = Integer.parseInt(in);
            switch (integer) {
                case 1:
                    System.out.print("Insert Nickname: ");
                    in = input.nextLine();
                    this.nickname = in;
                    controller.addNickname(in);
                    break;
                case 2:
                    System.out.print("Game is starting...");
                    controller.initialiseMatch();
                    controller.createTurn();
                    break;
                case 3:
                    System.out.println("Choose your god");
                    in = input.nextLine();
                    controller.setGod(in);
                    controller.decoratePlayer(player);
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void update(Object o, Object obj){
        String state = (String) obj;
        switch (state){
            case "GODSETTED":
                player = (Player)o;
                System.out.println("God setted");
                System.out.println( player.getNickname() + " " + player.getActiveCard().getGodName() + " " + "sto cazzo");
                break;
            case "PLAYERDECORATED":
                System.out.println(player);
                System.out.println(player.getNickname()+ " decorated corretly");
                break;
            default:
                break;
        }
    }
    @Override
    public void update(Object arg) {
        String state = (String) arg;
        switch (state) {
            case "ADDNICKNAMES":
                System.out.println("Nickname accepted");
                break;
            case "INIMATCH":
                System.out.println("Game is ready!");

                break;
            case "Exception":
                System.out.println("There was an exception");
                //case "GODSETTED":
                //  System.out.println("God setted");
            default:
                break;
        }

    }

}