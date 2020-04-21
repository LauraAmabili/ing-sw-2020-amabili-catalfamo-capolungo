
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.*;
import it.polimi.ingsw.Model.Player.Player;
import it.polimi.ingsw.Model.Player.PlayerInterface;

import java.util.List;

public class Controller {

    private Game game;

    public Controller(Game game) {
        this.game = game;
    }

    public void addNickname(String name) {
        game.addNickname(name);
    }

    public void addPlayer(PlayerInterface player) {
        try {
            (game).addPlayers(player);
        } catch (GameIsAlreadyStarted e) {
            System.out.println("Game is Already started!");
        }
    }

    public void initialiseMatch() {
        game.initialiseMatch();
    }
    public void setGod(String in) {
        game.setGod(in);
    }
    public void decoratePlayer(PlayerInterface player) {
        game.decoratePlayer(player.getActiveCard().getGodName(), player);
    }
    public void addWorker(Worker worker, int row, int col) {
        game.addWorker(row, col, worker);
    }
    public void chooseCards() {
        game.chooseCards();
    }

}
