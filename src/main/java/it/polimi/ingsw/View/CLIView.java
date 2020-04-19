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
            System.out.println("1: add nickname");
            String in = input.nextLine();
            Integer integer = Integer.parseInt(in);
            switch (integer) {
                case 1:
                    System.out.print("Nickname: ");
                    in = input.nextLine();
                    controller.addNickname(in);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        String state = (String) arg;
        switch (state) {
            case "ADDNICKNAMES":
                String name = input.nextLine();
                controller.addNickname(name);
                break;
            case "INIMATCH":
                controller.initialiseMatch();
                break;
            default:
                break;
        }

    }

}
