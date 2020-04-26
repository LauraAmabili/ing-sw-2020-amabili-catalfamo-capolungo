package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.util.ArrayList;
import java.util.List;

public class Observable {


    private List<ObserverModel> observerModels = new ArrayList<>();


    public void AddObserver(ObserverModel o){
        this.observerModels.add(o);
    }

    void notifyPlayerAdded(Object obj){
        for(ObserverModel o : observerModels){
            o.updatePlayerAdded(obj);
        }
    }
    void notifyGameIsRead(){
        for(ObserverModel o : observerModels){
            o.updateGameisReady();
        }
    }
    void notifyGodSetted(PlayerInterface player, String godName){
        for(ObserverModel o : observerModels){
            o.updateGodSetted(player, godName);
        }
    }

    void notifyPlayerDecorated(PlayerInterface playerDecorated){
        for(ObserverModel o : observerModels){
            o.updatePlayerDecorated(playerDecorated);
        }
    }

    void notifyWorkerSettled(Board board){
        for(ObserverModel o : observerModels){
            o.updateBoard(board);
        }
    }
    void notifyCards(List gods){
        for(ObserverModel o : observerModels){
            o.updateTimeToChoose(gods);
        }
    }
    /*
    void notifyObservers(Object obj) {
        for(Observer o : observers){
            o.update(obj);
        }
    }
*/
    void notifyObservers(Object something, Object obj){
        for(ObserverModel o : observerModels){
            o.update(something,obj);
        }
    }

    void notifyGodAdded(List gods){
        for(ObserverModel o : observerModels){
            o.updateGodAdded(gods);
        }
    }
    void notifyCardsChosen(List gods, int cardsChosen){
        for(ObserverModel o : observerModels){
            o.updateCardsChosen(gods, cardsChosen);
        }
    }
    void notifyWinner(PlayerInterface player){
        for(ObserverModel o : observerModels){
            o.updateWinners(player);
        }
    }
    void notifyBoardUpdate(Board board){
        for(ObserverModel o : observerModels){
            o.updateBoard(board);
        }
    }
    void notifyWhereToMove(){

    }


}
