
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

public class GameController {

    private GameManager gameManager;




    public GameController() {
        Game game = new Game();
        GameManager gameManager1 = new GameManager(game);
        gameManager = gameManager1;
    }
    public GameController(GameManager gameManager) {
        this.gameManager = gameManager;
    }



    public void addNickname(String name) {

        gameManager.addNickname(name);
    }
    public void addPlayer(PlayerInterface player) {
        gameManager.addPlayers(player);
    }
    public void initialiseMatch(){
        gameManager.initialiseMatch();
    }
    public void setGodName(String in) {
        //TODO: controllare che 'in' sia compreso nella lista dei god (corretto)
        gameManager.setGod(in);
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
