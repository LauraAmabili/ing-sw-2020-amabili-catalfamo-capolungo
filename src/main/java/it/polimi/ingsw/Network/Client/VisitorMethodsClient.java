package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.BoardCell;
import it.polimi.ingsw.Model.Worker;
import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
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
    public synchronized void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException {

        System.out.println("Number of player ");
        int numPLayer = input.nextInt(); //TODO: to cast
        client.send(new NumberOfPlayerResponse(numPLayer));
        //chiamo metodo sulla view del client che chiede al client l'inpt e la manda indietro


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
    public void visit(CardNotPresent cardNotPresent) {

        System.out.println("Card not Present");

    }

    @Override
    public void visit(TimeToPlaceWorkers timeToPlaceWorkers) {

        System.out.println("It's "+ timeToPlaceWorkers.getCurrentPlayer() + " time to place Workers");
    }

    @Override
    public void visit(SetWorkerRequest setWorkerRequest) throws IOException {

        //System.out.println("Time to set your Workers");
        int worker = setWorkerRequest.getWorker();
        System.out.println("Insert your coordinates (x,y) as row and col for worker " + worker);
        //for(int i = 0;  i < 2; i++) {

        int row = scanner.nextInt();
        int col = scanner.nextInt();
       //System.out.println(row);
        // System.out.println(col);

            /*
            while(row > 5 || row < 1 || col > 5 || col < 1) {
                System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
                //TODO:
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
             */
            //System.out.println("Mando row e col");
        client.send(new SetWorkerResponse(row, col, worker));

    }


    @Override
    public void visit(WrongPositionForWorker wrongPositionForWorker) throws IOException {


        System.out.println("Wrong position");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        /*
        while(row > 5 || row < 1 || col > 5 || col < 1) {
            System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }
         */

        client.send(new SetWorkerResponse(row, col, wrongPositionForWorker.getWorker()));
    }

    @Override
    public void visit(PlayerOut playerOut) {

        System.out.println(playerOut.getNickname() + "'s workers are locked. Out!");


    }

    @Override
    public void visit(BoardUpdate boardUpdate) {

        System.out.println("DOVREBBE ESSERE LA BOARD");
        boardToPrint = boardUpdate.getBoard();
        boardToPrint.printGrid();
       // System.out.println(boardUpdate.getBoard());
       // boardUpdate.getBoard().printGrid();
       // boardToPrint = boardUpdate.getBoard();

    }

    @Override
    public void visit(BoardUpdateWorker boardUpdateWorker) {

        System.out.println("Worker settato correttamente in posizione "+ boardUpdateWorker.getRow() + boardUpdateWorker.getCol());
        System.out.println(GREEN+"QUESTA DOVREBBE ESSERE LA BOARD AGGIORNATA dai worker "+ RESET);

    }

    @Override
    public void visit(TurnToMove turnToMove) {

        String nickname = turnToMove.getNickname();
        System.out.println("It's time to move!");
        System.out.println("Now playing :" + nickname);

    }

    @Override
    public void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException {

        System.out.println("Time to choose your worker! Which one do you want to move? 1 0 2? ");
        int input = scanner.nextInt();
        client.send(new ChooseYourWorkerResponse(input));

    }

    @Override
    public void visit(ChooseRowAndColRequest chooseRowAndColRequest) throws IOException {

        int worker = chooseRowAndColRequest.getWorker();
        System.out.println("Choose row and col for worker " + worker + " : " );
        int row = input.nextInt();
        int col = input.nextInt();
        client.send(new ChooseRowAndColResponse(row, col, worker));


    }


    @Override
    public void visit(NicknameAccepted nicknameAccepted) {

        System.out.println("Nickname accepted");
        System.out.println("Wait for others to connect..");

    }

    @Override
    public void visit(NicknameNotValid nicknameNotValid) throws IOException {

        System.out.println("Nickname not valid");
        System.out.println("Insert Nickname: ");
        String nickname = scanner.nextLine();
        client.send(new NicknameResponse(nickname));


    }

    @Override
    public void visit(TimeToChooseCards timeToChooseCards) {

        System.out.println("It's time to choose the cards for the game!");
        System.out.println("Challenger was random, " + timeToChooseCards.getChallenger()+ " is choosing the cards");

    }

    @Override
    public void visit(CardsName cardsName) {

        //questa la stampa!!!!
        System.out.println(cardsName.getCards());

    }

    @Override
    public void visit(ChooseTheCard chooseTheCard) throws IOException {

        System.out.println("Choose card: ");
        String cardName = scanner.nextLine();
        client.send(new ChosenCard(cardName));

    }

    @Override
    public void visit(GodAdded godAdded) {

        System.out.println("God added");
        List<String> addedGods = godAdded.getAddedGods();
        System.out.println(addedGods);

    }

    @Override
    public void visit(GodNotAdded godNotAdded) {

        System.out.println("Try again, God not correct");

    }

    @Override
    public void visit(TimeToSetCard timeToSetCard) {

        String current = timeToSetCard.getCurrentPlayer();
        System.out.println("It's " + current + " time to set his Card");

    }

    @Override
    public void visit(SetYourCard setYourCard) throws IOException {

        System.out.println("Choose your card between:  " + setYourCard.getChosenGods());
        String in = scanner.nextLine();
        client.send(new ChosenGod(in));

    }

    @Override
    public void visit(SetCardUpdate setCardUpdate) {

        System.out.println("Now "+ setCardUpdate.getCurrentPlayer() + " has " + setCardUpdate.getGodName() + " as Active Card");
    }


    @Override
    public void visit(MaxPlayerReach maxPlayerReach) {
        System.out.println("Lobby is full. Try again later");
    }


}
