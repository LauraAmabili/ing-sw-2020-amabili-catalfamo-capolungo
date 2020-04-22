
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exceptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.PlayerInterface;

public class Controller {

    Model model;

    /*
        private static Controller controller = null;
        public static Controller instance(){
            if(controller==null)
                controller = new Controller();
            return controller;
        }
         */
    public Controller() {
        Game game = new Game();
        Game.instance();
        Model model1 = new Model(game);
        Model.instance(game);
        model = model1;
    }
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
    public void setGodName(String in) {
        //TODO: controllare che 'in' sia compreso nella lista dei god (corretto)
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
    public synchronized void addObserver(Observer view){
        model.AddObserver(view);
    }
    public void addChosenGods(String godName){
        model.addChosenGods(godName);
    }
    /*
    public void turn(){
        model.Turn();
    }
    /*
    public void thisWorker(int worker){
        model.thisWorker(worker);
    }

     */
}
