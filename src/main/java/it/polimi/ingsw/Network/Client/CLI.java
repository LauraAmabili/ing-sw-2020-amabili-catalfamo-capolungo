package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Network.Message.MessageFromClient.PlayerNumberResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI implements UserInterface {

    private Scanner string = new Scanner(System.in);
    private Scanner input = new Scanner(System.in);
    Board boardToPrint = new Board();


    ClientBoard clientBoard = new ClientBoard();

    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";

    @Override
    public String  PlayerNumberRequest() {

        System.out.println("Welcome to Santorini! Ready to play? You are gonna move and build your own island!");
        //clientBoard.Martello();

        System.out.println("Choose a game mode: \n*: 2 for a game 1v1.\n*: 3 for a game 1v1v1.");
        //clientBoard.Players();
        String num = string.nextLine();
        return num;

    }

    @Override
    public String NicknameRequest() {



        System.out.println("Insert nickname:");
        String nickname = string.nextLine();
        return nickname;
    }

    @Override
    public void ConnectionResponse() {
        System.out.println("Connection established to server");
    }

    @Override
    public void CardNotFoundRequest() {

        System.out.println("Card not Present");

    }

    @Override
    public void StartingSetWorkerTimeUpdate(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {


        System.out.println("It's "+ startingSetWorkerTimeUpdate.getCurrentPlayer() + " time to place Workers");
    }

    @Override
    public List<String> StartingSetWorkerRequest(StartingSetWorkerRequest startingSetWorkerRequest) {


        List<String> coordinates = new ArrayList<String>();
        int worker = startingSetWorkerRequest.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        coordinates.add(rowstring);
        System.out.println("Col: ");
        String colstring = string.nextLine();
        coordinates.add(colstring);
        return coordinates;



    }

    @Override
    public List<String> WrongCoordinatesUpdate(WrongCoordinatesUpdate wrongCoordinatesUpdate) {

        List<String> coordinates = new ArrayList<String>();
        int worker = wrongCoordinatesUpdate.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        coordinates.add(rowstring);
        System.out.println("Col: ");
        String colstring = string.nextLine();
        coordinates.add(colstring);

        return coordinates;
    }

    @Override
    public void PlayerLockedUpdate(PlayerLockedUpdate playerLockedUpdate) {

        System.out.println(playerLockedUpdate.getNickname() + "'s workers are locked. Out!");
    }

    @Override
    public void BoardUpdate(BoardUpdate boardUpdate) {

        boardToPrint = boardUpdate.getBoard();
        System.out.println(GREEN);
        boardToPrint.printGrid();
        System.out.println(RESET);


    }

    @Override
    public void PlayerTurnUpdate(PlayerTurnUpdate playerTurnUpdate) {

        String nickname = playerTurnUpdate.getNickname();
        System.out.println("It's time to move!");
        System.out.println("Now playing : " + nickname);


    }

    @Override
    public String ChooseYourWorkerRequest(ChooseYourWorkerRequest chooseYourWorkerRequest) {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");

        //int worker = input.nextInt();
        String worker = string.nextLine();
        return worker;

    }

    @Override
    public List<String> MoveRequest(MoveRequest moveRequest) {

        List<String> coordinates = new ArrayList<String>();
        int worker = moveRequest.getWorker();
        System.out.println("Choose row and col for worker " + worker + " : " );
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        coordinates.add(rowstring);
        System.out.println("Col: ");
        String colstring = string.nextLine();
        coordinates.add(colstring);

        return coordinates;
    }

    @Override
    public void BuildTimeUpdate() {
        System.out.println("Time to Build!");
    }

    @Override
    public List<String> BuildRequest() {

        List<String> coordinates = new ArrayList<String>();
        System.out.println("Choose where to build! Insert Row and Col: ");
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        coordinates.add(rowstring);
        System.out.println("Col: ");
        String colstring = string.nextLine();
        coordinates.add(colstring);

        return coordinates;
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
        System.out.println("Challenger was random, " + chooseCardsUpdate.getChallenger()+ " is choosing the cards");

    }

    @Override
    public void AvailableGodsUpdate(AvailableGodsUpdate availableGodsUpdate) {

        List<String> cards = availableGodsUpdate.getCards();
        System.out.println(cards);


    }

    @Override
    public String ChallengerCardsRequest(ChallengerCardsRequest challengerCardsRequest) {

        System.out.println("Choose card: ");
        String cardName = string.nextLine();
        return cardName;
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
    public String SetYourCardRequest(SetYourCardRequest setYourCardRequest) {
        System.out.println("Choose your card between:  " + setYourCardRequest.getChosenGods());
        String in = string.nextLine();
        return in;
    }

    @Override
    public void CardSetUpdate(CardSetUpdate cardSetUpdate) {

        System.out.println("Now "+ cardSetUpdate.getCurrentPlayer() + " has " + cardSetUpdate.getGodName() + " as Active Card");

    }

    @Override
    public void MaxPlayerReachedUpdate() {

        System.out.println("Lobby is full. Try again later");

    }

    @Override
    public String  AskEffect() {

        System.out.println("Do you want to use yor card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        return effect;


    }

    @Override
    public String ChooseYourWorkerEffectRequest() {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        //int worker = input.nextInt();
        String worker = string.nextLine();
        return worker;

    }

    @Override
    public String AskEffectBuild(AskEffectBuild askEffectBuild) {

        System.out.println("Do you want to use yor card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        return effect;

    }

    @Override
    public void NumberOfPlayerWrong() {
        System.out.println("Wrong number of player! ");

    }

    @Override
    public List<String> BuildTwoInputRequest(BuildTwoInputRequest buildTwoInputRequest) {

        List<String> coordinates = new ArrayList<String>();
        System.out.println("Choose row and col for the first action");
        System.out.println("Row: ");
        String rowstring1 = string.nextLine();
        coordinates.add(rowstring1);
        System.out.println("Col: ");
        String colstring1 = string.nextLine();
        coordinates.add(colstring1);
        System.out.println("Choose row and col for the second action");
        System.out.println("Row: ");
        String rowstring2 = string.nextLine();
        coordinates.add(rowstring2);
        System.out.println("Col: ");
        String colstring2 = string.nextLine();
        coordinates.add(colstring2);

        return coordinates;



    }

    @Override
    public List<String> MoveTwoInputRequest(MoveTwoInputRequest moveTwoInputRequest) {

        List<String> coordinates = new ArrayList<String>();
        System.out.println("Choose row and col for the first action");
        System.out.println("Row: ");
        String rowstring1 = string.nextLine();
        coordinates.add(rowstring1);
        System.out.println("Col: ");
        String colstring1 = string.nextLine();
        coordinates.add(colstring1);
        System.out.println("Choose row and col for the second action");
        System.out.println("Row: ");
        String rowstring2 = string.nextLine();
        coordinates.add(rowstring2);
        System.out.println("Col: ");
        String colstring2 = string.nextLine();
        coordinates.add(colstring2);

        return coordinates;

    }

    @Override
    public void WinMessage(String nickaname) {

        System.out.println(nickaname + " wins the match! Yay!!");

    }

    @Override
    public void WorkerInputNotValid() {

        System.out.println("Input not valid");

    }


    public void face(){

        System.out.println(PURPLE);
        System.out.println(" +\"\"\"\"\"+ ");
        System.out.println("[| o o |]");
        System.out.println(" |  ^  | ");
        System.out.println(" | '-' | ");
        System.out.println(" +-----+ ");
        System.out.println(RESET);
    }









}
