package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;
import it.polimi.ingsw.View.VirtualView;

import java.io.IOException;
import java.util.Scanner;

public class VisitorMethodsClient implements VisitorClient {

    Client client = new Client();
    Scanner scanner = new Scanner(System.in);

    public VisitorMethodsClient(Client client) throws IOException {
        this.client = client;
    }



    @Override
    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException {

        System.out.println("Number of player ");
        int input = scanner.nextInt();
        client.send(new NumberOfPlayerResponse("Client1", "1", input));
        //chiamo metodo sulla view del client che chiede al client l'inpt e la manda indietro


    }


    @Override
    public void visit(CardToBeAdded cardToBeAdded){

        System.out.println("This card " + cardToBeAdded.getCardName() + " has been chosen from " + cardToBeAdded.getSenderUsername());
    }


    @Override
    public void visit(ChallengerName challengerName){

        System.out.println("Challenger was random, "+ challengerName.getChallengerName() + "can now choose the Cards \n. Here are the cards " + challengerName.getGodNames());

    }
    @Override
    public void visit(NicknameResponseOk nicknameResponseOk){

    }
    @Override
    public void visit(NicknameResponseNotOk nicknameResponseNotOk){

    }

    @Override
    public void visit(Challenger challenger) {

    }

    @Override
    public void visit(TimeToChooseCards timeToChooseCards) {

    }

    @Override
    public void visit(GodAdded godAdded) {

    }

    @Override
    public void visit(GodNotAdded godNotAdded) {

    }

    @Override
    public void visit(PlayerSetCard playerSetCard) {

    }

    @Override
    public void visit(SetCard setCard) {

    }

    @Override
    public void visit(CardSet cardSet) {

    }

    @Override
    public void visit(CardNotPresent cardNotPresent) {

    }

    @Override
    public void visit(Welcome welcome) {

        System.out.println("Welcome to Santorini");
        System.out.println("Press 1 to start your Game");

    }



    @Override
    public void visit(GameReady gameReady){}

    @Override
    public void visit(NicknameRequest nicknameRequest) {

    }

}
