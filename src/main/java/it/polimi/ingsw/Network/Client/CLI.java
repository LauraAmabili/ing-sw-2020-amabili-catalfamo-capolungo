package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.God.God;
import it.polimi.ingsw.Model.Player.SpecialEffects.PlayerInterface;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI extends NotifyMessages implements UserInterface {

    private Scanner string = new Scanner(System.in);
    private Scanner input = new Scanner(System.in);
    Board boardToPrint = new Board();
    private Client client;
    UpdatesForMessages up;
    List<God> gods = new ArrayList<>();




    public CLI() throws IOException {

        client = new Client(this);
        up = new UpdatesForMessages(client);
        this.addObserver(up);
        connectionPortRequest();

    }

    ClientBoard clientBoard = new ClientBoard();


    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";



    /**
     * Request of ip and server port
     */
    public void connectionPortRequest(){

        Scanner scanner= new Scanner(System.in);
        System.out.println("Host? ");
        String host = scanner.nextLine();
        String PATTERN = "^((0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)\\.){3}(0|1\\d?\\d?|2[0-4]?\\d?|25[0-5]?|[3-9]\\d?)$";
        boolean result = host.matches(PATTERN);
        if(result) {
            System.out.println("Port? ");
            String port = scanner.nextLine();
            client.startClient(host, port);
        } else {
            connectionPortRequest();
        }

    }


    /**
     * print the request to choose the number of player of the game  then sends a notify with the number
     * @throws IOException Exception
     */

    @Override
    public void  PlayerNumberRequest() {


        //clientBoard.Martello();
        //clientBoard.Players();
        System.out.println("Choose a game mode: \n*: 2 for a game 1v1.\n*: 3 for a game 1v1v1.");
        //clientBoard.Players();
        String num = string.nextLine();
        notifyPlayerNumberResponse(num);

    }


    /**
     * Asks the nickname for the game then sends a notify with the nickname
     */

    @Override
    public void NicknameRequest()  {
        System.out.println("Insert nickname:");
        String nickname = string.nextLine();
        notifyNicknameResponse(nickname);

    }


    /**
     * Print the connection update if the connection is established
     */
    @Override
    public void ConnectionResponse() {

        System.out.println("Connection established");
        clientBoard.Santorini();
        System.out.println("Welcome to Santorini! Ready to play? You are gonna move and build your own island!");

    }


    /**
     * Print to Cli when Card is not present
     */
    @Override
    public void CardNotFoundRequest() {

        System.out.println("Card not Present");

    }

    /**
     * print the name of the player that is placing workers
     * @param startingSetWorkerTimeUpdate message
     */

    @Override
    public void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {

        System.out.println("It's "+ startingSetWorkerTimeUpdate.getCurrentPlayer() + " time to place Workers");

    }

    /**
     * Asks the coordinates for the worker to set
     * @param startingSetWorkerRequest type of message
     */
    @Override
    public void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest)  {

        int worker = startingSetWorkerRequest.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowString = string.nextLine();
        System.out.println("Col: ");
        String colString = string.nextLine();
        notifyStartingSetWorkerResponse(rowString, colString, worker);

    }

    /**
     * Asks again for coordinates
     * @param wrongCoordinatesUpdate message
     */
    @Override
    public void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate)  {

        int worker = wrongCoordinatesUpdate.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowString = string.nextLine();
        System.out.println("Col: ");
        String colString = string.nextLine();
        notifyWrongCoordinatesUpdate(rowString, colString, worker);

    }


    /**
     * Prints to the player that his worker is blocked
     * @param playerLockedUpdate Message
     */
    @Override
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate) {

        System.out.println(playerLockedUpdate.getNickname() + "'s workers are locked. Out!");

    }

    /**
     * Prints the board
     * @param boardUpdate Message
     */
    @Override
    public void BoardUpdate(BoardUpdate boardUpdate) {

        boardToPrint = boardUpdate.getBoard();
        boardToPrint.printGrid();

    }


    /**
     * Prints the name of the player that is moving
     * @param playerTurnUpdate Message
     */
    @Override
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate) {

        String nickname = playerTurnUpdate.getPlayer().getNickname();
        System.out.println("It's time to move!");
        System.out.println("Now playing : " + playerTurnUpdate.getPlayer().getWorkerRef().get(0).getColor() + nickname + RESET);

    }

    /**
     * Asks the number of the worker that the player wats to move
     * @param chooseYourWorkerRequest Message
     */
    @Override
    public void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest)  {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        String worker = string.nextLine();
        notifyChooseYourWorkerResponse(worker);

    }

    /**
     * Moving request, need to inser row and col
     * @param moveRequest Message
     */
    @Override
    public void MoveRequest(MoveRequest moveRequest)  {


        boardToPrint.printAvailableGrid(moveRequest.getAvailableCess());
        int worker = moveRequest.getWorker();
        System.out.println(YELLOW);
        System.out.println("Choose row and col for worker " + worker + " : " );
        System.out.println(RESET);
        System.out.println("Row: ");
        String rowString = string.nextLine();
        System.out.println("Col: ");
        String colString = string.nextLine();
        notifyMoveResponse(rowString, colString, worker, moveRequest.getAvailableCess());
    }


    /**
     * Update for every player about the time
     */
    @Override
    public void BuildTimeUpdate() {
        System.out.println("Time to Build!");
    }


    /**
     * Building request, asking the coordinates
     * @param buildRequest Message
     */
    @Override
    public void BuildRequest(BuildRequest buildRequest) {

        boardToPrint.printAvailableGrid(buildRequest.getAv());
        System.out.println(YELLOW);
        System.out.println();
        System.out.println("Choose where to build! Insert Row and Col: ");
        System.out.println(RESET);
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        System.out.println("Col: ");
        String colstring = string.nextLine();
        int worker = buildRequest.getWorker();
        notifyBuildResponse(rowstring, colstring,worker, buildRequest.getAv());
    }


    /**
     * Notify that the coordinates are not correct
     */
    @Override
    public void TryNewCoordinatesRequest() {
        System.out.println("Coordinates not correct! Try new coordinates");

    }

    /**
     * If your worker cannot move, the game chooses the other one for you
     * @param wrongWorkerUpdate Message
     */
    @Override
    public void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate) {

        int worker = wrongWorkerUpdate.getWorker();
        System.out.println("This worker cannot move! Another worker has been chosen for you! Move now with worker " + worker);

    }

    /**
     * Update that the nickname is correct
     * @param nicknameAcceptedUpdate Message
     */
    @Override
    public void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate) {

        String color = nicknameAcceptedUpdate.getColor();
        System.out.println("Nickname " + nicknameAcceptedUpdate.getColor() + nicknameAcceptedUpdate.getName() + RESET + " accepted");
        System.out.println("Wait for others to connect..");

    }

    /**
     * Message that the nickname is not valid
     */
    @Override
    public void NicknameNotValidUpdate() {

        System.out.println("Nickname not valid");

    }

    /**
     * Update with the name of the challenger that is choosing the cards
     * @param chooseCardsUpdate Message
     */
    @Override
    public void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate) {

        System.out.println("It's time to choose the cards for the game!");
        System.out.println("Challenger was random, " + chooseCardsUpdate.getColor() +  chooseCardsUpdate.getChallenger() + RESET + " is choosing the cards");

    }

    /**
     * List of gods available for the game
     * @param availableGodsUpdate Message
     */
    @Override
    public void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate) {

        List<God> cards = availableGodsUpdate.getCards();
        for(God i : cards) {
            System.out.print(i.getGodName()+" ");
        }
        System.out.println();
        gods = availableGodsUpdate.getCards();

    }

    /**
     * Asks for the cards to choose by the challenger and prints the description of the card if asked
     * @param challengerCardsRequest Message
     */
    @Override
    public void ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest)  {


        System.out.println("Write "+ GREEN + " 'cardName -desc' " + RESET +  "to have the description of the card, " + GREEN + " 'cardName' to select the card." + RESET);
        System.out.println(RESET);
        //System.out.println("Choose card: ");
        String cardName = string.nextLine();
        boolean flag = true;
        if(cardName.contains("-desc")){
            String[] phrase = cardName.split(" ");
            System.out.println(phrase[0]);
            for(God g : gods){
                if(g.getGodName().equals(phrase[0])){
                    System.out.println(g.getDescriptionEffect());
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println("God not correct ");
            }
            ChallengerCardsRequest(challengerCardsRequest);
        }
        else {
            notifyChosenCardsUpdate(cardName);
        }
    }

    /**
     * Update with the list of god added already
     * @param cardAddedUpdate Message
     */
    @Override
    public void CardAddedUpdate(CardAddedUpdate cardAddedUpdate) {

        System.out.println("God added");
        List<String> addedGods = cardAddedUpdate.getAddedGods();
        System.out.println(addedGods);

    }

    /**
     * Prints god not correct if the god chosen is not correct
     * @param cardChallengerNotFoundRequest Message
     */
    @Override
    public void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

        System.out.println("Try again, God not correct");

    }

    /**
     * Prints the name of the person that is going to set the card
     * @param setCardTimeUpdate Exception
     */
    @Override
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate) {


        String current = setCardTimeUpdate.getCurrentPlayer().getNickname();
        System.out.println("It's " + setCardTimeUpdate.getCurrentPlayer().getWorkerRef().get(0).getColor() + current + RESET + " time to set the Card");

    }

    /**
     * The player can ask for the description of the card when choosing for it
     * @param setYourCardRequest Message
     */
    @Override
    public void SetYourCardRequest(SetYourCardRequest setYourCardRequest) {
        System.out.println("Choose your card between:  " + GREEN + setYourCardRequest.getAvailableGods() + RESET);
        System.out.println("Write 'cardName -desc' to have the description of the Card");
        List<God> chosenGods = setYourCardRequest.getChosenGods();
        String in = string.nextLine();
        boolean flag = true;
        if(in.contains("-desc")) {
            String[] phrase = in.split(" ");
            System.out.println(phrase[0]);
            for(God g : chosenGods){
                if(g.getGodName().equals(phrase[0])){
                    System.out.println(g.getDescriptionTitle());
                    System.out.println(g.getDescriptionEffect());
                    flag = false;
                    break;
                }
            }
            if(flag){
                System.out.println("God not correct ");
            }
           SetYourCardRequest(setYourCardRequest);
        } else {
            notifySetYourCardResponse(in);
        }

    }

    /**
     * Prints the name of the game and the card chosen
     * @param cardSetUpdate Message
     */
    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {

        System.out.println("Now "+ cardSetUpdate.getCurrentPlayer().getWorkerRef().get(0).getColor() + cardSetUpdate.getCurrentPlayer().getNickname() + RESET  + " has "  +  cardSetUpdate.getGodName().toUpperCase() + RESET +  " as Active Card");

    }

    /**
     * Prints this when someone is trying to connect but there are enough player for the chosen game
     */
    @Override
    public void MaxPlayerReachedUpdate() {

        System.out.println("Lobby is full. Try again later");

    }

    /**
     * Asking for the usage of the effect
     */
    @Override
    public void AskEffect()  {

        System.out.println("Do you want to use your card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        notifyAskEffectReply(effect, client.getNickname());

    }


    /**
     * Request of the worker to move
     * @param chooseYourWorkerEffectRequest Message
     */
    @Override
    public void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        boolean effect = chooseYourWorkerEffectRequest.isEffect();
        String worker = string.nextLine();
        notifyChooseYourWorkerEffectResponse(worker, effect);

    }

    /**
     * Asking for the usage of the effect for the build
     */
    @Override
    public void AskEffectBuild(AskEffectBuild askEffectBuild) {

        System.out.println("Do you want to use your card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        int worker = askEffectBuild.getWorker();
        notifyAskeffectBuildResponse(effect,client.getNickname(),worker);


    }

    /**
     * Prints this if you choose a different number instead of 2 or 3
     */
    @Override
    public void NumberOfPlayerWrong() {
        System.out.println("Wrong number of player! ");

    }

    /**
     * Request for two input for where to build
     * @param buildTwoInputRequest Message
     */
    @Override
    public void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) {

        int worker = buildTwoInputRequest.getWorker();
        System.out.println(YELLOW);
        System.out.println("Choose row and col for the first action");
        System.out.println(RESET);
        System.out.println("Row: ");
        String rowstring1 = string.nextLine();

        System.out.println("Col: ");
        String colstring1 = string.nextLine();
        System.out.println(YELLOW);
        System.out.println("Choose row and col for the second action");
        System.out.println(RESET);
        System.out.println("Row: ");
        String rowstring2 = string.nextLine();

        System.out.println("Col: ");
        String colstring2 = string.nextLine();


       notifyBuildTwoInputResponse(rowstring1, colstring1, rowstring2, colstring2, worker);



    }

    /**
     * Request for two input for where to move
     * @param moveTwoInputRequest Message
     */
    @Override
    public void MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) {


        int worker = moveTwoInputRequest.getWorker();
        System.out.println(YELLOW);
        System.out.println("Choose row and col for the first action");
        System.out.println(RESET);
        System.out.println("Row: ");
        String rowstring1 = string.nextLine();

        System.out.println("Col: ");
        String colstring1 = string.nextLine();
        System.out.println(YELLOW);
        System.out.println("Choose row and col for the second action");
        System.out.println(RESET);
        System.out.println("Row: ");
        String rowstring2 = string.nextLine();

        System.out.println("Col: ");
        String colstring2 = string.nextLine();

        notifyMoveTwoInputResponse(rowstring1, colstring1, rowstring2, colstring2, worker);

    }

    /**
     * Winning message
     * @param nickname name of the winner
     */

    @Override
    public void WinMessage(String nickname) {

        if(client.getNickname().equals(nickname)){
            clientBoard.win();
        }
        else {
            System.out.println( nickname + " wins the match! Yay!!");
        }
        client.setActive(false);
        client.getClientBeatSender().setActive(false);
        client.killClient();


    }

    /**
     * Input not valid
     */
    @Override
    public void WorkerInputNotValid() {

        System.out.println("Input not valid");

    }

    /**
     * Managing the disconnection
     * @param droppedConnection Message
     */
    @Override
    public void DroppedConnection(DroppedConnection droppedConnection) {
        String playerOut = droppedConnection.getNickname();
        System.out.println(PURPLE);
        if (playerOut == null) {
            System.out.println("Player disconnected!");
        } else {
            System.out.println(playerOut + " disconnected!");
            System.out.println("Sorry to hear that, maybe the game was too hard to handle!");
        }
        System.out.println(RESET);
    }


    /**
     * Challenger choosing the name of the first player
     * @param onlinePlayers Message
     */
    @Override
    public void SetFirstPlayer(List<PlayerInterface> onlinePlayers) {

        System.out.println("Choose the first player! Press the number near the name: ");
        int i = 1;
        for(PlayerInterface p : onlinePlayers){
            System.out.println(GREEN + i + RESET + " " + p.getWorkerRef().get(0).getColor() +  p.getNickname() + RESET);
            i = i + 1;
        }

        String player = input.nextLine();
        notifyPlayerThatStart(player, onlinePlayers);

    }

    /**
     * Prints that the server is restarting
     */
    @Override
    public void ServerRestart() {
        System.out.println("A player disconnected before the game started.\nServer is restarting, please reconnect to the server");
        client.setActive(false);
        client.getClientBeatSender().setActive(false);
        client.killClient();
    }


    /**
     * Prints the message that the length of the name is too long
     */
    @Override
    public void lengthNameError()  {
        System.out.println("The name was too long, please be under 18 characters ;) ");

    }

}
