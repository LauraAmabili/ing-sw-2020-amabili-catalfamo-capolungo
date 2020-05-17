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

    public final UserInterface userInterface;

    public VisitorMethodsClient(Client client, UserInterface userInterface) {
        this.client = client;
        this.userInterface = userInterface;
    }




    @Override
    public synchronized void visit(PlayerNumberRequest playerNumberRequest) throws IOException {

        String num = userInterface.PlayerNumberRequest();
        client.send(new PlayerNumberResponse(num));

    }

    @Override
    public synchronized void visit(NicknameRequest nicknameRequest) throws IOException {


        String nickname = userInterface.NicknameRequest();
        client.setNickname(nickname);
        client.send(new NicknameResponse(nickname));

    }

    @Override
    public void visit(ConnectionResponse connectionResponse) {

        userInterface.ConnectionResponse();

    }

    @Override
    public void visit(CardNotFoundRequest cardNotFoundRequest) {

        userInterface.CardNotFoundRequest();

    }

    @Override
    public void visit(StartingSetWorkerTimeUpdate startingSetWorkerTimeUpdate) {

        userInterface.StartingSetWorkerTimeUpdate(startingSetWorkerTimeUpdate);

    }



    @Override
    public void visit(StartingSetWorkerRequest startingSetWorkerRequest) throws IOException {


        List<String> coor = userInterface.StartingSetWorkerRequest(startingSetWorkerRequest);
        int worker = startingSetWorkerRequest.getWorker();
        client.send(new StartingSetWorkerResponse(coor.get(0), coor.get(1), worker));

    }


    @Override
    public void visit(WrongCoordinatesUpdate wrongCoordinatesUpdate) throws IOException {


        List<String> coor = userInterface.WrongCoordinatesUpdate(wrongCoordinatesUpdate);
        int worker = wrongCoordinatesUpdate.getWorker();
        client.send(new StartingSetWorkerResponse(coor.get(0), coor.get(1), worker));


    }

    @Override
    public void visit(PlayerLockedUpdate playerLockedUpdate) {

        userInterface.PlayerLockedUpdate(playerLockedUpdate);

    }

    @Override
    public void visit(BoardUpdate boardUpdate) {



       userInterface.BoardUpdate(boardUpdate);


    }

    @Override
    public void visit(PlayerTurnUpdate playerTurnUpdate) {

        userInterface.PlayerTurnUpdate(playerTurnUpdate);

    }

    @Override
    public void visit(ChooseYourWorkerRequest chooseYourWorkerRequest) throws IOException {


        int worker = userInterface.ChooseYourWorkerRequest(chooseYourWorkerRequest);
        client.send(new ChooseYourWorkerResponse(worker));


    }

    @Override
    public void visit(MoveRequest moveRequest) throws IOException {

        List<String> coor = userInterface.MoveRequest(moveRequest);
        int worker = moveRequest.getWorker();
        client.send(new MoveResponse(coor.get(0), coor.get(1), worker));

    }

    @Override
    public void visit(BuildTimeUpdate buildTimeUpdate) {

       userInterface.BuildTimeUpdate();

    }

    @Override
    public void visit(BuildRequest buildingRowAndCol) throws IOException {


        List<String> coor = userInterface.BuildRequest();
        int worker = buildingRowAndCol.getWorker();
        client.send(new BuildResponse(coor.get(0), coor.get(1), worker));


    }


    @Override
    public void visit(TryNewCoordinatesRequest tryNewCoordinatesRequest) throws IOException {


       userInterface.TryNewCoordinatesRequest();

    }

    @Override
    public void visit(WrongWorkerUpdate wrongWorkerUpdate) {

        userInterface.WrongWorkerUpdate(wrongWorkerUpdate);

    }


    @Override
    public void visit(NicknameAcceptedUpdate nicknameAcceptedUpdate) {

        userInterface.NicknameAcceptedUpdate(nicknameAcceptedUpdate);

    }

    @Override
    public void visit(NicknameNotValidUpdate nicknameNotValidUpdate) throws IOException {

       userInterface.NicknameNotValidUpdate();
        
    }

    @Override
    public void visit(ChooseCardsUpdate chooseCardsUpdate) {

        userInterface.ChooseCardsUpdate(chooseCardsUpdate);

    }

    @Override
    public void visit(AvailableGodsUpdate availableGodsUpdate) {

       userInterface.AvailableGodsUpdate(availableGodsUpdate);
    }

    @Override
    public void visit(ChallengerCardsRequest challengerCardsRequest) throws IOException {

        String cardName = userInterface.ChallengerCardsRequest(challengerCardsRequest);
        client.send(new ChosenCardsUpdate(cardName));

    }

    @Override
    public void visit(CardAddedUpdate cardAddedUpdate) {

       userInterface.CardAddedUpdate(cardAddedUpdate);

    }

    @Override
    public void visit(CardChallengerNotFoundRequest cardChallengerNotFoundRequest) {

       userInterface.CardChallengerNotFoundRequest(cardChallengerNotFoundRequest);

    }

    @Override
    public void visit(SetCardTimeUpdate setCardTimeUpdate) {

       userInterface.SetCardTimeUpdate(setCardTimeUpdate);
    }

    @Override
    public void visit(SetYourCardRequest setYourCardRequest) throws IOException {

        String in = userInterface.SetYourCardRequest(setYourCardRequest);
        client.send(new SetYourCardResponse(in));

    }

    @Override
    public void visit(CardSetUpdate cardSetUpdate) {

        userInterface.CardSetUpdate(cardSetUpdate);

    }


    @Override
    public void visit(MaxPlayerReachedUpdate maxPlayerReachedUpdate) {

        userInterface.MaxPlayerReachedUpdate();
    }

    @Override
    public void visit(AskEffect askEffect) throws IOException {


        String effect = userInterface.AskEffect();
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

        int worker = userInterface.ChooseYourWorkerEffectRequest();
        client.send(new ChooseYourWorkerEffectResponse(worker, chooseYourWorkerEffectRequest.isEffect()));

    }

    @Override
    public void visit(AskEffectBuild askEffectBuild) throws IOException {


        String effect = userInterface.AskEffectBuild(askEffectBuild);
        client.send(new AskEffectBuildResponse(effect, client.getNickname(), askEffectBuild.getWorker()));

    }

    @Override
    public void visit(BuildTwoInputRequest buildTwoInputRequest) throws IOException {

        int worker = buildTwoInputRequest.getWorker();
        List<String> coos = userInterface.BuildTwoInputRequest(buildTwoInputRequest);
        client.send(new BuildTwoInputResponse(coos.get(0), coos.get(1), coos.get(2), coos.get(3), worker));

    }

    @Override
    public void visit(NumberOfPlayerWrong numberOfPlayerWrong) {

       userInterface.NumberOfPlayerWrong();

    }

    @Override
    public void visit(MoveTwoInputRequest moveTwoInputRequest) throws IOException {

        int worker = moveTwoInputRequest.getWorker();
        List<String> coos = userInterface.MoveTwoInputRequest(moveTwoInputRequest);
        client.send(new MoveTwoInputResponse(coos.get(0), coos.get(1), coos.get(2), coos.get(3), worker));
    }

    @Override
    public void visit(WinMessage winMessage) {

        userInterface.WinMessage(winMessage.getNickname());

    }


}
