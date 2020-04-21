package it.polimi.ingsw.Model.Player.FSA;

import it.polimi.ingsw.Model.Game;
import it.polimi.ingsw.Model.Worker;

import java.util.List;

public class NoStarted implements GameFSA {

    private Game game;

    public NoStarted(Game game) {
        this.game = game;
    }

    @Override
    public void addNickname(String name) {
        game.getNicknames().add(name);
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
