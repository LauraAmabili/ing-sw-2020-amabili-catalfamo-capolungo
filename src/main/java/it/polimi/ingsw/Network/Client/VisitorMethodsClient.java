package it.polimi.ingsw.Network.Client;

import it.polimi.ingsw.Network.Message.*;
import it.polimi.ingsw.Network.Message.MessageFromClient.NumberOfPlayerResponse;
import it.polimi.ingsw.Network.Message.MessageFromServer.NumberOfPlayersRequest;
import it.polimi.ingsw.View.CLIView;

import java.io.IOException;

public class VisitorMethodsClient implements VisitorClient {

    Client client = new Client();
    CLIView view = new CLIView();

    public VisitorMethodsClient() throws IOException {
    }



    @Override
    public void visit(NumberOfPlayersRequest numberOfPlayersRequest) {

            System.out.println("Number of player ");
            //chiamo metodo sulla view del client che chiede al client l'inpt e la manda indietro


    }

    @Override
    public void visit(NumberOfPlayerResponse numberOfPlayerResponse) {

       //chiamo metodo che sulla view manda l'input ricevuto



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
