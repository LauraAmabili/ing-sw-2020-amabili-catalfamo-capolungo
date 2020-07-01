package it.polimi.ingsw.Model;

import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.List;

public interface ObserverModel {


    void updatePlayerAdded(String obj, String color) ;

    void updateGameisReady() throws InterruptedException;

    void updateGodSet(PlayerInterface player, String godName) ;

    void updateBoard(Board board) ;

    void updateTimeToChoose(String name, String color) ;

    void updateGodAdded(List<String> gods, boolean cardChosen, String challengerName) ;

    void updateWinners(PlayerInterface player) ;

    void updateMoving(int worker, String current,  List<BoardCell> available) ;

    void updateBuilding(int worker, String current,  List<BoardCell> available) ;

    void updateSetWorker(int i, String currentPlayer) ;

    void updateCardNotPresent(String nickname, List<String> availableGods , List<God> chosenGods) ;

    void updateGodNotAdded(String challengerName) ;

    void updateChoose(boolean chosenGods, List<God> Names, String ChallengerName) ;

    void updateNicknameNotValid(String nickname) ;

    void updatePlayerHasLost(String playerName) ;

    void updateDecideWorker(PlayerInterface nickname) ;

    void updateTimeToSetCard(List<String> chosenGods, PlayerInterface currentPlayerName) ;

    void updateTimeToPlaceWorker(String currentPlayerName) ;

    void updateWorkerSelected(int worker, String current, List<BoardCell> available) ;

    void updateNoCoordinatesValid(int worker, String current, List<BoardCell> available) ;

    void updateTimeToBuild(int worker, String current, List<BoardCell> av) ;

    void updateBoardAddedWorker(Board board, String currentPlayer);

    void updateSetCard(List<String> availableGods, String currentPlayer, List<God> chosenGods) ;

    void updateStartMoving(String current) ;

    void updateTimeToChooseWorker(String current) ;

    void updateAskForEffect(String currentPlayer) ;
    
    void updateAskForEffectBuild(String currentPlayer, int worker) ;

    void updateBuildTwoInput(String currentPlayer,int worker) ;

    void updateMoveTwoInput(String nickname, int worker) ;

    void updateSetFirstPlayer(String nickname, List<PlayerInterface> onlinePlayers) ;

    void updateNoCoordinatesValidBuildTwoInput(int worker, String nickname) ;

    void updateNoCoordinatesValidMoveTwoInput(int worker, String nickname) ;

    void updateServerRestart() ;


}

