package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;
import java.util.Scanner;

/**
 * All the methods here call a method in another class with the Message depending on the interface
 */
public class VisitorMethodsClient implements VisitorClient {

    final Client client;

    Scanner scanner = new Scanner(System.in);
    private final Scanner input = new Scanner(System.in);
    private final Scanner string = new Scanner(System.in);

    public final UserInterface userInterface;



    public VisitorMethodsClient(Client client, UserInterface userInterface) {
        this.client = client;
        this.userInterface = userInterface;
    }


    /**
     * Request the number of players
     * @param playerNumberRequest Message
     */
    @Override
    public synchronized void visit(PlayerNumberRequest playerNumberRequest) {

        userInterface.PlayerNumberRequest();
        //client.send(new PlayerNumberResponse(num));

    }

    /**
     * Request the nickname
     * @param nicknameRequest Message
     */
    @Override
    public synchronized void visit(NicknameRequest nicknameRequest) {


        userInterface.NicknameRequest();

        //client.send(new NicknameResponse(nickname));

    }

    /**
     * connection enabled
     * @param connectionResponse Message
     */
    @Override
    public void visit(ConnectionResponse connectionResponse) {

        userInterface.ConnectionResponse();

    }

    /**
     * card not found, a new card has to be chosen
     * @param cardNotFoundRequest Message
     */
    @Override
    public void visit(CardNotFoundRequest cardNotFoundRequest) {

        userInterface.CardNotFoundRequest();

    }

    /**
     * Time to set workers
     * @param startingSetWorkerTimeUpdate Message
     */
    @Override
    public void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {

        userInterface.StartingSetWorkerTimeUpdate(startingSetWorkerTimeUpdate);

    }


    /**
     * coordinates for the worker to set
     * @param startingSetWorkerRequest Message
     */
    @Override
    public void visit(StartingSetWorkerRequest startingSetWorkerRequest) {


        userInterface.StartingSetWorkerRequest(startingSetWorkerRequest);


    }


