package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;

public interface VisitorClient {


    /**
     * Request the number of players
     * @param playerNumberRequest Message
     */
    void visit(PlayerNumberRequest playerNumberRequest) throws IOException;
    /**
     * connection enabled
     * @param connectionResponse Message
     */
    void visit(ConnectionResponse connectionResponse);
    /**
     * Message if the max number of player is reached
     * @param maxPlayerReachedUpdate Message
     */
    void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate);
    /**
     * Request the nickname
     * @param nicknameRequest Message
     */
    void visit(NicknameRequest nicknameRequest);
    /**
     * Sends an update to notify the player that the nickname was correct
     * @param nicknameAcceptedUpdate message
     */
    void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate);
    /**
     * Sends the request for a new nickname
     * @param nicknameNotValidUpdate message
     */
    void visit(NicknameNotValidUpdate nicknameNotValidUpdate);

    /**
     * Sends the list of the available gods
     * @param availableGodsUpdate message
     */
    void visit(AvailableGodsUpdate availableGodsUpdate);
    /**
     * Asks for the cards for the game
     * @param challengerCardsRequest message
     */
    void visit(ChallengerCardsRequest challengerCardsRequest);

    /**
     * Sends the update with the card updated
     * @param cardAddedUpdate message
     */
    void visit(CardAddedUpdate cardAddedUpdate);
    /**
     * Sends an error if the card chosen is not in the given cards
     * @param cardChallengerNotFoundRequest message
     */
    void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest);
    /**
     * Update about the time of the game, it's time to set the cards
     * @param setCardTimeUpdate message
     */
    void visit(SetCardTimeUpdate setCardTimeUpdate);
    /**
     * Request of setting the card
     * @param setYourCardRequest message
     */
    void visit(SetYourCardRequest setYourCardRequest);
    /**
     * Update if the card is set correctly
     * @param cardSetUpdate Message
     */
    void visit(CardSetUpdate cardSetUpdate);
    /**
     * Asks for the card of the player
     * @param timeToChooseCard message
     */
    void visit(ChooseCardsUpdate timeToChooseCard);
    /**
     * card not found, a new card has to be chosen
     * @param cardNotFoundRequest Message
     */
    void visit(CardNotFoundRequest cardNotFoundRequest);
    /**
     * Time to set workers
     * @param startingSetWorkerTimeUpdate Message
     */
    void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate);

    /**
     * coordinates for the worker to set
     * @param startingSetWorkerRequest Message
     */
    void visit(StartingSetWorkerRequest startingSetWorkerRequest);
    /**
     * Coordinates wrong, aking for new ones
     * @param wrongCoordinatesUpdate message
     */
    void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) ;
    /**
     * The player is locked, no more in the game
     * @param playerLockedUpdate message
     */
    void visit(PlayerLockedUpdate playerLockedUpdate);
    /**
     * Sends the board when updated
     * @param boardUpdate message
     */
    void visit(BoardUpdate boardUpdate);
    /**
     * Information about the player that is playing now
     * @param playerTurnUpdate message
     */
    void visit(PlayerTurnUpdate playerTurnUpdate);
    /**
     * Asks for the worker to move
     * @param chooseYourWorkerRequest message
     */
    void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) ;
    /**
     * Asks for the coordinates
     * @param moveRequest message
     */
    void visit(MoveRequest moveRequest) ;
    /**
     * Update the game, it's time for the building
     * @param buildTimeUpdate message
     */
    void visit(BuildTimeUpdate buildTimeUpdate);
    /**
     * Asking for building coordinates
     * @param buildingRowAndCol message
     */
    void visit(BuildRequest buildingRowAndCol) ;

    /**
     * Coordinates were not correct, asking for new ones
     * @param tryNewCoordinatesRequest message
     */
    void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest);
    /**
     * The worker chosen was not correct, asking for a new one
     * @param wrongWorkerUpdate message
     */
    void visit(WrongWorkerUpdate wrongWorkerUpdate);
    /**
     * Asking to the player if it wants to use the effect or not, applied with active gods
     * @param askEffect Message
     */
    void visit(AskEffect askEffect) ;
    /**
     * Asking for the worker when the player has some special effect
     * @param chooseYourWorkerEffectRequest Message
     */

    void visit(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) ;
    /**
     * Asking for the effect when the player has an effect that involves building
     * @param askEffectBuild Message
     */
    void visit(AskEffectBuild askEffectBuild);
    /**
     * Asking for different input for the building
     * @param buildTwoInputRequest Message
     */
    void visit(BuildTwoInputRequest buildTwoInputRequest) ;

    /**
     * Sending the message that the number of player chosen is not 2 or 3
     * @param numberOfPlayerWrong Message
     */
    void visit(NumberOfPlayerWrong numberOfPlayerWrong);
    /**
     * Asking for a different input for the moving
     * @param moveTwoInputRequest Message
     */
    void visit(MoveTwoInputRequest moveTwoInputRequest) ;
    /**
     * Sending the win message with the name of the winner
     * @param winMessage message
     */
    void visit(WinMessage winMessage);
    /**
     * telling the client that the worker chosen was not valid
     * @param workerInputNotValid Message
     */

    void visit(WorkerInputNotValid workerInputNotValid);
    /**
     * Saying to the client that someone dropped the connection
     * @param droppedConnection Message with the name of the player that dropped the connection
     */
    void visit(DroppedConnection droppedConnection);
    /**
     * Asking for the name of the first Player
     * @param setFirstPlayer Message
     */

    void visit(SetFirstPlayer setFirstPlayer) ;
    /**
     * The server is restarting
     * @param serverRestart Message
     */
    void visit(ServerRestart serverRestart);
    /**
     * Saying to the client that the username length is too long
     * @param tooLongName Message
     */
    void visit(tooLongName tooLongName) ;

}
