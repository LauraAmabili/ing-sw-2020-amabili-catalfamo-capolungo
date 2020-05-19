package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Model.PlayerFSA.AddNickname;
import it.polimi.ingsw.Model.PlayerFSA.Initialized;
import it.polimi.ingsw.Model.PlayerFSA.PlayerFSA;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;
import java.util.*;

public class Game extends Observable {

    //Player
    private int id;
    private List<String> nickNames; //in game players
    private List<PlayerInterface> onlinePlayers;
    private List<PlayerFSA> stateList;
    private Turn currentTurn;
    private int counterId = 1;
    private Board board;
    private boolean cardsChosen = false;
    private List<String> color;
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
        stateList = new ArrayList<>();
        color = new ArrayList<>();
        String ANSI_YELLOW = "\u001B[33m";
        String ANSI_BLUE = "\u001B[34m";
        String ANSI_PURPLE = "\u001B[35m";
        color.add(ANSI_BLUE);
        color.add(ANSI_YELLOW);
        color.add(ANSI_PURPLE);
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
                worker.setColor(color.get(0));
                worker.setPlayerWorker(playerInterface);
                list.add(worker);
            }
            color.remove(0);
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
    public void createTurn() throws IOException, InterruptedException {
        Turn turn = new Turn(this.getOnlinePlayers());
        this.setCurrentTurn(turn);
    }


    /**
     * This method check if someone else has already chosen cards
     */
    public void chooseCards() throws IOException {

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

    public void toSetCard() throws IOException {
        this.notifyTimeToSetCard(chosenGods, getCurrentTurn().getCurrentPlayer().getNickname());
        this.notifySetCard(chosenGods, getCurrentTurn().getCurrentPlayer().getNickname());

    }

    public void BoardWorkerUpdate() throws IOException {

        notifyWorkerBoardUpdate(board, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void msgGodSet(String godName) throws IOException {
        notifyGodSet(currentTurn.getCurrentPlayer().getNickname(), godName);
    }

    public void toPlaceWorker() throws IOException {
        notifyTimeToPlaceWorker(getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void sameNameError(String nickname) throws IOException {
        notifyNicknameNotValid(nickname);
    }

    public void nameAccepted(String name) throws IOException {
        //String color = this.getCurrentTurn().getCurrentPlayer().getWorkerRef().get(0).getColor();
        notifyPlayerAdded(name);
    }

    public void NoGodHasSuchName() throws IOException {
        notifyGodNotCorrect(this.getCurrentTurn().getCurrentPlayer().getNickname(), chosenGods);
    }

    public void updateBoard() throws IOException {
        notifyBoardUpdate(board);
    }

    public void cellAlreadyOccupied(int worker) throws IOException {
        notifyCellAlreadyOccupied(worker, getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void NoPossibleMoves(String name) throws IOException {
        notifyPlayerHasLost(name);
    }

    public void updateWin(PlayerInterface p) throws IOException {
        notifyWin(p);
    }

    public void updateWorkerSelected(int worker) throws IOException {
        notifyWorkerSelected(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void NoCoordinatesValidMove(int worker) throws IOException {
        notifyNoCoordinatesValid(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void askEffectBuild(int worker) throws IOException {
        if(getCurrentTurn().getCurrentPlayer().isHasSpecialBuild()) {
            notifyAskForEffectBuild(currentTurn.getCurrentPlayer().getNickname(), worker);
        } else {
            notifyTimeToBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
        }
    }

    public void timeToBuild(int worker) throws IOException {
        for(int i = 0; i < getOnlinePlayers().size(); i++) {
            if (getOnlinePlayers().get(i).equals(getCurrentTurn().getCurrentPlayer())) {
                if(getStateList().get(i).getEffect()) {
                    if (getCurrentTurn().getCurrentPlayer().isHasTwoInputBuild()) {
                        notifyTimeToBuildTwoInput(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                    }
                } else {
                    notifyTimeToBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                }
                break;
            }
        }
    }

    public void NoCoordinatesValidBuild(int worker) throws IOException {
        notifyTryNewCoordinatesBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void timeToCheckWorker() throws IOException {
        notifyCanMove(this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void timeToMove(int worker) throws IOException {
        for(int i = 0; i < getOnlinePlayers().size(); i++) {
            if (getOnlinePlayers().get(i).equals(getCurrentTurn().getCurrentPlayer())) {
                if (getStateList().get(i).getEffect()) {
                    if (getCurrentTurn().getCurrentPlayer().isHasTwoInputMove()) {
                        notifyTimeToMoveTwoInput(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                    }
                }
                notifyCanMoveThisWorker(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                break;
            }
        }

    }

    public void timeToChallenger() throws IOException {
        notifyCards(getCurrentTurn().getCurrentPlayer().getNickname());
        notifyChoose(cardsChosen,this.getGodListNames(), this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void godAdded(boolean state) throws IOException {

        notifyGodAdded(chosenGods, state, this.getCurrentTurn().getCurrentPlayer().getNickname());
        System.out.println(chosenGods);

    }

    public void godNotAdded() throws IOException {
        notifyGodNotAdded(this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    public void toMoving() throws IOException {
        notifyStartMoving(this.getCurrentTurn().getCurrentPlayer().getNickname());
        if(getCurrentTurn().getCurrentPlayer().isHasSpecialMove()) {
            notifyAskForEffect(currentTurn.getCurrentPlayer().getNickname());
        } else {
            notifyChooseWorker(this.getCurrentTurn().getCurrentPlayer().getNickname());
        }
    }
    /*
    public void anotherRound(){
        notifyStartMoving(this.getCurrentTurn().getCurrentPlayer().getNickname());
        notifyChooseWorker(this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

     */
}

