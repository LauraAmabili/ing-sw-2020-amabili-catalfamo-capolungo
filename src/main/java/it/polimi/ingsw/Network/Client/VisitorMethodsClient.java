package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.NicknameResponse;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.ConnectionResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NicknameRequest;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;

import java.io.IOException;
import java.util.Scanner;

public class VisitorMethodsClient implements VisitorClient {

    Client client;
    Scanner scanner = new Scanner(System.in);

    public VisitorMethodsClient(Client client) {
        this.client = client;
    }



    @Override
    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) throws IOException {

        System.out.println("Number of player ");
        int input = scanner.nextInt(); //TODO: to cast
        client.send(new NumberOfPlayerResponse(input));
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
    public void visit(NicknameRequest nicknameRequest) throws IOException {
        System.out.println("Insert nickname:");
        String nickname = scanner.nextLine();
        client.send(new NicknameResponse(nickname));
    }

    @Override
    public void visit(ConnectionResponse connectionResponse) {

        System.out.println("Connection established to server");

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



}
