package it.polimi.ingsw.Model;

import it.polimi.ingsw.Exceptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Game extends Observable {

    //Player
    private int id;
    private List<String> nickNames; //in game players
    private List<PlayerInterface> onlinePlayers;
    private Turn currentTurn;
    private int counterId = 1;
    private Board board;
    private int cardsChosen = 0;
    private List<String> godListNames = new ArrayList<>();
    public List<String> getGodListNames() {
        return godListNames;
    }

    public void initialiseGodList(){

        godListNames.add("Apollo");
        godListNames.add("Artemis");


    }
    private List<God> chosenGods = new ArrayList<>();



    public Game() {
        nickNames = new ArrayList<>();
        onlinePlayers = new ArrayList<>();
    }



    public List<God> getChosenGods() {
        return chosenGods;
    }
    public Board getBoard() {
        return board;
    }
    public void setBoard(Board board) {
        this.board = board;
    }
    public List<PlayerInterface> getOnlinePlayers() {
        return onlinePlayers;
    }
    public List<String> getNicknames() {
        return nickNames;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Turn getCurrentTurn() {
        return currentTurn;
    }
    public void setCurrentTurn(Turn currentTurn) {
        this.currentTurn = currentTurn;
    }
    public String getPlayerNickname(int num){
        return getOnlinePlayers().get(num).getNickname();
    }



    /**
     * Delete the chosen player and all his workers
     * @param player
     */
    public void delPlayer(@NotNull PlayerInterface player){
        for(int i = player.getWorkerRef().size(); i > 0; i--) {
            player.getWorkerRef().get(i).getCurCell().setWorker(null);
            player.getWorkerRef().remove(player.getWorkerRef().get(i));
        }
        onlinePlayers.remove(player);
        currentTurn.getActivePlayers().remove(player);
    }

    /**
     *Create a list of Worker to match the Player
     *Create a player
     *Create a Board
     */
    public void initialiseMatch() { //TODO: add player
        List<Worker> list = new ArrayList<>();
        Board board = new Board();
        setBoard(board);
        int MAXPLAYER = 3;
        for (int i = 0; i < MAXPLAYER; i++) {
            onlinePlayers.add(new Player());
        }
        for (PlayerInterface playerInterface : onlinePlayers) {
            for (int i = 0; i < 2; i++, counterId++) {
                Worker worker = new Worker(counterId);
                worker.setPlayerWorker(playerInterface);
                list.add(worker);
            }
            playerInterface.setWorkerRef(list);
            playerInterface.setBoard(board);
            list.clear();
        }
       initialiseGodList();

    }

    /**
     * Check if the Worker won
     * @param worker
     */
    public void win(@NotNull Worker worker) {
        if(worker.getPlayerWorker().checkWin(worker) || onlinePlayers.size() == 1) {
            System.out.println(worker.getPlayerWorker().getNickname() + "wins");
        }
    }

    /**
     * Add nickname chosen to the list of OnlinePlayers
     * @param nickName
     */
    public void addNickname(String nickName) {

       //if lista nomi contains nome return false, se return false devi rifare il metodo

       //this.getCurrentTurn().getCurrentPlayer().getPlayerState().addNickname(nickName);
        this.getCurrentTurn().getCurrentPlayer().setNickname(nickName);
        this.notifyPlayerAdded(nickName);
       //this.getCurrentTurn().nextTurn();

    }

    /**
     * Create a turn with the online Players
     */
    public void createTurn() {
        Turn turn = new Turn(this.getOnlinePlayers());
        this.setCurrentTurn(turn);
        this.getCurrentTurn().setCurrentPlayer(this.getOnlinePlayers().get(0));
        this.notifyGameIsRead();
    }

    /**
     * Set the chosen god as ActiveGodCard
     * Remove the chosenGod from the list of ChosenGods
     * @param godName
     */
    public void setGod(String godName) {

        PlayerCreator playerCreator = new PlayerCreator();
        God god = new God(godName, null);
        chosenGods.remove(god);

        this.getCurrentTurn().getCurrentPlayer().setActiveCard(god);
        //this.getCurrentTurn().getCurrentPlayer().getPlayerState().setCard(god);
        this.notifyGodSetted(this.getCurrentTurn().getCurrentPlayer(), godName);
       //this.getCurrentTurn().nextTurn();

    }

    public void addChosenGods(String godName){

        //TODO: in base al nome del god creo il god da file e lo aggiungo alla chosenGods

        God god = new God(godName, null);
        if(this.getGodListNames().contains(godName)) {
            chosenGods.add(god);
            if (chosenGods.size() == this.getOnlinePlayers().size()) {
               // this.getCurrentTurn().getCurrentPlayer().getPlayerState().chosenCards(chosenGods);
                this.getCurrentTurn().getCurrentPlayer().setActiveCard(god);
                //this.getCurrentTurn().nextTurn();
            }
            notifyGodAdded(this.getChosenGods());

        }
        else {
            //notifyCardsChosen(chosenGods, cardsChosen, true);

            notifyExc();

        }

    }

    /**
     * if CardsChosen = 0 : if no cards were chosen
     *
     */
    public void chooseCards() {


            if (cardsChosen == 0) {
                List gods = this.getGodListNames();
                this.notifyCards(gods); //send list of Cards to the View
                cardsChosen = 1;
                this.notifyCardsChosen(chosenGods, cardsChosen, this.getCurrentTurn().getActivePlayers().size());
            } else {
                cardsChosen = 2;

                this.notifyCardsChosen(chosenGods, cardsChosen, this.getCurrentTurn().getActivePlayers().size());
                //this.notifyCardsChosen(this.getCurrentTurn().getCurrentPlayer().getChosenGods(), cardsChosen);
            }

    }

    /**
     * This notify sends the view a general exception.
     */
    public void notifyExc(){

        this.notifyObservers(null, null);
    }

    public void notifyGodNotPresent(){

        this.notifyCardsChosen(chosenGods, cardsChosen, this.getCurrentTurn().getActivePlayers().size());
    }

    public void addingWorker(int row, int col, int i){

        if(this.getCurrentTurn().getCurrentPlayer().addWorker(row-1, col-1,this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i)))

            this.notifyBoardUpdate(this.getBoard());

        else {
            if(i == 0) {
                i = -1;
            } else {
                i = 0;
            }
            notifyCellAlreadyOccupied(i);
        }
        //this.getCurrentTurn().getCurrentPlayer().getPlayerState().placeWorker(row, col, this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i));


    }

    public void canIMove(){


        int i = 0;
        if(this.getCurrentTurn().checkLockPlayer(this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i)) && this.getCurrentTurn().checkLockPlayer(this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i+1)) ){
            notifyCanMove(null);
        }
        else {
            //TODO: notifyPlayerJustLose
        }


    }

    public void checkWorker(int worker) {

        if (this.getCurrentTurn().checkLockPlayer(this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
            if (worker == 2) {
                worker = 1;
            } else {
                worker++;
            }
            notifyCanMove(worker);
        }
    }

    public void moving(int row, int col, int worker){

        if(!this.getCurrentTurn().getCurrentPlayer().move(row - 1, col - 1, this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker-1))){
            notifyTryNewCoordinatesMove(false);
        }
        else    {
            notifyBoardUpdate(this.board);
        }


    }

    public void building(int row, int col, int worker){

        if(!this.getCurrentTurn().getCurrentPlayer().build(row - 1, col - 1, this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker-1))){
            notifyTryNewCoordinatesBuild(false);
        }
        else    {

            notifyBoardUpdate(this.board);
        }


    }

    /**
     * This notify the view that the God chosen is not in the list of the God that the Challenger chose
     */
    public void GodNotCorrectException(){

        notifyGodNotCorrect(chosenGods);

    }



}

