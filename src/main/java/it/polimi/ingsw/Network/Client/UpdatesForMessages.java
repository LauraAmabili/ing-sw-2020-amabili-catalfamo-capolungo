package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;

import java.io.IOException;
import java.util.List;

public class UpdatesForMessages implements ClientObserver {

    private final Client client;

    public UpdatesForMessages(Client client) {
        this.client = client;
    }

    @Override
    public void updatePlayerNumberResponse(String num) throws IOException {
        try {
            client.setNumberOfPlayers(Integer.parseInt(num));
            client.send(new PlayerNumberResponse(num));
        } catch (NumberFormatException e) {
            client.send(new PlayerNumberResponse("-1"));
        }
    }

    @Override
    public void updateNicknameResponse(String nickname) throws IOException {

        client.setNickname(nickname);
        client.send(new NicknameResponse(nickname));

    }

    @Override
    public void updateStartingSetWorkerResponse(String row, String col, int worker) throws IOException {

        client.send(new StartingSetWorkerResponse(row, col, worker));

    }

    @Override
    public void updateWrongCoordinatesUpdate(String row, String col, int worker) throws IOException {
        client.send(new StartingSetWorkerResponse(row, col, worker));
    }

    @Override
    public void updateChooseYourWorkerResponse(String worker) throws IOException {

        client.send(new ChooseYourWorkerResponse(worker));

    }

    @Override
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
        client.send(new ChooseYourWorkerEffectResponse(worker, effect));
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

    public void updatePlayerThatStart(String player, List<PlayerInterface> onlinePlayers) throws IOException {
        client.send(new PlayerThatStart(player, onlinePlayers));
    }




}
