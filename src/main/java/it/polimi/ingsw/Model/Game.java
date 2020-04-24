package it.polimi.ingsw.Model;

import it.polimi.ingsw.Exceptions.GameIsAlreadyStarted;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.*;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import org.jetbrains.annotations.NotNull;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Game {

    private final int MAXPLAYER  = 3;

    //Player
    private int id;
    private List<String> nickNames; //in game players
    private List<PlayerInterface> onlinePlayers;
    private Turn currentTurn;
    private int counterId = 1;
    private Board board;


    //God
    private enum gods {
        Apollo, Artemis, Athena, Atlas, Demeter, Hephaestus, Minotaur, Pan, Prometheus
    }


    private List<gods> godList = new ArrayList<>();
    private List<God> chosenGods = new ArrayList<>();


    Map<String, Method> metodi = new HashMap<>();
    Map<String, List<PlayerInterface>> map = new HashMap<>();

    public Game() {
        nickNames = new ArrayList<>();
        onlinePlayers = new ArrayList<>();
    }

    public List<God> getChosenGods() {
        return chosenGods;
    }
    public List<gods> getGodList() {
        return godList;
    }
    public void setGodList(List<gods> godList) {
        this.godList = godList;
    }
    public void initializeGodList() {
        godList.addAll(Arrays.asList(gods.values()));
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
    public void addNickname(String nickNames) {
        for (PlayerInterface player : onlinePlayers) {
            this.nickNames.add(player.getNickname());
        }
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

    /**
     * Add player in OnlinePlayer list
     * @param player
     * @throws GameIsAlreadyStarted
     */
    public void addPlayers(PlayerInterface player){
        onlinePlayers.add(player);
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
        for (int i = 0; i < MAXPLAYER; i++) {
            onlinePlayers.add(new Player());
        }
        for (PlayerInterface playerInterface : onlinePlayers) {
            for (int i = 0; i < 2; i++, counterId++) {
                Worker worker = new Worker(counterId);
                list.add(worker);
            }
            playerInterface.setWorkerRef(list);
            playerInterface.setBoard(board);
            list.clear();
        }
    }

    /*
    public PlayerInterface decPlayer(String name, PlayerInterface player){
        Ref ref = new Ref();
        PlayerInterface newPlayer = ref.Decorator(name);
        newPlayer.setTutto(player);
        return newPlayer;
    }
    */

    public void notifyExc(){
        GameManager gameManager = new GameManager();
        gameManager.notifyObservers(null,"Exception");
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
    public String getPlayerNickname(int num){
        return getOnlinePlayers().get(num).getNickname();
    }

    public List<God> addChosenGods(String godName){
        //God god = new God(godName);
        //chosenGods.add(god);
        return chosenGods;
    }


}

