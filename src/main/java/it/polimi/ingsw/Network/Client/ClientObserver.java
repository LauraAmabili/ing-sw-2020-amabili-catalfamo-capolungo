package it.polimi.ingsw.Network.Client;

import java.io.IOException;

public interface ClientObserver {


    public void updatePlayerNumberResponse(String num) throws IOException;
    public void updateNicknameResponse(String nickname) throws IOException;
    public void updateStartingSetWorkerResponse(String row, String col, int worker) throws IOException;
    public void updateWrongCoordinatesUpdate(String row, String col, int worker) throws IOException;
    public void updateChooseYourWorkerResponse(String worker) throws IOException;
    public void updateMoveResponse(String row, String col, int worker) throws IOException;
    public void updateBuildResponse(String row, String col, int worker) throws IOException;
    public void updateChosenCardsUpdate(String cardName) throws IOException;
    public void updateSetYourCardResponse(String in) throws IOException;
    public void updateAskEffectReply(String effect, String nickname) throws IOException;
    public void updateChooseYourWorkerEffectResponse(String worker, boolean effect) throws IOException;
    public void updateAskeffectBuildResponse(String effect, String nickname, int worker) throws IOException;
    public void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException;
    public void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException;

}