    /**
     * Coordinates wrong, asking for new ones
     * @param wrongCoordinatesUpdate message
     */
    @Override
    public void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) {


        userInterface.WrongCoordinatesUpdate(wrongCoordinatesUpdate);



    }


    /**
     * The player is locked, no more in the game
     * @param playerLockedUpdate message
     */
    @Override
    public void visit(PlayerLockedUpdate playerLockedUpdate) {

        userInterface.PlayerLockedUpdate(playerLockedUpdate);

    }

    /**
     * Sends the board when updated
     * @param boardUpdate message
     */
    @Override
    public void visit(BoardUpdate boardUpdate) {

       userInterface.BoardUpdate(boardUpdate);


    }


    /**
     * Information about the player that is playing now
     * @param playerTurnUpdate message
     */
    @Override
    public void visit(PlayerTurnUpdate playerTurnUpdate) {

        userInterface.PlayerTurnUpdate(playerTurnUpdate);

    }


    /**
     * Asks for the worker to move
     * @param chooseYourWorkerRequest message
     */
    @Override
    public void visit(ChooseYourWorkerRequest chooseYourWorkerRequest)  {

        userInterface.ChooseYourWorkerRequest(chooseYourWorkerRequest);

    }

    /**
     * Asks for the coordinates
     * @param moveRequest message
     */
    @Override
    public void visit(MoveRequest moveRequest)  {

        userInterface.MoveRequest(moveRequest);


    }

    /**
     * Update the game, it's time for the building
     * @param buildTimeUpdate message
     */
    @Override
    public void visit(BuildTimeUpdate buildTimeUpdate) {

       userInterface.BuildTimeUpdate();

    }


    /**
     * Asking for building coordinates
     * @param buildingRowAndCol message
     */
    @Override
    public void visit(BuildRequest buildingRowAndCol)  {


        userInterface.BuildRequest(buildingRowAndCol);


    }


    /**
     * Coordinates were not correct, asking for new ones
     * @param tryNewCoordinatesRequest message
     */
    @Override
    public void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest) {


       userInterface.TryNewCoordinatesRequest();

    }


    /**
     * The worker chosen was not correct, asking for a new one
     * @param wrongWorkerUpdate message
     */
    @Override
    public void visit(WrongWorkerUpdate wrongWorkerUpdate) {

        userInterface.WrongWorkerUpdate(wrongWorkerUpdate);

    }


    /**
     * Sends an update to notify the player that the nickname was correct
     * @param nicknameAcceptedUpdate message
     */
    @Override
    public void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate) {

        userInterface.NicknameAcceptedUpdate(nicknameAcceptedUpdate);

    }


    /**
     * Sends the request for a new nickname
     * @param nicknameNotValidUpdate message
     */
    @Override
    public void visit(NicknameNotValidUpdate nicknameNotValidUpdate) {

       userInterface.NicknameNotValidUpdate();
        
    }


    /**
     * Asks for the card of the player
     * @param chooseCardsUpdate message
     */
    @Override
    public void visit(ChooseCardsUpdate chooseCardsUpdate) {

        userInterface.ChooseCardsUpdate(chooseCardsUpdate);

    }

    /**
     * Sends the list of the available gods
     * @param availableGodsUpdate message
     */
    @Override
    public void visit(AvailableGodsUpdate availableGodsUpdate) {

       userInterface.AvailableGodsUpdate(availableGodsUpdate);
    }


    /**
     * Asks for the cards for the game
     * @param challengerCardsRequest message
     */
    @Override
    public void visit(ChallengerCardsRequest challengerCardsRequest)  {

        userInterface.ChallengerCardsRequest(challengerCardsRequest);


    }


    /**
     * Sends the update with the card updated
     * @param cardAddedUpdate message
     */
    @Override
    public void visit(CardAddedUpdate cardAddedUpdate) {

       userInterface.CardAddedUpdate(cardAddedUpdate);

    }


    /**
     * Sends an error if the card chosen is not in the given cards
     * @param cardChallengerNotFoundRequest message
     */
    @Override
    public void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

       userInterface.CardChallengerNotFoundRequest(cardChallengerNotFoundRequest);

    }

    /**
     * Update about the time of the game, it's time to set the cards
     * @param setCardTimeUpdate message
     */
    @Override
    public void visit(SetCardTimeUpdate setCardTimeUpdate)  {

       userInterface.SetCardTimeUpdate(setCardTimeUpdate);
    }


    /**
     * Request of setting the card
     * @param setYourCardRequest message
     */
    @Override
    public void visit(SetYourCardRequest setYourCardRequest) {

        userInterface.SetYourCardRequest(setYourCardRequest);


    }


    /**
     * Update if the card is set correctly
     * @param cardSetUpdate Message
     */
    @Override
    public void visit(CardSetUpdate cardSetUpdate) {

        userInterface.CardSetUpdate(cardSetUpdate);

    }


    /**
     * Message if the max number of player is reached
     * @param maxPlayerReachedUpdate Message
     */
    @Override
    public void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate) {

        userInterface.MaxPlayerReachedUpdate();
    }


    /**
     * Asking to the player if it wants to use the effect or not, applied with active gods
     * @param askEffect Message
     */
    @Override
    public void visit(AskEffect askEffect) {


        userInterface.AskEffect();


    }


    /**
     * Asking for the worker when the player has some special effect
     * @param chooseYourWorkerEffectRequest Message
     */
    @Override
    public void visit(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest)  {

        userInterface.ChooseYourWorkerEffectRequest(chooseYourWorkerEffectRequest);


    }


    /**
     * Asking for the effect when the player has an effect that involves building
     * @param askEffectBuild Message
     */
    @Override
    public void visit(AskEffectBuild askEffectBuild)  {


        userInterface.AskEffectBuild(askEffectBuild);


    }


    /**
     * Asking for different input for the building
     * @param buildTwoInputRequest Message
     */
    @Override
    public void visit(BuildTwoInputRequest buildTwoInputRequest)  {


        userInterface.BuildTwoInputRequest(buildTwoInputRequest);


    }


    /**
     * Sending the message that the number of player chosen is not 2 or 3
     * @param numberOfPlayerWrong Message
     */
    @Override
    public void visit(NumberOfPlayerWrong numberOfPlayerWrong) {

       userInterface.NumberOfPlayerWrong();

    }


    /**
     * Asking for a different input for the moving
     * @param moveTwoInputRequest Message
     */
    @Override
    public void visit(MoveTwoInputRequest moveTwoInputRequest) {


        userInterface.MoveTwoInputRequest(moveTwoInputRequest);

    }

    /**
     * Sending the win message with the name of the winner
     * @param winMessage message
     */
    @Override
    public void visit(WinMessage winMessage) {

        userInterface.WinMessage(winMessage.getNickname());

    }


    /**
     * telling the client that the worker chosen was not valid
     * @param workerInputNotValid Message
     */

    @Override
    public void visit(WorkerInputNotValid workerInputNotValid) {

        userInterface.WorkerInputNotValid();

    }


    /**
     * Saying to the client that someone dropped the connection
     * @param droppedConnection Message with the name of the player that dropped the connection
     */
    @Override
    public void visit(DroppedConnection droppedConnection) {
        userInterface.DroppedConnection(droppedConnection);
    }


    /**
     * Asking for the name of the first Player
     * @param setFirstPlayer Message
     */

    @Override
    public void visit(SetFirstPlayer setFirstPlayer)  {
        userInterface.SetFirstPlayer(setFirstPlayer.getOnlinePlayers());
    }


    /**
     * The server is restarting
     * @param serverRestart Message
     */
    @Override
    public void visit(ServerRestart serverRestart) {
        userInterface.ServerRestart();
    }


    /**
     * Saying to the client that the username length is too long
     * @param tooLongName Message
     */
    @Override
    public void visit(tooLongName tooLongName)  {
        userInterface.lengthNameError();

    }


}
