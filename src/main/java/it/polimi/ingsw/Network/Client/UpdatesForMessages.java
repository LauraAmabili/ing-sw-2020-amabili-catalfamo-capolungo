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
     */
    @Override
    public void updatePlayerNumberResponse(String num) {
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
     */
    @Override
    public void updateNicknameResponse(String nickname) {

        client.setNickname(nickname);
        client.send(new NicknameResponse(nickname));

    }


    /**
     * Coordinates of the worker to be set
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     */
    @Override
    public void updateStartingSetWorkerResponse(String row, String col, int worker) {

        client.send(new StartingSetWorkerResponse(row, col, worker));

    }


    /**
     * Notify with new coordinates
     * @param row chosen row
     * @param col chosen col
     * @param worker worker
     */
    @Override
    public void updateWrongCoordinatesUpdate(String row, String col, int worker)  {
        client.send(new StartingSetWorkerResponse(row, col, worker));
    }


    /**
     * Sending the server the worker chosen to make the move
     * @param worker number of the worker
     */
    @Override
    public void updateChooseYourWorkerResponse(String worker){

        client.send(new ChooseYourWorkerResponse(worker));

    }


    /**
     * Coordinates for the server for the moving
     * @param row chosen row
     * @param col chosen col
     * @param worker number of the worker
     * @param av list of Board cells where to move in
     */
    @Override
    public void updateMoveResponse(String row, String col, int worker, List<BoardCell> av) {
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
     */
    public void updateBuildResponse(String row, String col, int worker, List<BoardCell> b) {
       BuildResponse message = new BuildResponse(row, col, worker);
       message.setAv(b);
        client.send(new BuildResponse(row, col, worker));
    }

    /**
     * Card chosen by the challenger
     * @param cardName name of the card
     */
    public void updateChosenCardsUpdate(String cardName)  {
        client.send(new ChosenCardsUpdate(cardName));
    }


    /**
     * Card chosen by the player
     * @param in name of the card
     */
    public void updateSetYourCardResponse(String in)  {
        client.send(new SetYourCardResponse(in));
    }

    /**
     * Reply for the decision of the worker
     * @param effect y or n
     * @param nickname name of the player
     */
    public void updateAskEffectReply(String effect, String nickname){

        client.send(new AskEffectReply(effect, client.getNickname()));
    }

    /**
     * sends a message to the server
     * @param worker number of the worker
     * @param effect y or n
     */
    public void updateChooseYourWorkerEffectResponse(String worker, boolean effect){
        client.send(new ChooseYourWorkerEffectResponse(worker, effect));
    }


    /**
     * response of the effect
     * @param effect effect
     * @param nickname nickname of the player
     * @param worker number of the worker
     * @throws IOException exception
     */
    public void updateAskeffectBuildResponse(String effect, String nickname, int worker)  {
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
    public void updateMoveTwoInputResponse(String row1, String col1, String row2, String col2, int worker)  {
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
    public void updateBuildTwoInputResponse(String row1, String col1, String row2, String col2, int worker)  {
        client.send(new BuildTwoInputResponse(row1, col1, row2, col2, worker));
    }


    /**
     * response with the name of the player that start
     * @param player name of the player
     * @param onlinePlayers list of players
     * @throws IOException
     */
    public void updatePlayerThatStart(String player, List<PlayerInterface> onlinePlayers)  {
        client.send(new PlayerThatStart(player, onlinePlayers));
    }




}
