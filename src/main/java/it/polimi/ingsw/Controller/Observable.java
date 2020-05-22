package it.polimi.ingsw.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Observable {



    private final List<Observer> observerController = new ArrayList<>();


    public void AddObserver(Observer o){
        this.observerController.add(o);
    }


    public void notifyInitialiseMatch(int number) throws IOException, InterruptedException {
        for(Observer o : observerController){
            o.updateInitialiseMatch(number);

        }
    }
    public void notifyAddingNickname(String in ) throws IOException {
        for(Observer o : observerController){
            o.updateNickname(in);
        }
    }
    public void notifyChoosingCards() throws IOException {
        for(Observer o : observerController){
            o.updateChoosingCards();
        }

    }

    public void notifyGodNameChosen(String godName) throws IOException {
        for(Observer o : observerController){
            o.updateSetGodName(godName);
        }
    }

    public void notifyAddingWorker(int row, int col, int i ) throws IOException {
        for(Observer o : observerController){
            o.updateAddingWorker(row, col, i );
        }
    }


    public void notifyStartMoving() throws IOException {
        for(Observer o : observerController){
            o.updateStartMoving();
        }

    }

    public void notifyTryThisWorker(int worker) throws IOException {
        for(Observer o : observerController){
            o.updateTryThisWorker(worker);
        }
    }

    public void notifyMoving(int row, int col, int worker) throws IOException {
        for(Observer o : observerController){
            o.updateMoving(row, col, worker);
        }


    }

    public void notifyTryThisCard(String in) throws IOException {
        for(Observer o : observerController){
            o.updateTryThisCard(in);
        }
    }


    public void notifyBuilding(int row, int col, int i ) throws IOException {
        for(Observer o : observerController){
            o.updateBuilding(row, col, i );
        }

    }

    public void notifyStartingGame() throws IOException, InterruptedException {
        for(Observer o : observerController){
            o.updateStartingGame();
        }
    }

    public void notifyTryThisWorkerEffect(boolean effect,int worker) throws IOException {
        for(Observer o : observerController){
            o.updateTryThisWorkerEffect(effect, worker);
        }
    }

    public void notifyPlayerBuild(boolean effect, String nickname, int worker) throws IOException {
        for(Observer o : observerController){
            o.updatePlayerBuild(effect, nickname, worker);
        }
    }

    public void notifyTimeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for(Observer o : observerController){
            o.updateTimeToMoveTwoInput(row1, col1, row2, col2, worker);
        }
    }

    public void notifyTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker) throws IOException {
        for(Observer o : observerController){
            o.updateTimeToBuildTwoInput(row1, col1, row2, col2, worker);
        }
    }

    public void notifyDropConnection(String nickname) {
        for(Observer o : observerController){
            o.updateDropConnection(nickname);
        }
    }







}
