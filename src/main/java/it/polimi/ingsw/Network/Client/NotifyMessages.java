package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.ObserverModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NotifyMessages {


    private List<ClientObserver> observersClient = new ArrayList<>();
    public void addObserver(ClientObserver o){
        this.observersClient.add(o);
    }
    public void removeObserver(ClientObserver o) {
        this.observersClient.remove(o);
    }

    public void notifyPlayerNumberResponse(String num) throws IOException {
        for(ClientObserver c : observersClient){
            c.updatePlayerNumberResponse(num);
        }
    }

    public void notifyNicknameResponse(String nickname) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateNicknameResponse(nickname);
        }
    }

    public void notifyStartingSetWorkerResponse(String row, String col, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateStartingSetWorkerResponse(row, col, worker);
        }
    }

    public void notifyWrongCoordinatesUpdate(String row, String col, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateWrongCoordinatesUpdate(row, col, worker);
        }
    }

    public void notifyChooseYourWorkerResponse(String worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateChooseYourWorkerResponse(worker);
        }
    }

    public void notifyMoveResponse(String row, String col, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateMoveResponse(row, col, worker);
        }
    }

    public void notifyBuildResponse(String row, String col, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateBuildResponse(row, col, worker);
        }
    }

    public void notifyChosenCardsUpdate(String cardName) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateChosenCardsUpdate(cardName);
        }
    }


    public void notifySetYourCardResponse(String in) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateSetYourCardResponse(in);
        }
    }
    public void notifyAskEffectReply(String effect, String nickname) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateAskEffectReply(effect, nickname);
        }
    }
    public void notifyChooseYourWorkerEffectResponse(String worker, boolean effect) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateChooseYourWorkerEffectResponse(worker, effect);
        }
    }

    public void notifyAskeffectBuildResponse(String effect, String nickname, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateAskeffectBuildResponse(effect, nickname, worker);
        }
    }


    public void notifyMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateMoveTwoInputResponse(row1, col1, row2,col2, worker);
        }
    }
    public void notifyBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException {
        for(ClientObserver c : observersClient){
            c.updateBuildTwoInputResponse(row1, col1, row2,col2, worker);
        }
    }





}
