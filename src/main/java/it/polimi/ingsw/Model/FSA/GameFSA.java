package it.polimi.ingsw.Model.FSA;

import it.polimi.ingsw.Model.Worker;

import java.util.List;

public interface GameFSA {

    void addNickname(String name);

    void chooseCards(List<String> chosenGods);

    void setCard(String godName);

    void move(int row, int col, Worker worker);

    void build(int row, int col, Worker worker);

}
