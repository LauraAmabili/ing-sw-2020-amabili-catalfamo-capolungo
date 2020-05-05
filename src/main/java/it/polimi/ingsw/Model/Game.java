package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.PlayerFSA.AddNickname;
import it.polimi.ingsw.Model.PlayerFSA.Initialized;
import it.polimi.ingsw.Model.PlayerFSA.PlayerFSA;
import org.jetbrains.annotations.NotNull;


import java.util.*;

public class Game extends Observable {

    //Player
    private int id;
    private List<String> nickNames; //in game players
    private List<PlayerInterface> onlinePlayers;
    private List<PlayerFSA> stateList = new ArrayList<>();
    private Turn currentTurn;
    private int counterId = 1;
    private Board board;
    private boolean cardsChosen = false;
    int maxPlayer;

    private List<String> godListNames = new ArrayList<>();
    public List<String> getGodListNames() {
        return godListNames;
    }

    public void initialiseGodList(){
        PlayerCreator playerCreator = new PlayerCreator();
        for (int i=0; i<playerCreator.getArrayGods().size(); i++)
            godListNames.add(playerCreator.getArrayGods().get(i).getGodName());
    }

    private List<String> chosenGods = new ArrayList<>();



    public Game() {
        nickNames = new ArrayList<>();
        onlinePlayers = new ArrayList<>();
    }


    public List<PlayerFSA> getStateList() {
        return stateList;
    }
    public List<String> getChosenGods() {
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
     * Delete the chosen player and all its workers
     * @param player the PlayerInterface to delete from the game
     */
    public void delPlayer(@NotNull PlayerInterface player){

        for (Worker x : player.getWorkerRef()){
            x.getCurCell().setWorker(null);
        }
        player.getWorkerRef().clear();
        onlinePlayers.remove(player);
        currentTurn.getActivePlayers().remove(player);

    }

    /**
     *Create a list of Worker to match the Player
     *Create a player
     *Create a Board
     */
    public void initialiseMatch(int numberOfPlayers) {

        maxPlayer = numberOfPlayers;
        List<Worker> list = new ArrayList<>();

        Board board = new Board();
        setBoard(board);

        //int MAXPLAYER = 3;
        for (int i = 0; i < numberOfPlayers; i++) {
            onlinePlayers.add(new Player());
        }
        for (PlayerInterface playerInterface : onlinePlayers) {
            for (int i = 0; i < 2; i++, counterId++) {
                Worker worker = new Worker(counterId);
                worker.setPlayerWorker(playerInterface);
                list.add(worker);
            }
            stateList.add(new AddNickname(playerInterface, this));
            playerInterface.setWorkerRef(list);
            playerInterface.setBoard(board);
            list.clear();
        }
        initialiseGodList();

    }

    /**
     * Create a turn with the online Players
     */
    public void createTurn() {
        Turn turn = new Turn(this.getOnlinePlayers());
        this.setCurrentTurn(turn);
        //this.getCurrentTurn().setCurrentPlayer(this.getOnlinePlayers().get(0));
        this.notifyGameIsRead();
    }


    /**
     * This method check if someone else has already chosen cards
     */
    public void chooseCards() {

        if(chosenGods.isEmpty()) {
            createChallenger();
            notifyChoose(cardsChosen, this.getGodListNames(), this.getCurrentTurn().getCurrentPlayer().getNickname());
        }
        else {
            notifyChoose(true, this.getGodListNames(), this.getCurrentTurn().getCurrentPlayer().getNickname());
        }

    }

    /**
     * This create the challenger by choosing a random number
     */
    public void createChallenger(){
        Random random = new Random();
        currentTurn.setCurrentPlayer(currentTurn.getActivePlayers().get(random.nextInt(currentTurn.getActivePlayers().size() - 1)));
        int i = onlinePlayers.indexOf(currentTurn.getCurrentPlayer());
        stateList.set(i, new Initialized(getCurrentTurn().getCurrentPlayer(), this));
    }

    /**
     * This notify the view that the God chosen is not in the list of the God that the Challenger chose
     */
    public void GodNotCorrectException(){
        notifyGodNotCorrect(chosenGods);
    }

    public void toSetCard() {
        this.notifyTimeToSetCard(getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void msgGodSet(String godName) {
        notifyGodSet(currentTurn.getCurrentPlayer().getNickname(), godName);
    }

    public void toPlaceWorker() {
        notifyTimeToPlaceWorker(getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void sameNameError() {
        notifyNicknameNotValid();
    }

    public void nameAccepted(String name) {
        notifyPlayerAdded(name);
    }

    public void NoGodHasSuchName() {
        notifyGodNotCorrect(chosenGods);
    }

    public void GodAlreadyChosen(String name) {
        notifyGodAlreadyChosen(name);
    }

    public void updateBoard() {
        notifyBoardUpdate(getBoard());
    }

    public void cellAlreadyOccupied(int worker) {
        notifyCellAlreadyOccupied(worker);
    }

    public void NoPossibleMoves(String name) {
        notifyPlayerHasLost(name);
    }

    public void updateWin(PlayerInterface p) {
        notifyWin(p);
    }

    public void updateWorkerSelected(int worker) {
        notifyWorkerSelected(worker);
    }

    public void NoCoordinatesValidMove(int worker) {
        notifyNoCoordinatesValid(worker);
    }

    public void timeToBuild(int worker) {
        notifyTimeToBuild(worker);
    }

    public void NoCoordinatesValidBuild(int worker) {
        notifyTryNewCoordinatesBuild(worker);
    }

    public void timeToCheckWorker() {
        notifyCanMove();
    }

    public void timeToMove(int worker) {
        notifyCanMoveThisWorker(worker);
    }

    public void timeToChallenger() {
        notifyCards(getGodListNames(), getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void godAdded(boolean state) {
        notifyGodAdded(chosenGods, state);
    }

    public void godNotAdded() {
        notifyGodNotAdded();
    }
}

