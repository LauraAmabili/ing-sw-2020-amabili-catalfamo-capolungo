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
    public void  PlayerNumberRequest() throws IOException {

        clientBoard.Santorini();
        System.out.println("Welcome to Santorini! Ready to play? You are gonna move and build your own island!");
        //clientBoard.Martello();
        //clientBoard.Players();
        System.out.println("Choose a game mode: \n*: 2 for a game 1v1.\n*: 3 for a game 1v1v1.");
        //clientBoard.Players();
        String num = string.nextLine();
        notifyPlayerNumberResponse(num);

    }


    /**
     * Asks the nickname for the game then sends a notify with the nickname
     * @throws IOException Exception
     */

    @Override
    public void NicknameRequest() throws IOException {
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

    }


    /**
     * Print to Cli when Card is not present
     */
    @Override
    public void CardNotFoundRequest() {

        System.out.println("Card not Present");

    }

    @Override
    public void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {

        System.out.println("It's "+ startingSetWorkerTimeUpdate.getCurrentPlayer() + " time to place Workers");

    }

    @Override
    public void StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException {

        int worker = startingSetWorkerRequest.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowString = string.nextLine();
        System.out.println("Col: ");
        String colString = string.nextLine();
        notifyStartingSetWorkerResponse(rowString, colString, worker);

    }

    @Override
    public void WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException {

        int worker = wrongCoordinatesUpdate.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowString = string.nextLine();
        System.out.println("Col: ");
        String colString = string.nextLine();
        notifyWrongCoordinatesUpdate(rowString, colString, worker);

    }

    @Override
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate) {

        System.out.println(playerLockedUpdate.getNickname() + "'s workers are locked. Out!");

    }

    @Override
    public void BoardUpdate(BoardUpdate boardUpdate) {

        boardToPrint = boardUpdate.getBoard();
        boardToPrint.printGrid();

    }

    @Override
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate) {

        String nickname = playerTurnUpdate.getNickname();
        System.out.println("It's time to move!");
        System.out.println("Now playing : " + nickname);

    }

    @Override
    public void ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        //int worker = input.nextInt();
        String worker = string.nextLine();
        notifyChooseYourWorkerResponse(worker);

    }

    @Override
    public void MoveRequest(MoveRequest moveRequest) throws IOException {


        int worker = moveRequest.getWorker();
        System.out.println("Choose row and col for worker " + worker + " : " );
        System.out.println("Row: ");
        String rowString = string.nextLine();
        System.out.println("Col: ");
        String colString = string.nextLine();
        notifyMoveResponse(rowString, colString, worker);
    }

    @Override
    public void BuildTimeUpdate() {
        System.out.println("Time to Build!");
    }

    @Override
    public void BuildRequest(BuildRequest buildRequest) throws IOException {

        System.out.println("Choose where to build! Insert Row and Col: ");
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        System.out.println("Col: ");
        String colstring = string.nextLine();
        int worker = buildRequest.getWorker();

        notifyBuildResponse(rowstring, colstring,worker);
    }

    @Override
    public void TryNewCoordinatesRequest() {
        System.out.println("Coordinates not correct! Try new coordinates");

    }

    @Override
    public void WrongWorkerUpdate(WrongWorkerUpdate wrongWorkerUpdate) {

        int worker = wrongWorkerUpdate.getWorker();
        System.out.println("This worker cannot move! Another worker has been chosen for you! Move now with worker " + worker);

    }

    @Override
    public void NicknameAcceptedUpdate(NicknameAcceptedUpdate nicknameAcceptedUpdate) {

        System.out.println("Nickname accepted");
        System.out.println("Wait for others to connect..");


    }

    @Override
    public void NicknameNotValidUpdate() {

        System.out.println("Nickname not valid");

    }

    @Override
    public void ChooseCardsUpdate(ChooseCardsUpdate chooseCardsUpdate) {

        System.out.println("It's time to choose the cards for the game!");
        System.out.println("Challenger was random, " + chooseCardsUpdate.getChallenger() + " is choosing the cards");

    }

    @Override
    public void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate) {

        List<God> cards = availableGodsUpdate.getCards();
        for(God i : cards) {
            System.out.print(i.getGodName()+" ");
        }
        System.out.println();
        gods = availableGodsUpdate.getCards();


    }

    @Override
    public void ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) throws IOException {

        System.out.println("Write 'cardName -desc' to have the description of the card, 'cardName' to select the card.");
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

    @Override
    public void CardAddedUpdate(CardAddedUpdate cardAddedUpdate) {

        System.out.println("God added");
        List<String> addedGods = cardAddedUpdate.getAddedGods();
        System.out.println(addedGods);

    }

    @Override
    public void CardChallengerNotFoundRequest(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

        System.out.println("Try again, God not correct");

    }

    @Override
    public void SetCardTimeUpdate(SetCardTimeUpdate setCardTimeUpdate) {

        String current = setCardTimeUpdate.getCurrentPlayer();
        System.out.println("It's " + current + " time to set his Card");

    }

    @Override
    public void SetYourCardRequest(SetYourCardRequest setYourCardRequest) throws IOException {
        System.out.println("Choose your card between:  " + setYourCardRequest.getAvailableGods());
        System.out.println("Write 'cardName -desc' to have the description of the Card");
        List<God> chosenGods = setYourCardRequest.getChosenGods();
        String in = string.nextLine();
        boolean flag = true;
        if(in.contains("-desc")) {
            String[] phrase = in.split(" ");
            System.out.println(phrase[0]);
            for(God g : chosenGods){
                if(g.getGodName().equals(phrase[0])){
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

    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {

        System.out.println("Now "+ cardSetUpdate.getCurrentPlayer().getNickname() + " has " + cardSetUpdate.getGodName() + " as Active Card");

    }

    @Override
    public void MaxPlayerReachedUpdate() {

        System.out.println("Lobby is full. Try again later");

    }

    @Override
    public void AskEffect() throws IOException {

        System.out.println("Do you want to use yor card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        notifyAskEffectReply(effect, client.getNickname());

    }

    @Override
    public void ChooseYourWorkerEffectRequest(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        boolean effect = chooseYourWorkerEffectRequest.isEffect();
        String worker = string.nextLine();
        notifyChooseYourWorkerEffectResponse(worker, effect);

    }

    @Override
    public void AskEffectBuild(AskEffectBuild askEffectBuild) throws IOException {

        System.out.println("Do you want to use yor card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        int worker = askEffectBuild.getWorker();
        notifyAskeffectBuildResponse(effect,client.getNickname(),worker);
       // return effect;

    }

    @Override
    public void NumberOfPlayerWrong() {
        System.out.println("Wrong number of player! ");

    }

    @Override
    public void BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) throws IOException {

        int worker = buildTwoInputRequest.getWorker();
        System.out.println("Choose row and col for the first action");
        System.out.println("Row: ");
        String rowstring1 = string.nextLine();

        System.out.println("Col: ");
        String colstring1 = string.nextLine();

        System.out.println("Choose row and col for the second action");
        System.out.println("Row: ");
        String rowstring2 = string.nextLine();

        System.out.println("Col: ");
        String colstring2 = string.nextLine();


       notifyBuildTwoInputResponse(rowstring1, colstring1, rowstring2, colstring2, worker);



    }

    @Override
    public void MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) throws IOException {


        int worker = moveTwoInputRequest.getWorker();

        System.out.println("Choose row and col for the first action");
        System.out.println("Row: ");
        String rowstring1 = string.nextLine();

        System.out.println("Col: ");
        String colstring1 = string.nextLine();

        System.out.println("Choose row and col for the second action");
        System.out.println("Row: ");
        String rowstring2 = string.nextLine();

        System.out.println("Col: ");
        String colstring2 = string.nextLine();


        notifyMoveTwoInputResponse(rowstring1, colstring1, rowstring2, colstring2, worker);


    }

    @Override
    public void WinMessage(String nickaname) {

        clientBoard.win();
        System.out.println(nickaname + " wins the match! Yay!!");
        client.setActive(false);

    }

    @Override
    public void WorkerInputNotValid() {

        System.out.println("Input not valid");

    }

    @Override
    public void DroppedConnection(DroppedConnection droppedConnection) {
        String playerOut = droppedConnection.getNickname();
        if (playerOut == null) {
            System.out.println("Player disconnected!");
        } else {
            System.out.println(playerOut + " disconnected!");
        }
    }

    @Override
    public void SetFirstPlayer(List<PlayerInterface> onlinePlayers) throws IOException {

        System.out.println("Choose the first player! Press the number near the name: ");
        int i = 1;
        for(PlayerInterface p : onlinePlayers){
            System.out.println(i + p.getNickname());
            i = i + 1;
        }

        String player = input.nextLine();
        notifyPlayerThatStart(player, onlinePlayers);

    }

}
