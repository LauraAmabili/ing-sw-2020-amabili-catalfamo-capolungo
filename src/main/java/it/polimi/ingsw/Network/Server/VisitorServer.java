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
     * @
     */
    void visit(NicknameResponse nicknameResponse) ;

    /**
     * Number of players chosen by the client
     * @param playerNumberResponse
     * @
     * @throws InterruptedException
     */
    void visit(PlayerNumberResponse playerNumberResponse) throws InterruptedException;

    /**Cards chosen by the challenger
     *
     * @param chosenCardsUpdate
     * @
     */
    void visit(ChosenCardsUpdate chosenCardsUpdate) ;

    /**Card chosen by each player
     *
     * @param setYourCardResponse
     * @
     */
    void visit(SetYourCardResponse setYourCardResponse) ;

    /**Put the workers on the board
     *
     * @param startingSetWorkerResponse
     * @
     */
    void visit(StartingSetWorkerResponse startingSetWorkerResponse) ;

    /**Select which worker you want to move
     *
     * @param chooseYourWorkerResponse
     * @
     */
    void visit(ChooseYourWorkerResponse chooseYourWorkerResponse) ;

    /**
     * Move input
     * @param moveResponse
     * @
     */
    void visit(MoveResponse moveResponse) ;

    /**Build input
     *
     * @param buildResponse
     * @
     */
    void visit(BuildResponse buildResponse) ;

    /**Ask clients if it wants to enable the effect
     *
     * @param askEffectReply
     * @
     */
    void visit(AskEffectReply askEffectReply) ;

    /**
     * Choose the worker for the effect
     * @param chooseYourWorkerEffectResponse
     * @
     */
    void visit(ChooseYourWorkerEffectResponse chooseYourWorkerEffectResponse) ;

    /**
     * Ask clients if it wants to enable the build effect
     * @param askEffectBuildResponse
     * @
     */
    void visit(AskEffectBuildResponse askEffectBuildResponse) ;

    /**
     * Send a build with two boardcells as input
     * 1. normal build
     * 2. special build
     * @param buildTwoInputResponse
     */
    void visit(BuildTwoInputResponse buildTwoInputResponse) ;

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
