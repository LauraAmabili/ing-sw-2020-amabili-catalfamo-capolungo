package it.polimi.ingsw.Model.Player.SpecialEffects;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.PlayerFSA.*;
import it.polimi.ingsw.Model.Worker;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PlayerDecorator implements PlayerInterface {

    PlayerFSA addNickname;
    PlayerFSA initialized;
    PlayerFSA setCard;
    PlayerFSA placeWorker;
    PlayerFSA moving;
    PlayerFSA building;
    PlayerFSA idle;

    PlayerFSA oldPlayerState;
    PlayerFSA playerState;

    // attributes
    protected PlayerInterface player;
    private String nickname;
    private List<Worker> workerRef; // reference to the workers
    private List<God> chosenGods;
    private God activeCard;
    private Board board;
    private boolean moveUp;

    // constructor
    public PlayerDecorator(PlayerInterface player) {
        this.player = player;
        this.nickname = player.getNickname();
        this.workerRef = player.getWorkerRef();
        this.chosenGods = player.getChosenGods();
        this.activeCard = player.getActiveCard();
        this.board = player.getBoard();
        this.moveUp = player.isMoveUp();
        this.addNickname = new AddNickname(this);
        this.initialized =  new Initialized(this);
        this.setCard = new SetCard(this);
        this.placeWorker = new PlaceWorker(this);
        this.moving = new Moving(this);
        this.building = new Building(this);
        this.idle = new Idle(this);
        this.oldPlayerState = setCard;
        this.playerState = idle;
    }

    public boolean isMoveUp(){
        return moveUp;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getNickname(){
        return nickname;
    }

    @Override
    public void setChosenGods(List<God> gods) {
        this.chosenGods = gods;
    }

    @Override
    public List<God> getChosenGods() {
        return chosenGods;
    }


    @Override
    public void setActiveCard(God activeCard) {
        this.activeCard = activeCard;
    }

    @Override
    public God getActiveCard() {
        return activeCard;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void setOldPlayerState(PlayerFSA playerState) {
        this.oldPlayerState = playerState;
    }

    @Override
    public PlayerFSA getOldPlayerState() {
        return oldPlayerState;
    }

    @Override
    public boolean addWorker(int row, int col, Worker worker) {
        return player.addWorker(row, col, worker);
    }

    @Override
    public void setWorkerRef(List<Worker> list) {
        this.workerRef = list;
    }

    @Override
    public List<Worker> getWorkerRef() {
        return workerRef;
    }

    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker){
        return player.availableCellsToMove(worker);
    }

    @Override
    public List<BoardCell> availableCellsToMove(@NotNull Worker worker, boolean specialEffect){
        return player.availableCellsToMove(worker, specialEffect);
    }

    @Override
    public boolean move(int row, int col, @NotNull Worker worker){
        return player.move(row, col, worker);
    }

    @Override
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker){
        return player.availableCellsToBuild(worker);
    }

    @Override
    public List<BoardCell> availableCellsToBuild(@NotNull Worker worker, boolean specialEffect){
        return player.availableCellsToBuild(worker, specialEffect);
    }

    @Override
    public boolean build(int row, int col, @NotNull Worker worker){
        return player.build(row, col, worker);
    }

    @Override
    public boolean build(int row, int col, @NotNull Worker worker, boolean specialEffect){
        return player.build(row, col, worker, specialEffect);
    }

    @Override
    public boolean build(int row1, int col1, @NotNull Worker worker, int row2, int col2){return player.build(row1, col1, worker, row2, col2);}

    @Override
    public boolean checkWin(@NotNull Worker worker){
        return player.checkWin(worker);
    }

    @Override
    public boolean move(int row, int col, @NotNull Worker worker, boolean specialEffect, int rowBuild, int colBuild) {
        return player.move(row, col, worker, specialEffect, rowBuild, colBuild);
    }

    @Override
    public PlayerFSA getAddNickname() {
        return addNickname;
    }

    @Override
    public PlayerFSA getInitialized() {
        return initialized;
    }

    @Override
    public PlayerFSA getSetCard() {
        return setCard;
    }

    @Override
    public PlayerFSA getPlaceWorker() {
        return placeWorker;
    }

    @Override
    public PlayerFSA getMoving() {
        return moving;
    }

    @Override
    public PlayerFSA getBuilding() {
        return building;
    }

    @Override
    public PlayerFSA getIdle() {
        return idle;
    }

    @Override
    public PlayerFSA getPlayerState() {
        return playerState;
    }

    @Override
    public void setPlayerState(PlayerFSA playerState) {
        player.setPlayerState(playerState);
    }

    @Override
    public void StateMove(int row, int col, Worker worker) {
        player.getPlayerState().Move(row, col, worker);
    }

    @Override
    public void StateBuild(int row, int col, Worker worker) {
        player.getPlayerState().Build(row, col, worker);
    }

    @Override
    public void addNickname(String nickname) {
        player.getPlayerState().addNickname(nickname);
    }

    @Override
    public void setCard(God godName) {
        player.getPlayerState().setCard(godName);
    }

    @Override
    public void PlaceWorker(int row, int col, Worker worker) {
        player.getPlayerState().placeWorker(row, col, worker);
    }
}
