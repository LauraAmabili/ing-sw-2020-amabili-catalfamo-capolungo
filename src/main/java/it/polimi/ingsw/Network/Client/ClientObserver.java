package it.polimi.ingsw.Network.Client;

public interface ClientObserver {


    public void updatePlayerNumberResponse(String num);
    public void updateNicknameResponse(String nickname);
    public void updateStartingSetWorkerResponse(String row, String col, int worker);
    public void updateWrongCoordinatesUpdate(String row, String col, int worker);
    public void updateChooseYourWorkerResponse(String worker);
    public void updateMoveResponse(String row, String col, int worker);
    public void updateBuildResponse(String row, String col, int worker);
    public void updateChosenCardsUpdate(String cardName);
    public void updateSetYourCardResponse(String in);
    public void updateAskEffectReply(String effect, String nickname);
    public void updateChooseYourWorkerEffectResponse(String worker, boolean effect);
    public void updateAskeffectBuildResponse(String effect, String nickname, int worker);
    public void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker);
    public void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker);

}

