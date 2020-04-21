package it.polimi.ingsw.Model.FSA;

import it.polimi.ingsw.Controller.GameEngine;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class NoStarted implements GameFSA {

    private GameEngine gameEngine;

    public NoStarted(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void addNickname(String name) {
        gameEngine.getNicknames().add(name);
    }

    @Override
    public void chooseCards(List<String> chosenGods) {

    }

    @Override
    public void setCard(String godName) {

    }

    @Override
    public void move(int row, int col, Worker worker) {

    }

    @Override
    public void build(int row, int col, Worker worker) {

    }
}
