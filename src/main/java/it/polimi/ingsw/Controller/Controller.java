
package it.polimi.ingsw.Controller;

import it.polimi.ingsw.Model.Model;
import it.polimi.ingsw.Model.Player.PlayerInterface;
import it.polimi.ingsw.Model.Turn;

import java.util.List;

public class Controller {

    public String name;
    private Model model = new Model();

    public Controller() {
        model.initialiseMatch();
    }



    public void setNickname(String name) {
        this.model.setNickname(name);
        this.model.notifyStartMatch();
    }

    public void initialiseMatch(){
        this.model.initialiseMatch();
    }

    public List<PlayerInterface> getOnlinePlayers(){
        return this.model.getOnlinePlayers();
    }

    public void setActiveCard(String name){
        this.model.setActiveCard(name);
    }

    public Turn getTurn(){
        return this.model.getTurn();
    }

    public void setTurn(){
        model.setTurn();
    }
   // public void setUpTurn(){
    //this.model.setUpTurn();
    //}
}
