package it.polimi.ingsw.Network.Server;

import it.polimi.ingsw.Network.Message.MessageFromClient.*;

import java.io.IOException;

/**
 * Each incoming message is sent here.
 * The type of the message is analysed and the right method
 *      is sent to VisitorMethodsServer
 *
 */
public interface VisitorServer {

    /**
     * Clients tell the server that they are alive
     * @param beatUpdate
     */
    void visit(BeatUpdate beatUpdate);

    /**
     * Nickname chosen by the client
     * @param nicknameResponse
     * @throws IOException
     */
    void visit(NicknameResponse nicknameResponse) throws IOException;

    /**
     * Number of players chosen by the client
     * @param playerNumberResponse
     * @throws IOException
     * @throws InterruptedException
     */
    void visit(PlayerNumberResponse playerNumberResponse) throws IOException, InterruptedException;

    /**Cards chosen by the challenger
     *
     * @param chosenCardsUpdate
     * @throws IOException
     */
    void visit(ChosenCardsUpdate chosenCardsUpdate) throws IOException;

    /**Card chosen by each player
     *
     * @param setYourCardResponse
     * @throws IOException
     */
    void visit(SetYourCardResponse setYourCardResponse) throws IOException;

    /**Put the workers on the board
     *
     * @param startingSetWorkerResponse
     * @throws IOException
     */
    void visit(StartingSetWorkerResponse startingSetWorkerResponse) throws IOException;

    /**Select which worker you want to move
     *
     * @param chooseYourWorkerResponse
     * @throws IOException
     */
    void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) throws IOException;

    /**
     * Move input
     * @param moveResponse
     * @throws IOException
     */
    void visit(MoveResponse moveResponse) throws IOException;

    /**Build input
     *
     * @param buildResponse
     * @throws IOException
     */
    void visit(BuildResponse buildResponse) throws IOException;

    /**Ask clients if it wants to enable the effect
     *
     * @param askEffectReply
     * @throws IOException
     */
    void visit(AskEffectReply askEffectReply) throws IOException;

    /**
     * Choose the worker for the effect
     * @param chooseYourWorkerEffectResponse
     * @throws IOException
     */
    void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) throws IOException;

    /**
     * Ask clients if it wants to enable the build effect
     * @param askEffectBuildResponse
     * @throws IOException
     */
    void visit(AskEffectBuildResponse askEffectBuildResponse) throws IOException;

    /**
     * Send a build with two boardcells as input
     * 1. normal build
     * 2. special build
     * @param buildTwoInputResponse
     * @throws IOException
     */
    void visit(BuildTwoInputResponse buildTwoInputResponse) throws IOException;

    /**
     * Send a move with two boardcells as input
     * 1. normal move
     * 2. special move
     * @param moveTwoInputResponse
     */
    void visit(MoveTwoInputResponse moveTwoInputResponse) ;

    /**
     * Takes the number of player for the game that starts and sends it to the Model
     * @param playerThatStart number of the player that starts
     */
    void visit(PlayerThatStart playerThatStart);

}
