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
    private final boolean cardsChosen = false;
    private final List<String> color;
    int maxPlayer;
    private List<God> allGods;

    /**
     * List of the chosenGods of the challenger
     */
    private List<God> chosenGodList;
    /**
     * List of che gods still available for th client
     */
    private final List<String> availableGods = new ArrayList<>();
    public List<God> getChosenGodList() {
        return chosenGodList;
    }



    private final List<String> godListNames = new ArrayList<>();
    public List<String> getGodListNames() {
        return godListNames;
    }

    /**
     * Create the list of gods
     */
    public void initialiseGodList(){
        PlayerCreator playerCreator = new PlayerCreator();
        for (int i=0; i<playerCreator.getArrayGods().size(); i++)
            godListNames.add(playerCreator.getArrayGods().get(i).getGodName());
    }

    //constructor
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
        PlayerCreator playerCreator = new PlayerCreator();
        allGods = playerCreator.getArrayGods();
        chosenGodList = new ArrayList<>();
    }


    public List<God> getAllGods() {
        return allGods;
    }
    public List<PlayerFSA> getStateList() {
        return stateList;
    }
    public List<String> getAvailableGods() {
        return availableGods;
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
    public void createTurn() {
        Turn turn = new Turn(this.getOnlinePlayers());
        this.setCurrentTurn(turn);
    }


    /**
     * This method check if someone else has already chosen cards
     */
    public void chooseCards() throws IOException {

        if(availableGods.isEmpty()) {
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
    public void createChallenger() {

        Random random = new Random();
        currentTurn.setCurrentPlayer(currentTurn.getActivePlayers().get(random.nextInt(currentTurn.getActivePlayers().size() - 1)));
        int i = onlinePlayers.indexOf(currentTurn.getCurrentPlayer());
        stateList.set(i, new Initialized(getCurrentTurn().getCurrentPlayer(), this));

    }

    /**
     * Calls the first notify to tell oll the observer that it's time to Set the card, the second one
     * to tell the clients that the current player is setting the card
     * @throws IOException Exception for the Message
     */
    public void toSetCard() throws IOException {
        this.notifyTimeToSetCard(availableGods, getCurrentTurn().getCurrentPlayer().getNickname());
        this.notifySetCard(availableGods, getCurrentTurn().getCurrentPlayer().getNickname(), chosenGodList);

    }

    /**
     * notify the observers that the board is updated by the current player
     * @throws IOException
     */
    public void BoardWorkerUpdate() throws IOException {

        notifyWorkerBoardUpdate(board, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * notify the observers that the card was set
     * @param godName name of the god just set
     * @throws IOException
     */
    public void msgGodSet(String godName) throws IOException {
        notifyGodSet(currentTurn.getCurrentPlayer(), godName);
    }

    /**
     * notify observers that it's time for the current player to place workers
     * @throws IOException Exception for the Message
     */
    public void toPlaceWorker() throws IOException {
        notifyTimeToPlaceWorker(getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * notify the observers that the name inserted is not valid because someone else took it
     * @param nickname nickname that is not valid
     * @throws IOException Exception for the message
     */
    public void sameNameError(String nickname) throws IOException {
        notifyNicknameNotValid(nickname);
    }


    /**
     * calls a notify to the observers to say that the name was added
     * @param name name just added
     * @throws IOException Exception for the Message
     */
    public void nameAccepted(String name) throws IOException {

        notifyPlayerAdded(name);
    }

    /**
     * calls a Notify to tell that the god chosen is not present in the list of gods
     * @throws IOException Exception for the Message
     */
    public void NoGodHasSuchName() throws IOException {
        notifyGodNotCorrect(this.getCurrentTurn().getCurrentPlayer().getNickname(), availableGods, chosenGodList);
    }

    /**
     * notify with the update of the Board
     * @throws IOException
     */
    public void updateBoard() throws IOException {
        notifyBoardUpdate(board);
    }

    /**
     * calls a notify on all the observers to say that the chosen cell is already occupied
     * @param worker num of the worker that the player is trying to set
     * @throws IOException Exception for the message
     */
    public void cellAlreadyOccupied(int worker) throws IOException {
        notifyCellAlreadyOccupied(worker, getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * calls a notify to all the observers to say that the Player has just lost because both his workers cannot move
     * @param name nae of the player
     * @throws IOException Exception for the message
     */
    public void NoPossibleMoves(String name) throws IOException {
        notifyPlayerHasLost(name);
    }

    /**
     * calls a notify to all the observers to say that the player wins the game
     * @param p player that is winning
     * @throws IOException Exception for the message
     */
    public void updateWin(PlayerInterface p) throws IOException {
        notifyWin(p);
    }

    /**
     * calls a notify to all the observer that the worker selected is set
     * @param worker number of the chosen worker
     * @throws IOException Exception for the message
     */
    public void updateWorkerSelected(int worker) throws IOException {
        notifyWorkerSelected(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * calls a notify to all the observer to say that the coordinates chosen to move the worker are not valid
     * @param worker number of the worker
     * @throws IOException Exception for the message
     */
    public void NoCoordinatesValidMove(int worker) throws IOException {
        notifyNoCoordinatesValid(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * calls a notify to all the observer to ask the current player if he wants the effect, only if h has a card with the special effect
     * @param worker worker to build around
     * @throws IOException Exception for the message
     */
    public void askEffectBuild(int worker) throws IOException {
        if(getCurrentTurn().getCurrentPlayer().isHasSpecialBuild()) {
            notifyAskForEffectBuild(currentTurn.getCurrentPlayer().getNickname(), worker);
        } else {
            notifyTimeToBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
        }
    }

    //TODO: javadoc
    public void timeToBuild(int worker) throws IOException {
        for(int i = 0; i < getOnlinePlayers().size(); i++) {
            if (getOnlinePlayers().get(i).equals(getCurrentTurn().getCurrentPlayer())) {
                if(getStateList().get(i).getEffect()) {
                    if (getCurrentTurn().getCurrentPlayer().isHasTwoInputBuild()) {
                        notifyTimeToBuildTwoInput(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                    } else {
                        notifyTimeToBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                    }
                } else {
                    notifyTimeToBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                }
                break;
            }
        }
    }

    /**
     * calls a notify to all the observer to say that the coordinates to build on are not valid
     * @param worker number of the worker
     * @throws IOException exception for the message
     */
    public void NoCoordinatesValidBuild(int worker) throws IOException {
        notifyTryNewCoordinatesBuild(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * calls a notify to all the observer to say that the worker can move
     * @throws IOException Exception for the message
     */
    public void timeToCheckWorker() throws IOException {
        notifyCanMove(this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    //TODO: javadoc
    public void timeToMove(int worker) throws IOException {
        for(int i = 0; i < getOnlinePlayers().size(); i++) {
            if (getOnlinePlayers().get(i).equals(getCurrentTurn().getCurrentPlayer())) {
                if (getStateList().get(i).getEffect()) {
                    if (getCurrentTurn().getCurrentPlayer().isHasTwoInputMove()) {
                        notifyTimeToMoveTwoInput(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                    } else {
                        notifyCanMoveThisWorker(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                    }
                } else {
                    notifyCanMoveThisWorker(worker, this.getCurrentTurn().getCurrentPlayer().getNickname());
                }
                break;
            }
        }

    }

    /**
     * calls a notify to all the observer to say that the challenger must choose the cards for the game
     * @throws IOException Exception for the message
     */
    public void timeToChallenger() throws IOException {
        notifyCards(getCurrentTurn().getCurrentPlayer().getNickname());
        notifyChoose(cardsChosen,this.getAllGods(), this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * calls a notify to all the observer to say that the god chosen by the challenger is added correctly
     * @param state boolean to set if the cards are all chosen
     * @throws IOException exception for the message
     */
    public void godAdded(boolean state) throws IOException {

        notifyGodAdded(availableGods, state, this.getCurrentTurn().getCurrentPlayer().getNickname());
        //System.out.println(availableGods);

    }

    /**
     * calls a notify to all the observer to say that the god chosen by the challenger
     * is not added because it was not correct
     * @throws IOException exception for the message
     */
    public void godNotAdded() throws IOException {
        notifyGodNotAdded(this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

    /**
     * sends first a notify to say to all the observer that it's time for the current player to move,
     * then choose the notify to ask for the effect if the current player has the special effect,
     * otherwise sends the notify to choose for the worker to move
     * @throws IOException exception for the message
     */
    public void toMoving() throws IOException {
        notifyStartMoving(this.getCurrentTurn().getCurrentPlayer().getNickname());
        if(getCurrentTurn().getCurrentPlayer().isHasSpecialMove()) {
            notifyAskForEffect(currentTurn.getCurrentPlayer().getNickname());
        } else {
            notifyChooseWorker(this.getCurrentTurn().getCurrentPlayer().getNickname());
        }
    }

    public void sendDropConnection(String nickname) throws IOException {
        notifyDroppedConnection(nickname);
    }
    /*
    public void anotherRound(){
        notifyStartMoving(this.getCurrentTurn().getCurrentPlayer().getNickname());
        notifyChooseWorker(this.getCurrentTurn().getCurrentPlayer().getNickname());
    }

     */

    public void setFirstPlayer() throws IOException {
        notifySetFirstPlayer(this.getCurrentTurn().getCurrentPlayer().getNickname(), onlinePlayers);
    }
}

