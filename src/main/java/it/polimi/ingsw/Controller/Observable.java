package it.polimi.ingsw.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Observable {



    private final List<Observer> observerController = new ArrayList<>();


    public void AddObserver(Observer o){
        this.observerController.add(o);
    }

    /**
     * update to initialise the match with the first setting
     * @param number number of players of the game
     *
     * @throws InterruptedException exception
     */
    public void notifyInitialiseMatch(int number) throws InterruptedException {
        for(Observer o : observerController){
            o.updateInitialiseMatch(number);

        }
    }

    /**
     * update to set the nickname
     * @param in nickname chosen by the client
     *
     */
    public void notifyAddingNickname(String in )  {
        for(Observer o : observerController){
            o.updateNickname(in);
        }
    }

    /**
     * update that it's time to choose the cards
     *
     */
    public void notifyChoosingCards()  {
        for(Observer o : observerController){
            o.updateChoosingCards();
        }

    }

    /**
     * update with the name of the god chosen bu the client to check if it's correct
     * @param godName name of the god chosen
     * @ excepton
     */
    public void notifyGodNameChosen(String godName)  {
        for(Observer o : observerController){
            o.updateSetGodName(godName);
        }
    }

    /**
     * update to add the worker in the coordinates chosen
     * @param row chosen row
     * @param col chosen col
     * @param i worker to add
     *
     */
    public void notifyAddingWorker(int row, int col, int i )  {
        for(Observer o : observerController){
            o.updateAddingWorker(row, col, i );
        }
    }

    /**
     * uptade to start the movement
     *
     */
    public void notifyStartMoving()  {
        for(Observer o : observerController){
            o.updateStartMoving();
        }

    }

    /**
     * Update for the controller with the worker to try
     * @param worker number of the worker
     *
     */
    public void notifyTryThisWorker(int worker)  {
        for(Observer o : observerController){
            o.updateTryThisWorker(worker);
        }
    }

    /**
     * update for the movement with the coordinates for the worker
     * @param row row chosen
     * @param col col chosen
     * @param worker number of the worker
     *
     */
    public void notifyMoving(int row, int col, int worker)  {
        for(Observer o : observerController){
            o.updateMoving(row, col, worker);
        }

    }

    /**
     * Update for the controller to try if the card chosen is correct
     * @param in name of the card
     *
     */
    public void notifyTryThisCard(String in)  {
        for(Observer o : observerController){
            o.updateTryThisCard(in);
        }
    }

    /**
     * Uptade with the coordinates of the build
     * @param row row chosen
     * @param col con chosen
     * @param i number of the worker
     *
     */

    public void notifyBuilding(int row, int col, int i )  {
        for(Observer o : observerController){
            o.updateBuilding(row, col, i );
        }

    }

    /**
     * update that the game is ready to start
     * @
     * @throws InterruptedException
     */
    public void notifyStartingGame()  throws InterruptedException {
        for(Observer o : observerController){
            o.updateStartingGame();
        }
    }

    /**
     * Update for the check of the effect for the worker
     * @param effect effect of the player
     * @param worker number of the worker
     */
    public void notifyTryThisWorkerEffect(boolean effect,int worker)  {
        for(Observer o : observerController){
            o.updateTryThisWorkerEffect(effect, worker);
        }
    }

    /**
     * Update for the special build of the player with a build effect
     * @param effect effect of the player
     * @param nickname nickname of the player
     * @param worker number of the worker
     *
     */
    public void notifyPlayerBuild(boolean effect, String nickname, int worker)  {
        for(Observer o : observerController){
            o.updatePlayerBuild(effect, nickname, worker);
        }
    }

    /**
     * update with the double coordinates for the moving with two input
     * @param row1 first row chosen
     * @param col1 first col chosen
     * @param row2 second row chosen
     * @param col2 second col chosen
     * @param worker number of the worker
     *
     */
    public void notifyTimeToMoveTwoInput(int row1, int col1, int row2, int col2, int worker) {
        for(Observer o : observerController){
            o.updateTimeToMoveTwoInput(row1, col1, row2, col2, worker);
        }
    }


    /**
     * update with the double coordinates for the building with two input
     * @param row1 first row chosen
     * @param col1 first col chosen
     * @param row2 second row chosen
     * @param col2 second col chosen
     * @param worker number of the worker
     *
     */
    public void notifyTimeToBuildTwoInput(int row1, int col1, int row2, int col2, int worker)  {
        for(Observer o : observerController){
            o.updateTimeToBuildTwoInput(row1, col1, row2, col2, worker);
        }
    }


    /**
     * Update with the name of the player that dropped the conneciton
     * @param nickname name of the player disconnected
     *
     */
    public void notifyDropConnection(String nickname)  {
        for(Observer o : observerController){
            o.updateDropConnection(nickname);
        }
    }

    /**
     * Update with the name of the first Player chosen by the challenger
     * @param player name of the player
     */
    public void notifyFirstPlayer(int player) {
        for(Observer o : observerController){
            o.updateFirstPlayer(player);
        }
    }







}
