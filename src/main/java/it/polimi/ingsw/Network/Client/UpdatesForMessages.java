package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.WrongCoordinatesUpdate;

import java.io.IOException;

public class UpdatesForMessages {

    Client client;

    public void updatePlayerNumberResponse(String num) throws IOException {

        client.send(new PlayerNumberResponse(num));

    }

    public void updateNicknameResponse(String nickname) throws IOException {

        client.send(new NicknameResponse(nickname));

    }

    public void updateStartingSetWorkerResponse(String row, String col, int worker) throws IOException {

        client.send(new StartingSetWorkerResponse(row, col, worker));

    }

    public void updateWrongCoordinatesUpdate(String row, String col, int worker) throws IOException {
        client.send(new StartingSetWorkerResponse(row, col, worker));
    }

    public void updateChooseYourWorkerResponse(String worker) throws IOException {

        client.send(new ChooseYourWorkerResponse(worker));

    }

    public void updateMoveResponse(String row, String col, int worker) throws IOException {

        client.send(new MoveResponse(row, col, worker));

    }

    public void updateBuildResponse(String row, String col, int worker) throws IOException {
        client.send(new BuildResponse(row, col, worker));
    }

    public void updateChosenCardsUpdate(String cardName) throws IOException {
        client.send(new ChosenCardsUpdate(cardName));
    }


    public void updateSetYourCardResponse(String in) throws IOException {
        client.send(new SetYourCardResponse(in));
    }

    public void updateAskEffectReply(String effect, String nickname) throws IOException {

        client.send(new AskEffectReply(effect, client.getNickname()));
    }

    public void updateChooseYourWorkerEffectResponse(String worker, boolean effect) throws IOException {
        client.send(new ChooseYourWorkerResponse(worker));
    }


    public void updateAskeffectBuildResponse(String effect, String nickname, int worker) throws IOException {
        client.send(new AskEffectBuildResponse(effect, client.getNickname(), worker));
    }

    public void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException {
        client.send(new MoveTwoInputResponse(row1, col1, row2, col2, worker));
    }

    public void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException {
        client.send(new BuildTwoInputResponse(row1, col1, row2, col2, worker));
    }





}
