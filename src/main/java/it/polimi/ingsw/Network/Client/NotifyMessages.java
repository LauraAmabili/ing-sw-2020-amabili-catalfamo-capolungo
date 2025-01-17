package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotifyMessages {


    private final List<ClientObserver> observersClient = new ArrayList<>();
    public void addObserver(ClientObserver o){
        this.observersClient.add(o);
    }
    public void removeObserver(ClientObserver o) {
        this.observersClient.remove(o);
    }

    public void notifyPlayerNumberResponse(String num) {
        for(ClientObserver c : observersClient){
            c.updatePlayerNumberResponse(num);
        }
    }

    public void notifyNicknameResponse(String nickname)  {
        for(ClientObserver c : observersClient){
            c.updateNicknameResponse(nickname);
        }
    }

    public void notifyStartingSetWorkerResponse(String row, String col, int worker)  {
        for(ClientObserver c : observersClient){
            c.updateStartingSetWorkerResponse(row, col, worker);
        }
    }

    public void notifyWrongCoordinatesUpdate(String row, String col, int worker)  {
        for(ClientObserver c : observersClient){
            c.updateWrongCoordinatesUpdate(row, col, worker);
        }
    }

    public void notifyChooseYourWorkerResponse(String worker) {
        for(ClientObserver c : observersClient){
            c.updateChooseYourWorkerResponse(worker);
        }
    }

    public void notifyMoveResponse(String row, String col, int worker, List<BoardCell> av) {
        for(ClientObserver c : observersClient){
            c.updateMoveResponse(row, col, worker, av);
        }
    }

    public void notifyBuildResponse(String row, String col, int worker, List<BoardCell> b)  {
        for(ClientObserver c : observersClient){
            c.updateBuildResponse(row, col, worker, b);
        }
    }

    public void notifyChosenCardsUpdate(String cardName)  {
        for(ClientObserver c : observersClient){
            c.updateChosenCardsUpdate(cardName);
        }
    }


    public void notifySetYourCardResponse(String in)  {
        for(ClientObserver c : observersClient){
            c.updateSetYourCardResponse(in);
        }
    }
    public void notifyAskEffectReply(String effect, String nickname) {
        for(ClientObserver c : observersClient){
            c.updateAskEffectReply(effect, nickname);
        }
    }
    public void notifyChooseYourWorkerEffectResponse(String worker, boolean effect)  {
        for(ClientObserver c : observersClient){
            c.updateChooseYourWorkerEffectResponse(worker, effect);
        }
    }

    public void notifyAskeffectBuildResponse(String effect, String nickname, int worker) {
        for(ClientObserver c : observersClient){
            c.updateAskeffectBuildResponse(effect, nickname, worker);
        }
    }


    public void notifyMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker)  {
        for(ClientObserver c : observersClient){
            c.updateMoveTwoInputResponse(row1, col1, row2,col2, worker);
        }
    }
    public void notifyBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker)  {
        for(ClientObserver c : observersClient){
            c.updateBuildTwoInputResponse(row1, col1, row2,col2, worker);
        }
    }

    public void notifyPlayerThatStart(String player, List <PlayerInterface> onlinePlayers)  {
        for(ClientObserver c : observersClient){
            c.updatePlayerThatStart(player,onlinePlayers);
        }
    }





}
