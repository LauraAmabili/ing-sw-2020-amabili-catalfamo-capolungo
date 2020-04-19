package it.polimi.ingsw.View;

import it.polimi.ingsw.Controller.Controller;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Model;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class CLIView extends View {

    PlayerInterface player;
    Controller controller;

    public CLIView(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while(true) {
            Scanner input = new Scanner(System.in);
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
                Model model = ((Model)o);
                for (int i = 0; i < model.getGame().getNicknames().size(); i++) {
                    System.out.println(model.getGame().getNicknames().get(i));
                }
                break;
            case "":
                break;
            default:
                break;
        }

    }

}
