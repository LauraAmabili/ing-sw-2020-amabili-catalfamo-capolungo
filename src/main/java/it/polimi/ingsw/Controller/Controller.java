
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Exeptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Model;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Turn;

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

}
