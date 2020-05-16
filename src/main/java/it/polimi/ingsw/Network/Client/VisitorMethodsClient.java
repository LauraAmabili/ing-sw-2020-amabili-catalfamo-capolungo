package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class VisitorMethodsClient implements VisitorClient {

    Client client;

    Scanner scanner = new Scanner(System.in);
    private Scanner input = new Scanner(System.in);
    private Scanner string = new Scanner(System.in);
    Board boardToPrint = new Board();

    public VisitorMethodsClient(Client client) {
        this.client = client;
    }

    public static String ANSI_BLUE = "\u001B[34m";
    public static String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String PURPLE = "\033[0;35m";
    public static final String RESET = "\033[0m";
    public static final String GREEN = "\033[0;32m";



    @Override
    public synchronized void visit(PlayerNumberRequest playerNumberRequest) throws IOException {

        System.out.println("Choose a game mode: \n*: 2 for a game 1v1.\n*: 3 for a game 1v1v1.");
        String num = string.nextLine();
        client.send(new PlayerNumberResponse(num));

    }

    @Override
    public synchronized void visit(NicknameRequest nicknameRequest) throws IOException {

        System.out.println("Insert nickname:");
        String nickname = string.nextLine();
        client.setNickname(nickname);
        client.send(new NicknameResponse(nickname));
    }

    @Override
    public void visit(ConnectionResponse connectionResponse) {

        System.out.println("Connection established to server");

    }



    @Override
    public void visit(CardNotFoundRequest cardNotFoundRequest) {

        System.out.println("Card not Present");

    }

    @Override
    public void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {

        System.out.println("It's "+ startingSetWorkerTimeUpdate.getCurrentPlayer() + " time to place Workers");
    }

    @Override
    public void visit(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException {

        //System.out.println("Time to set your Workers");
        int worker = startingSetWorkerRequest.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        System.out.println("Col: ");
        String colstring = string.nextLine();

        client.send(new StartingSetWorkerResponse(rowstring, colstring, worker));

    }


    @Override
    public void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException {


        System.out.println("Wrong coordinates inserted. Insert new coordinates");
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        System.out.println("Col: ");
        String colstring = string.nextLine();
        int worker = wrongCoordinatesUpdate.getWorker();
        client.send(new StartingSetWorkerResponse(rowstring, colstring, worker));





    }

    @Override
    public void visit(PlayerLockedUpdate playerLockedUpdate) {

        System.out.println(playerLockedUpdate.getNickname() + "'s workers are locked. Out!");


    }

    @Override
    public void visit(BoardUpdate boardUpdate) {


        boardToPrint = boardUpdate.getBoard();
        System.out.println(GREEN);
        boardToPrint.printGrid();
        System.out.println(RESET);

        // System.out.println(boardUpdate.getBoard());
        // boardUpdate.getBoard().printGrid();
        // boardToPrint = boardUpdate.getBoard();

    }

    @Override
    public void visit(PlayerTurnUpdate playerTurnUpdate) {

        String nickname = playerTurnUpdate.getNickname();
        System.out.println("It's time to move!");
        System.out.println("Now playing : " + nickname);

    }

    @Override
    public void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        int worker = input.nextInt();
        client.send(new ChooseYourWorkerResponse(worker));

    }

    @Override
    public void visit(MoveRequest moveRequest) throws IOException {

        int worker = moveRequest.getWorker();
        System.out.println("Choose row and col for worker " + worker + " : " );
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        System.out.println("Col: ");
        String colstring = string.nextLine();
        client.send(new MoveResponse(rowstring, colstring, worker));



    }

    @Override
    public void visit(BuildTimeUpdate buildTimeUpdate) {

        System.out.println("Time to Build!");

    }

    @Override
    public void visit(BuildRequest buildingRowAndCol) throws IOException {

        int worker = buildingRowAndCol.getWorker();
        System.out.println("Choose where to build! Insert Row and Col: ");
        System.out.println("Row: ");
        String rowstring = string.nextLine();
        System.out.println("Col: ");
        String colstring = string.nextLine();
        client.send(new BuildResponse(rowstring, colstring, worker));


    }


    @Override
    public void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest) throws IOException {


        System.out.println("Coordinates not correct! Try new coordinates");

    }

    @Override
    public void visit(WrongWorkerUpdate wrongWorkerUpdate) {

        int worker = wrongWorkerUpdate.getWorker();
        System.out.println("This worker cannot move! Another worker has been chosen for you! Move now with worker " + worker);
    }


    @Override
    public void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate) {

        System.out.println("Nickname accepted");
        System.out.println("Wait for others to connect..");

    }

    @Override
    public void visit(NicknameNotValidUpdate nicknameNotValidUpdate) throws IOException {

        System.out.println("Nickname not valid");
        
    }

    @Override
    public void visit(ChooseCardsUpdate chooseCardsUpdate) {

        System.out.println("It's time to choose the cards for the game!");
        System.out.println("Challenger was random, " + chooseCardsUpdate.getChallenger()+ " is choosing the cards");

    }

    @Override
    public void visit(AvailableGodsUpdate availableGodsUpdate) {

        List<String> cards = availableGodsUpdate.getCards();
        System.out.println(cards);

    }

    @Override
    public void visit(ChallengerCardsRequest challengerCardsRequest) throws IOException {

        System.out.println("Choose card: ");
        String cardName = string.nextLine();
        client.send(new ChosenCardsUpdate(cardName));

    }

    @Override
    public void visit(CardAddedUpdate cardAddedUpdate) {

        System.out.println("God added");
        List<String> addedGods = cardAddedUpdate.getAddedGods();
        System.out.println(addedGods);

    }

    @Override
    public void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

        System.out.println("Try again, God not correct");

    }

    @Override
    public void visit(SetCardTimeUpdate setCardTimeUpdate) {

        String current = setCardTimeUpdate.getCurrentPlayer();
        System.out.println("It's " + current + " time to set his Card");

    }

    @Override
    public void visit(SetYourCardRequest setYourCardRequest) throws IOException {

        System.out.println("Choose your card between:  " + setYourCardRequest.getChosenGods());
        String in = string.nextLine();
        client.send(new SetYourCardResponse(in));

    }

    @Override
    public void visit(CardSetUpdate cardSetUpdate) {

        System.out.println("Now "+ cardSetUpdate.getCurrentPlayer() + " has " + cardSetUpdate.getGodName() + " as Active Card");
    }


    @Override
    public void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate) {
        System.out.println("Lobby is full. Try again later");
    }

    @Override
    public void visit(AskEffect askEffect) throws IOException {

        System.out.println("Do you want to use yor card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        client.send(new AskEffectReply(effect, client.getNickname()));

    }

    @Override
    public void visit(PingRequest pingRequest){
        try {
            client.send(new PingResponse(pingRequest.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void visit(ChooseYourWorkerEffectRequest chooseYourWorkerEffectRequest) throws IOException {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        int worker = input.nextInt();
        client.send(new ChooseYourWorkerEffectResponse(worker, chooseYourWorkerEffectRequest.isEffect()));

    }

    @Override
    public void visit(AskEffectBuild askEffectBuild) throws IOException {
        System.out.println("Do you want to use yor card effect?\ny: Yes, n: No");
        String effect = string.nextLine();
        client.send(new AskEffectBuildResponse(effect, client.getNickname(), askEffectBuild.getWorker()));
    }

    @Override
    public void visit(BuildTwoInputRequest buildTwoInputRequest) throws IOException {

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
        client.send(new BuildTwoInputResponse(rowstring1, colstring1, rowstring2, colstring2, worker));


    }

    @Override
    public void visit(NumberOfPlayerWrong numberOfPlayerWrong) {

        System.out.println("Wrong number of player! ");
    }

    @Override
    public void visit(MoveTwoInputRequest moveTwoInputRequest) throws IOException {
        int worker = moveTwoInputRequest.getWorker();
        System.out.println("Choose row and col for the first action");
        System.out.println("Row: ");
        String row1 = string.nextLine();
        System.out.println("Col: ");
        String col1 = string.nextLine();
        System.out.println("Choose row and col for the second action");
        System.out.println("Row: ");
        String row2 = string.nextLine();
        System.out.println("Col: ");
        String col2 = string.nextLine();
        client.send(new MoveTwoInputResponse(row1, col1, row2, col2, worker));
    }


}
