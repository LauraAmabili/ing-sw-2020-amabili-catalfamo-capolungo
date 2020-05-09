package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.*;
import it.polimi.ingsw.Network.Message.MessageFromServer.*;

import java.io.IOException;
import java.util.Scanner;

public class VisitorMethodsClient implements VisitorClient {

    Client client;

    Scanner scanner = new Scanner(System.in);
    private Scanner input = new Scanner(System.in);
    private Scanner string = new Scanner(System.in);

    public VisitorMethodsClient(Client client) {
        this.client = client;
    }





    @Override
    public synchronized void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException {

        System.out.println("Number of player ");
        int numPLayer = input.nextInt(); //TODO: to cast
        client.send(new NumberOfPlayerResponse(numPLayer));
        //chiamo metodo sulla view del client che chiede al client l'inpt e la manda indietro


    }


    @Override
    public void visit(CardToBeAdded cardToBeAdded){

    }
    @Override
    public void visit(ChallengerName challengerName){

        System.out.println("Challenger was random, "+ challengerName.getChallengerName() + "can now choose the Cards \n. Here are the cards " + challengerName.getGodNames());

    }

    @Override
    public synchronized void visit(NicknameRequest nicknameRequest) throws IOException {

        System.out.println("Insert nickname:");
        String nickname = string.nextLine();
        client.send(new NicknameResponse(nickname));
    }

    @Override
    public void visit(ConnectionResponse connectionResponse) {

        System.out.println("Connection established to server");

    }


    @Override
    public void visit(Challenger challenger) {

    }



    @Override
    public void visit(PlayerSetCard playerSetCard) {

    }


    @Override
    public void visit(CardSet cardSet) {

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

        System.out.println("Time to set your Workers");
        System.out.println("Insert your coordinates (x,y) as row and col");
        for(int i = 0;  i < 2; i++) {

            int row = scanner.nextInt();
            int col = scanner.nextInt();
            while(row > 5 || row < 1 || col > 5 || col < 1) {
                System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
                //TODO:
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
            client.send(new SetWorkerResponse(row, col, i));
        }
    }

    @Override
    public void visit(WrongPositionForWorker wrongPositionForWorker) throws IOException {

        System.out.println("Wrong position");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        while(row > 5 || row < 1 || col > 5 || col < 1) {
            System.out.println("Input not correct, insert coordinates greater than 1 and lesser then 5");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }
        client.send(new SetWorkerResponse(row, col, wrongPositionForWorker.getWorker()));
    }

    @Override
    public void visit(PlayerOut playerOut) {

        System.out.println(playerOut.getNickname() + "'s workers are locked. Out!");


    }

    @Override
    public void visit(BoardUpdate boardUpdate) {

        boardUpdate.getBoard().printGrid();
    }


    @Override
    public void visit(Welcome welcome) {

        System.out.println("Welcome to Santorini");
        System.out.println("Press 1 to start your Game");

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
        System.out.println(godAdded.getAddedGods());
    }

    @Override
    public void visit(GodNotAdded godNotAdded) {

        System.out.println("Try again, God not correct");

    }

    @Override
    public void visit(TimeToSetCard timeToSetCard) {

        System.out.println("It's " + timeToSetCard.getCurrentPlayer() + " time to set his Card");

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
    public void visit(GameReady gameReady){}

    @Override
    public void visit(MaxPlayerReach maxPlayerReach) {
        System.out.println("Lobby is full. Try again later");
    }


}
