package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;

import java.io.IOException;
import java.util.List;

public interface ClientObserver {


    void updatePlayerNumberResponse(String num);
    void updateNicknameResponse(String nickname);
    void updateStartingSetWorkerResponse(String row, String col, int worker);
    void updateWrongCoordinatesUpdate(String row, String col, int worker);
    void updateChooseYourWorkerResponse(String worker) ;
    void updateMoveResponse(String row, String col, int worker, List<BoardCell> av) ;
    void updateBuildResponse(String row, String col, int worker, List<BoardCell> av) ;
    void updateChosenCardsUpdate(String cardName) ;
    void updateSetYourCardResponse(String in) ;
    void updateAskEffectReply(String effect, String nickname) ;
    void updateChooseYourWorkerEffectResponse(String worker, boolean effect);
    void updateAskeffectBuildResponse(String effect, String nickname, int worker) ;
    void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) ;
    void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) ;
    void updatePlayerThatStart(String player, List<PlayerInterface> onlinePlayers) ;
}

