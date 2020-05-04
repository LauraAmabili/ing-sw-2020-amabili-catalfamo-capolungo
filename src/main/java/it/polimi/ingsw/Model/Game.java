package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.PlayerFSA.AddNickname;
import it.polimi.ingsw.Model.PlayerFSA.Initialized;
import org.jetbrains.annotations.NotNull;


import java.util.*;

public class Game extends Observable {

    //Player
    private int id;
    private List<String> nickNames; //in game players
    private List<PlayerInterface> onlinePlayers;
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
            onlinePlayers.get(i).setPlayerState(new AddNickname(onlinePlayers.get(i), this));
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
     * @param nickName nickname of the Player
     */
    public synchronized void addNickname(String nickName) {

        //TODO: menage when number of player = 2 bt someone else tries to connect, we can a set a global variable when we initialise the match
        boolean flag = true;
        for(PlayerInterface p : onlinePlayers) {
            if (p.getNickname() == null) {
                break;
            } else {
                if(p.getNickname().equals(nickName)) {
                    flag = false;
                    notifyNicknameNotValid();
                    break;
                }
            }
        }
        if(flag) {
            for(PlayerInterface p : onlinePlayers) {
                if(p.getNickname() == null) {
                    p.setNickname(nickName);
                    this.nickNames.add(nickName);
                    break;
                }
            }
            //TODO: assegnare player a view
            this.notifyPlayerAdded(nickName);
            if(maxPlayer == nickNames.size()) {
                createChallenger();
                notifyCards(this.getGodListNames(), getCurrentTurn().getCurrentPlayer().getNickname());
            }
           // this.getCurrentTurn().nextTurn();
        }


       //this.getCurrentTurn().getCurrentPlayer().getPlayerState().addNickname(nickName);


       //this.getCurrentTurn().nextTurn();

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
     * Set the chosen god as ActiveGodCard
     * Remove the chosenGod from the list of ChosenGods
     * @param godName name of the god
     */
    public void setGod(String godName) {

        PlayerCreator playerCreator = new PlayerCreator();

        God god = new God(godName, null);

        for (God g : chosenGods) {
            if(g.getGodName().equals(godName)) {
                chosenGods.remove(g);
                break;
            }
        }

        getCurrentTurn().getCurrentPlayer().setActiveCard(god);

        PlayerInterface p1 = this.getCurrentTurn().getCurrentPlayer();
        p1 = playerCreator.createPlayer(godName, p1);

        getCurrentTurn().setCurrentPlayer(p1);

        //TODO: controllare se funziona

        for(int i= 0; i < this.getCurrentTurn().getActivePlayers().size(); i++){
            if(this.getCurrentTurn().getActivePlayers().get(i).getNickname().equals(p1.getNickname())){
                getCurrentTurn().getActivePlayers().set(i, p1);
                onlinePlayers.set(i, p1);
                break;
            }
        }

        //this.getCurrentTurn().getCurrentPlayer().getPlayerState().setCard(god);
        this.notifyGodSetted(this.getCurrentTurn().getCurrentPlayer(), godName);


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
     * Check if the GodName input is correct by checking if he is written correctly
     * @param godName
     */
    public boolean checkAndAdd(String godName) {

        boolean flag = true;
        God god = new God(godName, null);
        if(this.getGodListNames().contains(godName)) {
            if(chosenGods.isEmpty()) {
                chosenGods.add(god);
            } else {
                for (God g : chosenGods) {
                    if (g.getGodName().equals(godName)) {
                        flag = false;
                        notifyGodNotAdded();
                        break;
                    }
                }
                if(flag){
                    chosenGods.add(god);
                }
            }
            if (chosenGods.size() == this.getCurrentTurn().getActivePlayers().size()) {
                cardsChosen = true;
                notifyGodAdded(this.getChosenGods(), cardsChosen);
                return true;
            } else {
                notifyGodAdded(this.getChosenGods(), cardsChosen);
                return false;
            }
        }
        else {
            notifyGodNotAdded();
            return false;
        }



    }

    /**
     * This notify sends the view a general exception.
     */
    public void notifyExc(){

        this.notifyObservers(null, null);
    }

    /**
     * This method add first workers in the board
     * @param row row of the board
     * @param col col of the board
     * @param i reference to the worker to add
     * @return
     */
    public boolean addingWorker(int row, int col, int i) {

        if(this.getCurrentTurn().getCurrentPlayer().addWorker(row - 1, col - 1,this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i))) {
            this.notifyBoardUpdate(this.getBoard());
            return i == 1;
        }
        else {
            notifyCellAlreadyOccupied(i);
            return false;
        }
        //this.getCurrentTurn().getCurrentPlayer().getPlayerState().placeWorker(row, col, this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i));
    }

    /**
     * Checking if the player can move both his workers, otherwise he loose
     */
    public void canIMove(){

        int i = 0;
        if(!this.getCurrentTurn().checkLockPlayer(this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i)) && !this.getCurrentTurn().checkLockPlayer(this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(i+1)) ){
            notifyCanMove(true);
        }
        else {
            notifyPlayerHasLost(this.getCurrentTurn().getCurrentPlayer().getNickname());
            delPlayer(currentTurn.getCurrentPlayer());
            //TODO: disconnettere player + toglierlo dagli observer
        }

    }

    /**
     * Check if the worker can move around at least in one position
     * @param worker worker to check
     */
    public void checkWorker(int worker) {

        if (!this.getCurrentTurn().checkLockPlayer(this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))) {
        //TODO: if i choose worker number 1, it moves worker #2
            //TODO: try to add list of movements
            notifyCanMoveThisWorker(worker);
        }

    }

    /**
     * Must check if it has written the right coordinates, if so we send the Board Updated
     * @param row row of the board to check
     * @param col col of the board to check
     * @param worker worker to move
     */
    public void moving(int row, int col, int worker){

        if(!this.getCurrentTurn().getCurrentPlayer().move(row - 1, col - 1, this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker-1))){
            notifyTryNewCoordinatesMove(worker);
        }
        else    {
            notifyBoardUpdate(this.board);
        }


    }

    /**
     * Check if he can build in this position
     * @param row row to build on
     * @param col col to build on
     * @param worker worker to build around
     */
    public boolean building(int row, int col, int worker) {

        if(!this.getCurrentTurn().getCurrentPlayer().build(row - 1, col - 1, this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(worker - 1))){
            notifyTryNewCoordinatesBuild(false, worker);
            return false;
        }
        else {
            notifyBoardUpdate(this.board);
            return true;

        }


    }

    /**
     * This notify the view that the God chosen is not in the list of the God that the Challenger chose
     */
    public void GodNotCorrectException(){

        notifyGodNotCorrect(chosenGods);

    }

    /**
     * This create the challenger by choosing a random number
     */
    public void createChallenger(){
        Random random = new Random();
        currentTurn.setCurrentPlayer(currentTurn.getActivePlayers().get(random.nextInt(currentTurn.getActivePlayers().size() - 1)));
        currentTurn.getCurrentPlayer().setPlayerState(new Initialized(getCurrentTurn().getCurrentPlayer(), this));
    }


    public void askingToBuild(int worker){

        //notifyCanBuild(null, worker);

    }
    public void toSetCard() {
        this.notifyTimeToSetCard(getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void toPlaceWorker() {
        notifyTimeToPlaceWorker(getCurrentTurn().getCurrentPlayer().getNickname());
    }

}

