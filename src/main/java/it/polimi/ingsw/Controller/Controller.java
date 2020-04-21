
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;

public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void addNickname(String name) {
        model.addNickname(name);
    }

    public void addPlayer(PlayerInterface player) {
        try {
            (model).addPlayers(player);
        } catch (GameIsAlreadyStarted e) {
            System.out.println("Game is Already started!");
        }
    }

    public void initialiseMatch(){
        model.initialiseMatch();
    }
    public void setGod(String in){
        model.setGod(in);
    }
    public void decoratePlayer(PlayerInterface player){
        model.decoratePlayer(player);
    }
    public void addWorker(Worker worker, int row, int col){
        model.addWorker(worker, row, col);
    }
    public void chooseCards(){
        model.chooseCards();
    }

    public void createTurn(){
        model.createTurn();
    }


}
