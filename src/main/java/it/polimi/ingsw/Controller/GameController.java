
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exceptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.PlayerInterface;

public class GameController {

    GameManager gameManager;

    /*
        private static Controller controller = null;
        public static Controller instance(){
            if(controller==null)
                controller = new Controller();
            return controller;
        }
         */
    public GameController() {
        Game game = new Game();
        Game.instance();
        GameManager gameManager1 = new GameManager(game);
        GameManager.instance(game);
        gameManager = gameManager1;
    }
    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public void addNickname(String name) {
        gameManager.addNickname(name);
    }
    public void addPlayer(PlayerInterface player) {
        try {
            (gameManager).addPlayers(player);
        } catch (GameIsAlreadyStarted e) {
            System.out.println("Game is Already started!");
        }
    }
    public void initialiseMatch(){
        gameManager.initialiseMatch();
    }
    public void setGodName(String in) {
        //TODO: controllare che 'in' sia compreso nella lista dei god (corretto)
        gameManager.setGod(in);
    }
    public void decoratePlayer(PlayerInterface player){
        gameManager.decoratePlayer(player);
    }
    public void addWorker(Worker worker, int row, int col){
        gameManager.addWorker(worker, row, col);
    }
    public void chooseCards(){
            gameManager.chooseCards();
    }
    public void createTurn(){
        gameManager.createTurn();
    }
    public synchronized void addObserver(Observer view){
        gameManager.AddObserver(view);
    }
    public void addChosenGods(String godName){
        gameManager.addChosenGods(godName);
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
