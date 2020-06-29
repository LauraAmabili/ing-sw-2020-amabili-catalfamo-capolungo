package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;

import java.io.IOException;
import java.util.List;

public class UpdatesForMessages implements ClientObserver {

    private final Client client;

    public UpdatesForMessages(Client client) {
        this.client = client;
    }

    /**
     * Sends the message with the number of player
     * @param num number of players of the game
     * @throws IOException Exception
     */
    @Override
    public void updatePlayerNumberResponse(String num) throws IOException {
        try {
            client.setNumberOfPlayers(Integer.parseInt(num));
            client.send(new PlayerNumberResponse(num));
        } catch (NumberFormatException e) {
            client.send(new PlayerNumberResponse("-1"));
        }
    }

    /**
     * Nickname response to send to the server
     * @param nickname name chosen by the player
     * @throws IOException Exception
     */
    @Override
    public void updateNicknameResponse(String nickname) throws IOException {

        client.setNickname(nickname);
        client.send(new NicknameResponse(nickname));

    }


    /**
     * Coordinates of the worker to be set
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @throws IOException Exception
     */
    @Override
    public void updateStartingSetWorkerResponse(String row, String col, int worker) throws IOException {

        client.send(new StartingSetWorkerResponse(row, col, worker));

    }


    /**
     * Notify with new coordinates
     * @param row chosen row
     * @param col chosen col
     * @param worker worker
     * @throws IOException Exception
     */
    @Override
    public void updateWrongCoordinatesUpdate(String row, String col, int worker) throws IOException {
        client.send(new StartingSetWorkerResponse(row, col, worker));
    }


    /**
     * Sending the server the worker chosen to make the move
     * @param worker number of the worker
     * @throws IOException Exception
     */
    @Override
    public void updateChooseYourWorkerResponse(String worker) throws IOException {

        client.send(new ChooseYourWorkerResponse(worker));

    }


    /**
     * Coordinates for the server for the moving
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @param av list of Board cells where to move in
     * @throws IOException Exception
     */
    @Override
    public void updateMoveResponse(String row, String col, int worker, List<BoardCell> av) throws IOException {
        MoveResponse move = new MoveResponse(row, col, worker);
        move.setAvailable(av);
        client.send(move);

    }

    /**
     * Coordinates for the server for the building
     * @param row chosen row
     * @param col chosen col
     * @param worker worker chosen before
     * @param b board cells available
     * @throws IOException Exception
     */
    public void updateBuildResponse(String row, String col, int worker, List<BoardCell> b) throws IOException {
       BuildResponse message = new BuildResponse(row, col, worker);
       message.setAv(b);
        client.send(new BuildResponse(row, col, worker));
    }

    /**
     * Card chosen by the challenger
     * @param cardName name of the card
     * @throws IOException Exception
     */
    public void updateChosenCardsUpdate(String cardName) throws IOException {
        client.send(new ChosenCardsUpdate(cardName));
    }


    /**
     * Card chosen by the player
     * @param in name of the card
     * @throws IOException exception
     */
    public void updateSetYourCardResponse(String in) throws IOException {
        client.send(new SetYourCardResponse(in));
    }

    /**
     * Reply for the decision of the worker
     * @param effect y or n
     * @param nickname name of the player
     * @throws IOException
     */
    public void updateAskEffectReply(String effect, String nickname) throws IOException {

        client.send(new AskEffectReply(effect, client.getNickname()));
    }

    /**
     * sends a message to the server
     * @param worker number of the worker
     * @param effect y or n
     * @throws IOException exception
     */
    public void updateChooseYourWorkerEffectResponse(String worker, boolean effect) throws IOException {
        client.send(new ChooseYourWorkerEffectResponse(worker, effect));
    }


    /**
     * response of the effect
     * @param effect effect
     * @param nickname nickname of the player
     * @param worker number of the worker
     * @throws IOException exception
     */
    public void updateAskeffectBuildResponse(String effect, String nickname, int worker) throws IOException {
        client.send(new AskEffectBuildResponse(effect, client.getNickname(), worker));
    }

    /**
     * Response of the double coordinates
     * @param row1 first chosen row
     * @param col1 first chosen col
     * @param row2 second chosen row
     * @param col2 second chosen col
     * @param worker number for the worker
     * @throws IOException
     */
    public void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException {
        client.send(new MoveTwoInputResponse(row1, col1, row2, col2, worker));
    }

    /**
     * Response of double coordinates for building
     * @param row1 first chosen row
     * @param col1 first chosen col
     * @param row2 second chosen row
     * @param col2 second chosen col
     * @param worker number of the worker
     * @throws IOException
     */
    public void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker) throws IOException {
        client.send(new BuildTwoInputResponse(row1, col1, row2, col2, worker));
    }


    /**
     * response with the name of the player that start
     * @param player name of the player
     * @param onlinePlayers list of players
     * @throws IOException
     */
    public void updatePlayerThatStart(String player, List<PlayerInterface> onlinePlayers) throws IOException {
        client.send(new PlayerThatStart(player, onlinePlayers));
    }




}
