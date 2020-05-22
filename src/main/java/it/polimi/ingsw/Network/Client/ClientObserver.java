package it.polimi.ingsw.Network.Client;

import java.io.IOException;

public interface ClientObserver {


    void updatePlayerNumberResponse(String num) throws IOException;
    void updateNicknameResponse(String nickname) throws IOException;
    void updateStartingSetWorkerResponse(String row, String col, int worker) throws IOException;
    void updateWrongCoordinatesUpdate(String row, String col, int worker) throws IOException;
    void updateChooseYourWorkerResponse(String worker) throws IOException;
    void updateMoveResponse(String row, String col, int worker) throws IOException;
    void updateBuildResponse(String row, String col, int worker) throws IOException;
    void updateChosenCardsUpdate(String cardName) throws IOException;
    void updateSetYourCardResponse(String in) throws IOException;
    void updateAskEffectReply(String effect, String nickname) throws IOException;
    void updateChooseYourWorkerEffectResponse(String worker, boolean effect) throws IOException;
    void updateAskeffectBuildResponse(String effect, String nickname, int worker) throws IOException;
    void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException;
    void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException;

}

